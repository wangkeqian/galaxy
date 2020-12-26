package com.galaxy.galaxyblog.service;

import com.galaxy.galaxyblog.config.login.LoginIntercept;
import com.galaxy.galaxyblog.mapper.ArticleCommentMapper;
import com.galaxy.galaxyblog.model.ArticleComment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/12/26
 */
@Service
public class ArticleCommentService {

    @Resource
    private ArticleCommentMapper articleCommentMapper;

    public ArticleComment addComment(ArticleComment comment) {
        String id = String.valueOf(LoginIntercept.getLoginUserInfo().get("id"));
        String name = String.valueOf(LoginIntercept.getLoginUserInfo().get("name"));
        String headPhoto = String.valueOf(LoginIntercept.getLoginUserInfo().get("headPhoto"));
        comment.setCreator(id);
        comment.setCreateName(name);
        comment.setHeadPhoto(headPhoto);
        articleCommentMapper.insert(comment);
        return comment;
    }

    public List<ArticleComment> loadComment(String articleId) {

        return articleCommentMapper.loadComment(articleId);
    }
}
