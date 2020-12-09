package com.lzy.springbootshiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.springbootshiro.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统用户dao
 */
@Repository
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

}
