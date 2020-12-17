package com.galaxy.galaxyblog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.galaxy.galaxyblog.common.HttpResultResp;
import com.galaxy.galaxyblog.common.WsResultResp;
import com.galaxy.galaxyblog.common.utils.RedisUtil;
import com.galaxy.galaxyblog.config.login.LoginIntercept;
import com.galaxy.galaxyblog.mapper.ArticleMapper;
import com.galaxy.galaxyblog.model.Article;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Function;

import static org.springframework.data.redis.connection.DataType.ZSET;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/10/24
 */
@Service
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Autowired
    private RedisUtil redisUtil;

    public List<Article> findAll(){
        return articleMapper.selectList(null);
    }



    public void insertOrUpdate(Article article) {
        ZoneOffset zoneOffset=ZoneOffset.ofHours(8);
        LocalDateTime localDateTime=LocalDateTime.now();
        if (article.getId() != null) {
            redisUtil.hdel("articleSimpleInfo",String.valueOf(article.getId()));
            redisUtil.hset("articleSimpleInfo",String.valueOf(article.getId()),article);
            redisUtil.lSet("articleList",article);
            articleMapper.updateById(article);
        }
        Map loginUserInfo = LoginIntercept.getLoginUserInfo();
        article.setCreator(String.valueOf(loginUserInfo.get("id")));
        articleMapper.insert(article);
        redisUtil.zAdd("articleByTime","article:"+String.valueOf(article.getId()),localDateTime.toEpochSecond(zoneOffset));
        /**
         * 群发推送
         */
        String id = String.valueOf(loginUserInfo.get("id"));
        Set<Object> followerList = redisUtil.zRange("followers:" + String.valueOf(loginUserInfo.get("id")), 0, -1);
        String sendMsg = "你关注的"+loginUserInfo.get("name")+"发布了【"+article.getTitle()+"】，快来看看吧";
        WebSocketServer.groupSend2Client(followerList, WsResultResp.POP_UP(sendMsg));
    }

    public Article findById(BigInteger id) {
        Map map = LoginIntercept.getLoginUserInfo();
        Long articlePV = redisUtil.rank("articlePV", id);
        if (articlePV == null){
            redisUtil.zAdd("articlePV",id,1.0);
        }else {
            // 如果存在,则获取排序值  并且+1
            Double score =redisUtil.score("articlePV", id);
            redisUtil.zAdd("articlePV", id, score + 1);
        }
        return articleMapper.findArticleById(id);
    }
    public Set<Object> hotSearch() {
        Set<Object> strSet = redisUtil.reverseRange("articlePV", 0, 10 - 1); //正确个数为下标-1
        return strSet;
    }
    public PageInfo<Article> findByParams(Article article, Integer page, int size) {
        PageHelper.startPage(page,size);
        List<Article> articles = articleMapper.searchArticlePages(article);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        return pageInfo;
    }

    public int delById(Integer id) {
        int i = articleMapper.deleteById(id);
        return i;
    }

    public String refresh() {
        List<Article> articles = articleMapper.selectList(new QueryWrapper<>());
        articles.stream().map((Function<Article, Object>) article -> {
            if (article.getContent().length() >= 31){
                article.setContent(article.getContent().substring(0,31));
            }
            return article;
        });
        System.out.println(articles);
        return "";
    }

    public String findByTitle(String key) {

        return null;
    }

    /**
     * 首先拿到关注列表
     * 根据关注列表获取文章
     * @return
     */
    public PageInfo<Article> getHomePageArticle(int page) {
        PageHelper.startPage(page,10);
        Map loginUserInfo = LoginIntercept.getLoginUserInfo();
        Set<Object> followingList = getFollowingList();
        followingList.add(loginUserInfo.get("id"));

        Map<String, Object> params = new HashMap<>();
        params.put("followingList",followingList);
        List<Article> articles = articleMapper.homePageArticle(params);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        return pageInfo;

    }

    private Set<Object> getFollowingList() {
        Map loginUserInfo = LoginIntercept.getLoginUserInfo();
        String id = String.valueOf(loginUserInfo.get("id"));
        Set<Object> list = redisUtil.zRange("following:" + id, 0, -1);
        return list;
    }
}
