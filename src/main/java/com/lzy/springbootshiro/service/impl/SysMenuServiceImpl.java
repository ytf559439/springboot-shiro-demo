package com.lzy.springbootshiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzy.springbootshiro.dao.SysMenuDao;
import com.lzy.springbootshiro.entity.SysMenu;
import com.lzy.springbootshiro.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限实现类
 * 20201124
 * lzy
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {
    /**
     * 根据角色查询用户权限
     * @param roleId 角色ID
     * @return List<SysMenu> 权限集合
     */
    @Override
    public List<SysMenu> selectSysMenuByRoleId(Long roleId) {
        return this.baseMapper.selectSysMenuByRoleId(roleId);
    }
}
