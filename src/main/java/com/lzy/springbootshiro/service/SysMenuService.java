package com.lzy.springbootshiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.springbootshiro.entity.SysMenu;

import java.util.List;

/**
 * 权限业务接口
 * 20201124
 * lzy
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 根据角色查询用户权限
     * @param roleId 角色ID
     * @return List<SysMenu> 权限集合
     */
    List<SysMenu> selectSysMenuByRoleId(Long roleId);

}
