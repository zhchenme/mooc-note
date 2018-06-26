package com.jas.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jas
 * @create 2018-06-26 9:04
 **/
public class AuthenticationTest {
    
    private SimpleAccountRealm realm = new SimpleAccountRealm();
    
    @Before
    public void addUser() {
        realm.addAccount("jas", "123456", "admin");
    }
    
    @Test
    public void testAuthentication() {
        // 构建 SecurityManager 环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm);
        // 注册 SecurityManager 并获取主题对象
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        // 用户 token
        UsernamePasswordToken token = new UsernamePasswordToken("jas", "123456");
        subject.login(token);
        
        System.out.println("用户信息认证结果：" + subject.isAuthenticated());
        System.out.println("admin 角色验证结果：" + subject.hasRole("admin"));
    }
}
