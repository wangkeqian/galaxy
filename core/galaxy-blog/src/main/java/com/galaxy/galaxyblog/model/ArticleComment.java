package com.galaxy.galaxyblog.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

/**
 * 文章评论
 *
 * @author wangkq
 * @date 2020/12/26
 */
@Data
public class ArticleComment extends DataEntity{
    /**
     * 回复文章id
     */
    private BigInteger articleId;
    /**
     * 回复内容
     */
    private String comment;
    //------------------以下二级评论-----------------//
    /**
     * 2级评论下，回复的评论id (父id)
     */
    private Integer replyCommentId;
    /**
     * 2级评论下，被回复的人 （用户id）
     */
    private Integer replyUserId;
    /**
     * 冗余用户名 减少表连接
     */
    private String replyUsername;
    /**
     * 二级评论内容
     */
    List<ArticleComment> childrenComment;

    //-------------展示字段，不存入数据库------------//
    /**
     * 头像地址
     */
    @TableField(exist = false)
    private String headPhoto;
    /**
     * 评论者名称
     */
    @TableField(exist = false)
    private String createName;
}
