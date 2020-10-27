package com.galaxy.galaxyblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.galaxy.galaxyblog.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> searchArticlePages(@Param("params") Article article);
}
