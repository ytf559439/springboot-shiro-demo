package com.lzy.springbootshiro.test.controller;

import com.lzy.springbootshiro.common.ShiroUtil;
import com.lzy.springbootshiro.entity.SysMenu;
import com.lzy.springbootshiro.entity.SysRole;
import com.lzy.springbootshiro.entity.SysRoleMenu;
import com.lzy.springbootshiro.entity.SysUser;
import com.lzy.springbootshiro.service.SysMenuService;
import com.lzy.springbootshiro.service.SysRoleMenuService;
import com.lzy.springbootshiro.service.SysRoleService;
import com.lzy.springbootshiro.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class UserMenuController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 获取用户信息集合
     * @return
     */
    @RequestMapping("/getUserInfoList")
    @RequiresPermissions("sys:user:info")
    public Map<String,Object> getUserInfoList () {
        Map<String,Object> map = new HashMap<>();
        List<SysUser> sysUserList = sysUserService.list();
        map.put("sysUserList",sysUserList);
        return map;
    }

    /**
     * 获取角色信息集合
     * @return
     */
    @RequestMapping("/getRoleInfoList")
    @RequiresPermissions("sys:role:info")
    public Map<String,Object> getRoleInfoList () {
        Map<String,Object> map = new HashMap<>();
        List<SysRole> sysRoleList = sysRoleService.list();
        map.put("sysRoleList",sysRoleList);
        return map;
    }

    /**
     * 获取权限信息集合
     * @Return Map<String,Object> 返回结果
     */
    @RequestMapping("/getMenuInfoList")
    @RequiresPermissions("sys:menu:info")
    public Map<String,Object> getMenuInfoList(){
        Map<String,Object> map = new HashMap<>();
        List<SysMenu> sysMenuList = sysMenuService.list();
        map.put("sysMenuList",sysMenuList);
        return map;
    }

    /**
     * 获取所有数据
     * @Return Map<String,Object> 返回结果
     */
    @RequestMapping("/getInfoAll")
    @RequiresPermissions("sys:info:all")
    public Map<String,Object> getInfoAll(){
        Map<String,Object> map = new HashMap<>();
        List<SysUser> sysUserList = sysUserService.list();
        map.put("sysUserList",sysUserList);
        List<SysRole> sysRoleList = sysRoleService.list();
        map.put("sysRoleList",sysRoleList);
        List<SysMenu> sysMenuList = sysMenuService.list();
        map.put("sysMenuList",sysMenuList);
        return map;
    }

    /**
     * 添加管理员角色权限
     * @return
     */
    @RequestMapping("/addMenu")
    public Map<String,Object> addMenu () {
        //添加管理员角色权限
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setMenuId(4L);
        sysRoleMenu.setRoleId(1L);
        sysRoleMenuService.save(sysRoleMenu);
        //消除缓存
        String username = "admin";
        ShiroUtil.deleteCache(username,false);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","权限添加成功");
        return map;
    }

}
