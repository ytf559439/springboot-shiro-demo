package com.lzy.springbootshiro.test.controller;

import com.lzy.springbootshiro.common.ShiroUtil;
import com.lzy.springbootshiro.service.SysMenuService;
import com.lzy.springbootshiro.service.SysRoleMenuService;
import com.lzy.springbootshiro.service.SysRoleService;
import com.lzy.springbootshiro.service.SysUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色测试类
 * 2020-11-26
 * lzy
 */
@RestController
@RequestMapping("/role")
public class UserRoleController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 管理员角色测试接口
     * @return
     */
    @RequestMapping("/getAdminInfo")
    @RequiresRoles("ADMIN")
    public Map<String,Object> getAdminInfo () {
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","这里是只有管理员角色才能访问的接口");
        return map;
    }
    /**
     * 用户角色测试接口
     * @return
     */
    @RequestMapping("/getUserInfo")
    @RequiresRoles("USER")
    public Map<String,Object> getUserInfo () {
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","这里是只有用户角色才能访问的接口");
        return map;
    }
    /**
     * 角色测试接口
     * @return
     */
    @RequestMapping("/getRoleInfo")
    @RequiresRoles(value = {"ADMIN","USER"},logical = Logical.OR)
    public Map<String,Object> getRoleInfo () {
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","这里是只要有ADMIN或者USER角色才能访问的接口");
        return map;
    }

    /**
     * 登出 测试
     * @return
     */
    @RequestMapping("/getLogout")
    @RequiresUser
    public Map<String,Object> getLogout () {
        ShiroUtil.logout();
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","登出");
        return map;
    }

}
