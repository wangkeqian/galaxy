package com.galaxy.galaxyblog.model.vo;

import com.galaxy.galaxyblog.model.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/12/10
 */
@Data
@AllArgsConstructor
public class SysUserVo  {
    private SysUser sysUser;
    private String token;
}
