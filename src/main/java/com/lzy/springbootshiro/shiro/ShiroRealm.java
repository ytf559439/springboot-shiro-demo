package com.lzy.springbootshiro.shiro;

import com.lzy.springbootshiro.common.ShiroUtil;
import com.lzy.springbootshiro.entity.SysMenu;
import com.lzy.springbootshiro.entity.SysRole;
import com.lzy.springbootshiro.entity.SysUser;
import com.lzy.springbootshiro.service.SysMenuService;
import com.lzy.springbootshiro.service.SysRoleService;
import com.lzy.springbootshiro.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro权限匹配和账号密码匹配
 * 2020-11-26
 * lzy
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 授权权限
     * 用户进行权限验证的时候shiro会去缓存中找，如果查不到数据，会执行这个方法去查找权限，并放入缓存中
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        //获取用户ID
        Long userId = sysUser.getUserId();
        //这里可以进行授权和处理
        Set<String> rolesSet = new HashSet<>();
        Set<String> permsSet = new HashSet<>();
        //查询角色和权限（这里根据业务自行查询）
        List<SysRole> sysRoleList = sysRoleService.selectSysRoleByUserId(userId);
        for (SysRole sysRole : sysRoleList) {
            //添加角色信息
            rolesSet.add(sysRole.getRoleName());
            //根据角色ID获取角色权限集合
            List<SysMenu> sysMenuList = sysMenuService.selectSysMenuByRoleId(sysRole.getRoleId());
            for (SysMenu sysMenu : sysMenuList) {
                permsSet.add(sysMenu.getPerms());
            }
        }
        //将查到的权限和角色分别传入authorizationInfo中
        authorizationInfo.setStringPermissions(permsSet);
        authorizationInfo.setRoles(rolesSet);
        return authorizationInfo;
    }

    /**
     * 身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户输入的账号
        String username = (String) authenticationToken.getPrincipal();
        //通过username从数据库中查找user对象 ，如果找到进行验证
        //在实际项目中，这里可以根据实际情况做缓存，如果不做，shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser user = sysUserService.selectUserByName(username);
        //判断账号是否存在
        if (user == null) {
            throw new AuthenticationException();
        }
        //判断账号是否被冻结
        if (user.getState() == null || "PROHIBIT".equals(user.getState())) {
            throw new LockedAccountException();
        }
        //进行验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,                                   //用户
                user.getPassword(),                     //密码
                ByteSource.Util.bytes(user.getSalt()),  //设置盐值
                getName()
        );
        //验证成功，消除缓存和session
        ShiroUtil.deleteCache(username,true);
        return authenticationInfo;
    }
}
