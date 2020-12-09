package com.lzy.springbootshiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.springbootshiro.entity.SysRole;

import java.util.List;

/**
 * 角色业务接口
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 根据用户ID查询用户角色集合
     * @param userId
     * @return List<SysRole> 用户角色集合
     */
    List<SysRole> selectSysRoleByUserId(Long userId);
}
