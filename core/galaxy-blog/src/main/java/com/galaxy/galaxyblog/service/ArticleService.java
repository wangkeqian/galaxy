package com.galaxy.galaxyblog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.galaxy.galaxyblog.common.utils.RedisUtil;
import com.galaxy.galaxyblog.mapper.ArticleMapper;
import com.galaxy.galaxyblog.model.Article;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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

    @Transactional
    public int insertOrUpdate(Article article) {
        if (article.getId() != null) {
            redisUtil.hdel("articleSimpleInfo",String.valueOf(article.getId()));
            redisUtil.hset("articleSimpleInfo",String.valueOf(article.getId()),article);
            redisUtil.lSet("articleList",article);
            return articleMapper.updateById(article);
        }
        redisUtil.hset("articleSimpleInfo",article.getId(),article);
        return articleMapper.insert(article);
    }

    public Article findById(BigInteger id) {
        return articleMapper.selectById(id);
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
}
