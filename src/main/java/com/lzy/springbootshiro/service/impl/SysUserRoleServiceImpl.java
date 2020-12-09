package com.lzy.springbootshiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzy.springbootshiro.dao.SysUserRoleDao;
import com.lzy.springbootshiro.entity.SysUserRole;
import com.lzy.springbootshiro.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户与角色关系实现类
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

}
