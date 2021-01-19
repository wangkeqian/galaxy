package com.galaxy.galaxyblog.service;

import com.galaxy.galaxyblog.common.WsResultResp;
import com.galaxy.galaxyblog.common.myenum.WsResultTypeEnum;
import com.galaxy.galaxyblog.common.utils.CommonUtils;
import com.galaxy.galaxyblog.common.utils.RedisUtil;
import com.galaxy.galaxyblog.config.login.LoginIntercept;
import com.galaxy.galaxyblog.mapper.ArticleMapper;
import com.galaxy.galaxyblog.model.Article;
import com.galaxy.galaxyblog.model.vo.ArticleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

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
        if (article.getId() != null) {
            redisUtil.hdel("articleSimpleInfo",String.valueOf(article.getId()));
            redisUtil.hset("articleSimpleInfo",String.valueOf(article.getId()),article);
            redisUtil.lSet("articleList",article);
            articleMapper.updateById(article);
        }
        Map loginUserInfo = LoginIntercept.getLoginUserInfo();
        article.setCreator(String.valueOf(loginUserInfo.get("id")));
        articleMapper.insert(article);
        redisUtil.zAdd("articleByTime","article:"+String.valueOf(article.getId()), CommonUtils.getLocalTimestamp());
        /**
         * 群发推送
         */
        String id = String.valueOf(loginUserInfo.get("id"));
        Set<Object> followerList = redisUtil.zRange("followers:" + String.valueOf(loginUserInfo.get("id")), 0, -1);
        String sendMsg = "你关注的"+loginUserInfo.get("name")+"发布了【"+article.getTitle()+"】，快来看看吧";
        WebSocketServer.groupSend2Client(followerList, WsResultResp.POP_UP(sendMsg, WsResultTypeEnum.MY_FOCUS));
    }

    public Article findById(BigInteger id) {
        return fill(articleMapper.findArticleById(id),true);
    }
    public Set<Object> hotSearch() {
        Set<Object> strSet = redisUtil.reverseRange("articlePV", 0, 10 - 1); //正确个数为下标-1
        return strSet;
    }
    public PageInfo<ArticleVo> findByParams(Article article, Integer page, int size) {
        PageHelper.startPage(page,size);
        List<Article> articles = articleMapper.searchArticlePages(article);
        PageInfo<ArticleVo> pageInfo = new PageInfo<>(fill(articles));
        return pageInfo;
    }

    /**
     * 填充属性
     * @param article 文章
     * @param single 是否点击量+1
     * @return
     */
    private ArticleVo fill(Article article,Boolean single){
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        BigInteger id = article.getId();
        //获取评分
        Map<Object, Object> rateMap = redisUtil.hmget("article:grade:" + id);
        //计算/获取访问量
        Double pageView =redisUtil.score("articlePV", id);
        if (pageView == null){
            redisUtil.zAdd("articlePV",id,1.0);
        }else if(single) {
            // 如果存在,并且single = true ,则获取排序值  并且+1
            redisUtil.zAdd("articlePV", id, pageView + 1);
        }
        //获取收藏量 TODO
        Double score = redisUtil.score("article:collector:" + LoginIntercept.getLoginUserId(), String.valueOf(id));
        if (score == null)
            articleVo.setCollectIs(0);
        else
            articleVo.setCollectIs(1);

        articleVo.setPageView(pageView);
        if (0 != rateMap.size()){
            articleVo.setArticleGrade((Double) rateMap.get("rate"));
            articleVo.setArticleGradeNum((Integer) rateMap.get("rateNum"));
        }else {
            articleVo.setArticleGrade(0.0);
            articleVo.setArticleGradeNum(1);
        }
        return articleVo;
    }
    private List<ArticleVo> fill(List<Article> articles) {
        List<ArticleVo> articleVos = new ArrayList<>();
        articles.forEach( e ->{
            articleVos.add(fill(e,false));
        });
        return articleVos;
    }

    public int delById(Integer id) {
        int i = articleMapper.deleteById(id);
        return i;
    }


    public String findByTitle(String key) {

        return null;
    }

    /**
     * 首先拿到关注列表
     * 根据关注列表获取文章
     * @return
     */
    public PageInfo<ArticleVo> getHomePageArticle(int page) {
        PageHelper.startPage(page,10);
        Map loginUserInfo = LoginIntercept.getLoginUserInfo();
        Set<Object> followingList = getFollowingList();
        followingList.add(loginUserInfo.get("id"));

        Map<String, Object> params = new HashMap<>();
        params.put("followingList",followingList);
        List<Article> articles = articleMapper.homePageArticle(params);
        PageInfo<ArticleVo> pageInfo = new PageInfo<>(fill(articles));
        return pageInfo;

    }

    private Set<Object> getFollowingList() {
        Map loginUserInfo = LoginIntercept.getLoginUserInfo();
        String id = String.valueOf(loginUserInfo.get("id"));
        Set<Object> list = redisUtil.zRange("following:" + id, 0, -1);
        return list;
    }

    /**
     * 取点击榜前十名
     * @return
     */
    public List<Article> getHotRankingList() {
        Set<Object> articlePV = redisUtil.zRevRange("articlePV", 0, 9);
        return articleMapper.getHotRankingListByPvScores(articlePV);
    }

    //评分
    public synchronized Map rate(String id, Double rate) {
        Map<Object, Object> rateMap = redisUtil.hmget("article:grade:" + id);
        Map rateMap1 = new HashMap<>();
        if (rateMap.size() == 0){
            rateMap1.put("rateNum",1);
            rateMap1.put("rate",rate);
            redisUtil.hmset("article:grade:" + id,rateMap1);
        }else {
            // 如果存在,则获取排序值  并且计算平均数
            Double rate1 = (Double) rateMap.get("rate");
            Integer rateNum = (Integer) rateMap.get("rateNum");
            rateNum ++;
            rate =  (rate1 * (rateNum-1) + rate)/rateNum;
            rateMap1.put("rateNum",rateNum);
            rateMap1.put("rate",Double.valueOf(String.format("%.2f", rate)));
            redisUtil.hmset("article:grade:" + id,rateMap1);
        }
        return rateMap1;
    }

    public String collect(String articleId) {
        Double score = redisUtil.score("article:collector:" + LoginIntercept.getLoginUserId(), articleId);
        if (null == score){
            redisUtil.zAdd("article:collector:"+LoginIntercept.getLoginUserId(),articleId,CommonUtils.getLocalTimestamp());
            return "已收藏";
        }else {
            redisUtil.zRem("article:collector:"+LoginIntercept.getLoginUserId(),articleId);
            return "取消收藏";
        }
    }
}
