package com.lzy.springbootshiro.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * 自定义sessionId生成器
 * 2020-11-26
 * lzy
 */
public class ShiroSessionIdGenerator implements SessionIdGenerator {

    /**
     * 实现sessionId生成
     * @param session
     * @return
     */
    @Override
    public Serializable generateId(Session session) {
        Serializable sessionId = new JavaUuidSessionIdGenerator().generateId(session);
        return String.format("login_token_%s",sessionId);
    }
}
