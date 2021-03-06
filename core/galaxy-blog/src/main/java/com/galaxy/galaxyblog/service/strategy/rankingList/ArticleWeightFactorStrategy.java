package com.galaxy.galaxyblog.service.strategy.rankingList;

import com.galaxy.galaxyblog.model.Article;
import com.galaxy.galaxyblog.model.WeightFactor;
import com.galaxy.galaxyblog.model.vo.ArticleVo;
import lombok.*;

import java.util.List;

/**
 * 文章排行榜加权算法
 *
 * @author wangkq
 * @date 2021/3/4
 */
@Data
public class ArticleWeightFactorStrategy extends WeightFactorStrategy<ArticleVo> {
    List<Article> articles;

    public ArticleWeightFactorStrategy(WeightFactor weightFactor) {
        super.weightFactor = weightFactor;
    }

    @Override
    void setWeightFactor(WeightFactor weightFactor) {
        super.weightFactor = weightFactor;
    }

    @Override
    public Object getResult() {
        return articles;
    }
}
