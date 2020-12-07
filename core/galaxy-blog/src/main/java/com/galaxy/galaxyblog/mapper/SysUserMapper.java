package com.galaxy.galaxyblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.galaxy.galaxyblog.model.SysUser;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/12/6
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser verifySysUser(String username, String password);
}
