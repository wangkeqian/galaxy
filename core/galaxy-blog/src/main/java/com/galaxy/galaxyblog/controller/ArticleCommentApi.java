package com.galaxy.galaxyblog.controller;

import com.galaxy.galaxyblog.common.HttpResultResp;
import com.galaxy.galaxyblog.model.ArticleComment;
import com.galaxy.galaxyblog.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章评论控制器
 *
 * @author wangkq
 * @date 2020/12/26
 */
@RestController
@RequestMapping("/comment")
public class ArticleCommentApi {
    @Autowired
    private ArticleCommentService articleCommentService;

    @PostMapping("/addComment")
    public HttpResultResp addComment(@RequestBody ArticleComment comment){
        return HttpResultResp.ok(articleCommentService.addComment(comment));
    }
    @GetMapping("loadComment/{articleId}")
    public HttpResultResp loadComment(@PathVariable String articleId){
        return HttpResultResp.ok(articleCommentService.loadComment(articleId));
    }
}
