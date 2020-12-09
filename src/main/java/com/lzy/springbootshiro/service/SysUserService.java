package com.lzy.springbootshiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.springbootshiro.entity.SysUser;

/**
 * 系统用户业务接口
 * 20201124
 * lzy
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名查询user实体
     * @param username
     * @return
     */
    SysUser selectUserByName(String username);
}
