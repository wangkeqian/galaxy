package com.galaxy.galaxyblog.controller;

import com.galaxy.galaxyblog.common.HttpResultResp;
import com.galaxy.galaxyblog.model.Article;
import com.galaxy.galaxyblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 *
 *
 * @author wangkq
 * @date 2020/10/24
 */
@RestController
public class ArticleViewApi {

    @Autowired
    ArticleService articleService;

    @GetMapping("/article/{id}")
    public HttpResultResp searchArticle(@PathVariable BigInteger id){
        return HttpResultResp.ok(articleService.findById(id));
    }

    @GetMapping("/article/find")
    public HttpResultResp searchArticleByParams(Article Args,
                                                Integer page,
                                                @RequestParam(
                                                        name = "size",
                                                        required = false,
                                                        defaultValue = "10") int size){
        return HttpResultResp.ok(articleService.findByParams(Args,page,size));
    }

}
