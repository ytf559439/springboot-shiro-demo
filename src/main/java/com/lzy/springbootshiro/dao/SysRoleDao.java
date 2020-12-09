package com.lzy.springbootshiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.springbootshiro.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色dao
 * 20201124
 * lzy
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

    /**
     * 根据用户ID查询用户角色集合
     * @param userId
     * @return List<SysRole> 用户角色集合
     */
    List<SysRole> selectSysRoleByUserId(Long userId);
}
