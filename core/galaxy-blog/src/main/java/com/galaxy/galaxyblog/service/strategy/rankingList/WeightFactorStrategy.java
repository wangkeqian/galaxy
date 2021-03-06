package com.galaxy.galaxyblog.service.strategy.rankingList;

import com.galaxy.galaxyblog.model.DataEntity;
import com.galaxy.galaxyblog.model.WeightFactor;
import lombok.Data;

/**
 * 权重因子策略
 *
 *
 * @author wangkq
 * @date 2021/3/4
 */
@Data
public abstract class WeightFactorStrategy<T extends DataEntity> implements RankingListStrategy {
    protected WeightFactor weightFactor;
    abstract void setWeightFactor(WeightFactor weightFactor);


}
