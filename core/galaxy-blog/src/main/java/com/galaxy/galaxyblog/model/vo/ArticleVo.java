package com.galaxy.galaxyblog.model.vo;

import com.galaxy.galaxyblog.model.Article;
import lombok.Data;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2021/1/18
 */
@Data
public class ArticleVo extends Article {
    /**
     * 访问量
     */
    private Double pageView;
    /**
     * 评分
     */
    private Double articleGrade;
    /**
     * 评分人数
     */
    private Integer articleGradeNum;
    /**
     * 收藏量
     */
    private int collectNum;
}
