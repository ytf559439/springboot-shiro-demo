package com.lzy.springbootshiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.springbootshiro.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限dao
 * 20201124
 * lzy
 */
@Repository("sysMenuDao")
public interface SysMenuDao extends BaseMapper<SysMenu> {
    /**
     * 根据角色查询用户权限
     * @param roleId 角色ID
     * @return List<SysMenu> 权限集合
     */
    List<SysMenu> selectSysMenuByRoleId(Long roleId);

}
