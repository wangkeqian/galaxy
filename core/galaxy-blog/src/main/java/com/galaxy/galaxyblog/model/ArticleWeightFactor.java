package com.galaxy.galaxyblog.model;

import com.galaxy.galaxyblog.config.excep.MyException;

import javax.annotation.PostConstruct;

import static com.galaxy.galaxyblog.common.myenum.ErrorExceptionEnum.WEIGHT_FACTOR_ADD_ERROR;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2021/3/5
 */
public class ArticleWeightFactor implements WeightFactor {
    /**
     * 访问量
     */
    private int pageView;
    /**
     * 评分
     */
    private int articleGrade;
    /**
     * 评分人数
     */
    private int articleGradeNum;
    /**
     * 收藏量
     */
    private int collectNum;

    // 默认
    public ArticleWeightFactor() {
        this.articleGrade = 10;
        this.articleGrade = 20;
        this.articleGradeNum = 30;
        this.collectNum = 40;
    }

    public ArticleWeightFactor(int pageView, int articleGrade, int articleGradeNum, int collectNum) {
        this.pageView = pageView;
        this.articleGrade = articleGrade;
        this.articleGradeNum = articleGradeNum;
        this.collectNum = collectNum;
        checkSum();
    }

    @Override
    @PostConstruct
    public void checkSum() {
        int totalSum = articleGrade+collectNum+articleGrade+articleGradeNum;
        if (totalSum != 100){
            throw new MyException(WEIGHT_FACTOR_ADD_ERROR);
        }
    }
}
