package com.galaxy.galaxyblog.service;

import com.galaxy.galaxyblog.common.WsResultResp;
import com.galaxy.galaxyblog.common.myenum.WsResultTypeEnum;
import com.galaxy.galaxyblog.config.login.LoginIntercept;
import com.galaxy.galaxyblog.mapper.ArticleCommentMapper;
import com.galaxy.galaxyblog.model.Article;
import com.galaxy.galaxyblog.model.ArticleComment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private ArticleService articleService;

    public ArticleComment addComment(ArticleComment comment) {
        String id = String.valueOf(LoginIntercept.getLoginUserInfo().get("id"));
        String name = String.valueOf(LoginIntercept.getLoginUserInfo().get("name"));
        String headPhoto = String.valueOf(LoginIntercept.getLoginUserInfo().get("headPhoto"));
        comment.setCreator(id);
        comment.setCreateName(name);
        comment.setHeadPhoto(headPhoto);
        articleCommentMapper.insert(comment);
        /**
         * 群发推送
         */
        // 1.判断是回复文章还是回复人
        // 2.
        Article at = null;
        Set<Object> target= new HashSet<>();
        if (comment.getReplyUserId() == null){
            at = articleService.findById(comment.getArticleId());
            target.add(at.getCreator());
        }else {
            target.add(comment.getReplyUserId());
        }
        String sendMsg = "['@"+name+"'回复了你]";
        WebSocketServer.groupSend2Client(target, WsResultResp.POP_UP(sendMsg, WsResultTypeEnum.MY_REPLY));
        return comment;
    }

    public List<ArticleComment> loadComment(String articleId) {

        return articleCommentMapper.loadComment(articleId);
    }
}
