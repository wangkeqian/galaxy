package com.galaxy.galaxyblog.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 文章
 *
 * @author wangkq
 * @date 2020/10/24
 */
@Data
public class Article extends DataEntity {
    /**
     * 标题
     */
    private String title;
    /**
     * 副标题
     */
    private String subtitle;
    /**
     * 文本内容  Markdown格式
     */
    private String content;
    /**
     * 标签  用英文逗号分割
     */
    private String tag;
    /**
     * 访问量
     */
    private int pageView;
    /**
     * 点赞量
     */
    private int likeNum;
    /**
     * 反对量
     */
    private int againstNum;

    /**
     * --------------展示字段，非数据库字段------
     */
    @TableField(exist = false)
    private String author; //作者名

}
