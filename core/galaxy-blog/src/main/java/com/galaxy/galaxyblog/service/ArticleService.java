package com.galaxy.galaxyblog.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.galaxy.galaxyblog.mapper.ArticleMapper;
import com.galaxy.galaxyblog.model.Article;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

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

    public List<Article> findAll(){
        return articleMapper.selectList(null);
    }

    public int insertOrUpdate(Article article) {
        if (article.getId() != null) return articleMapper.updateById(article);
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
}
