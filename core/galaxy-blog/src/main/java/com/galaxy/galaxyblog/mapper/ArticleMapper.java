package com.galaxy.galaxyblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.galaxy.galaxyblog.model.Article;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> searchArticlePages(@Param("params") Article article);
    Article findArticleById(@Param("id") BigInteger id);
    List<Article> homePageArticle(@Param("params")Map params);
}
