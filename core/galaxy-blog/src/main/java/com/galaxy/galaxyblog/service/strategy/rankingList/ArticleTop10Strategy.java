package com.galaxy.galaxyblog.service.strategy.rankingList;

import com.galaxy.galaxyblog.common.utils.RedisCacheUtil;
import com.galaxy.galaxyblog.common.utils.RedisUtil;
import com.galaxy.galaxyblog.mapper.ArticleMapper;
import com.galaxy.galaxyblog.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2021/3/6
 */
@Component
public class ArticleTop10Strategy implements RankingListStrategy{
    @Resource
    private ArticleMapper articleMapper;
    @Autowired
    private RedisUtil redisUtil = new RedisUtil();
    @Override
    public Object getResult() {
                return RedisCacheUtil.get("articlePV",
                key -> (List<Article>)redisUtil.get("hotRankingList"),
                key -> articleMapper.getHotRankingListByPvScores(redisUtil.zRevRange(key, 0, 9)),
                (k,v)-> redisUtil.set("hotRankingList",v));
    }
}
