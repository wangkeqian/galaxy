package com.galaxy.galaxyblog.controller;

import com.galaxy.galaxyblog.common.HttpResultResp;
import com.galaxy.galaxyblog.model.Article;
import com.galaxy.galaxyblog.service.ArticleService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Arrays;

/**
 *
 *
 * @author wangkq
 * @date 2020/10/24
 */
@RestController
@RequestMapping("/article")
public class ArticleViewApi {

    @Autowired
    ArticleService articleService;

    @GetMapping("/{id}")
    public HttpResultResp searchArticle(@PathVariable BigInteger id){
        return HttpResultResp.ok(articleService.findById(id));
    }

    @GetMapping("/find")
    public HttpResultResp searchArticleByParams(Article Args,
                                                Integer page,
                                                @RequestParam(
                                                        name = "size",
                                                        required = false,
                                                        defaultValue = "10") int size){
        return HttpResultResp.ok(articleService.findByParams(Args,page,size));
    }

    @GetMapping("/searchArticleByTitle")
    public HttpResultResp searchArticleByTitle(@RequestParam(name = "key") String key){

        return HttpResultResp.ok(articleService.findByTitle(key));
    }
}
