package com.lzy.springbootshiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.springbootshiro.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户与角色关系dao
 */
@Repository
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

}
