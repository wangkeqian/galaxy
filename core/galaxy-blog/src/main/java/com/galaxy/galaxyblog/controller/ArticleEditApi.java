package com.galaxy.galaxyblog.controller;

import com.galaxy.galaxyblog.common.HttpResultResp;
import com.galaxy.galaxyblog.model.Article;
import com.galaxy.galaxyblog.service.ArticleService;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/10/24
 */
@RestController
@RequestMapping("/article")
public class ArticleEditApi {
    @Autowired
    ArticleService articleService;

    @PostMapping("/add")
    public HttpResultResp addArticle(@RequestBody Article article){
        articleService.insertOrUpdate(article);
        return HttpResultResp.ok(article.getId());
    }
    @DeleteMapping("/del/{id}")
    public HttpResultResp delArticle(@PathVariable("id")Integer id){
        return HttpResultResp.ok(articleService.delById(id));
    }
    @GetMapping("/rate/{id}/{rate}")
    public HttpResultResp rate(@PathVariable("id") String id,@PathVariable Double rate){
        return HttpResultResp.ok(articleService.rate(id,rate));
    }
    @GetMapping("/collect/{id}")
    public HttpResultResp collect(@PathVariable String id){
        return HttpResultResp.ok(articleService.collect(id));
    }
}
