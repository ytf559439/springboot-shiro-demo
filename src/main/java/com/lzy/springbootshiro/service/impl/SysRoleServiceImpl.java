package com.lzy.springbootshiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzy.springbootshiro.dao.SysRoleDao;
import com.lzy.springbootshiro.entity.SysRole;
import com.lzy.springbootshiro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色实现类
 * 20201124
 * lzy
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    /**
     * 根据用户ID获取用户角色集合
     * @param userId
     * @return List<SysRole> 角色集合
     */
    @Override
    public List<SysRole> selectSysRoleByUserId(Long userId) {
        System.out.println(this.baseMapper.selectById(userId));
        return sysRoleDao.selectSysRoleByUserId(userId);
        //return sysRoleDao.selectSysRoleByUserId(userId);
    }
}
