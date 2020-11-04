package com.galaxy.galaxyblog.model;

import lombok.Data;

/**
 * 思维导图
 *
 * @author wangkq
 * @date 2020/11/3
 */
@Data
public class MindMap extends DataEntity {
    private String title;
    private String data;
}
