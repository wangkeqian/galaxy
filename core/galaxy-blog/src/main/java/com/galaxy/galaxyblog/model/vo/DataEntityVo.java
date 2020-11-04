package com.galaxy.galaxyblog.model.vo;

import com.galaxy.galaxyblog.model.DataEntity;
import lombok.Data;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/11/4
 */
@Data
public class DataEntityVo extends DataEntity {
    private int page;
    private int size;
}
