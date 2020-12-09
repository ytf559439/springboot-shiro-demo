package com.lzy.springbootshiro.common;

import com.lzy.springbootshiro.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;


import java.net.Authenticator;
import java.util.Collection;
import java.util.Objects;

/**
 * shiro工具类
 * 2020-11-26
 * lzy
 */
public class ShiroUtil {
    /**
     * 私有构造方法
     */
    private ShiroUtil(){}

    private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

    /**
     * 获取当前用户Session
     * @return
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 用户登出
     */
    public static void logout(){
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前用户信息
     * @return  SysUser 用户信息
     */
    public static SysUser getUserInfo(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 删除用户缓存信息
     * @param username 用户名称
     * @param isRemoveSession 是否删除session
     */
    public static void deleteCache(String username,boolean isRemoveSession) {
        Session session = null;
        //从缓存中获取session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        SysUser sysUser;
        Object attribute = null;
        //循环遍历sessions
        for (Session sessionInfo : sessions ) {
            //遍历sessions ，找到该用户名称对应的session
            attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attribute == null) continue;
            sysUser = (SysUser) ((SimplePrincipalCollection)attribute).getPrimaryPrincipal();
            if (sysUser == null) continue;
            if (Objects.equals(sysUser.getUsername(),username)) {
                session = sessionInfo;
                break;
            }
        }
        if (session == null || attribute == null) return;
        //删除session
        if (isRemoveSession) redisSessionDAO.delete(session);
        //删除cache，在访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authc = (Authenticator) securityManager.getAuthenticator();
        ((LogoutAware)authc).onLogout((PrincipalCollection) attribute);
    }
}
