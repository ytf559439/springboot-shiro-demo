package com.lzy.springbootshiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.springbootshiro.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色与权限关系dao
 * 20201124
 * lzy
 */
@Repository("sysRoleMenuDao")
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

}
