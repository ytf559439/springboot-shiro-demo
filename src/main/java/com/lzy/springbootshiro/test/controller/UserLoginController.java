package com.lzy.springbootshiro.test.controller;

import com.lzy.springbootshiro.common.ShiroUtil;
import com.lzy.springbootshiro.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录
 * 2020-11-26
 * lzy
 */
@RestController
@RequestMapping("/userLogin")
public class UserLoginController {
    /**
     * 登录
     * @param sysUser
     * @return
     */
    @RequestMapping("/login")
    public Map<String,Object> login (@RequestBody SysUser sysUser) {
        Map<String,Object> map = new HashMap<>();
        //进行身份验证
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(),sysUser.getPassword());
            //验证成功
            subject.login(token);
        }catch (IncorrectCredentialsException e) {
            map.put("code",500);
            map.put("msg","用户不存在或者密码错误");
            return map;
        } catch (LockedAccountException e) {
            map.put("code",500);
            map.put("msg","登录失败，该用户已被冻结");
            return map;
        } catch (AuthenticationException e) {
            map.put("code",500);
            map.put("msg","该用户不存在");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","未知异常");
            return map;
        }
        map.put("code",200);
        map.put("msg","登录成功");
        map.put("token", ShiroUtil.getSession().getId().toString());
        return map;
    }

    /**
     * 未登录
     * @return
     */
    @RequestMapping("/unauth")
    public Map<String ,Object> unauth() {
        Map<String ,Object> map = new HashMap<>();
        map.put("code",500);
        map.put("msg","未登录");
        return map;
    }
}
