package com.galaxy.galaxyblog.service.strategy;

import com.galaxy.galaxyblog.service.strategy.rankingList.RankingListStrategy;

import java.util.List;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2021/3/6
 */
public class StrategyDirector <T> {
    static RankingListStrategy strategy;
    public static Object getResult(RankingListStrategy strategy){
        strategy = strategy;
        return strategy.getResult();
    }

}
