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

    /**
     * 博客管理查询接口
     * @param Args
     * @param page
     * @param size
     * @return
     */
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
    @GetMapping("/getHotArticle")
    public HttpResultResp getHotArticle(){
        return HttpResultResp.ok(articleService.hotSearch());
    }
    /*
      获取主页文章
     */
    @GetMapping("/homePageArticleQuery")
    public HttpResultResp homePageArticleQuery(Integer page){
        return HttpResultResp.ok(articleService.getHomePageArticle(page));
    }
    /**
     * 获取热门排行榜
     */
    @GetMapping("/hotRankingList")
    public HttpResultResp hotRankingList(){
        return HttpResultResp.ok(articleService.getHotRankingList());
    }
}
