package com.galaxy.galaxyblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.galaxy.galaxyblog.model.Article;
import com.galaxy.galaxyblog.model.ArticleComment;

import java.util.List;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/12/26
 */

public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {
    List<ArticleComment> loadComment(String articleId);

}
