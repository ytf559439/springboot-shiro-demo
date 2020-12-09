package com.lzy.springbootshiro.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户实体
 * 2020-11-24
 * lzy
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId
    private Long userId;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 状态 ： NORMAL正常
     * PROHIBIT禁用
     */
    private String state;
}
