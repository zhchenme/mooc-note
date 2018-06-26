package com.jas.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Jas
 * @create 2018-06-26 15:09
 **/
public class MyRealmTest {
    
    @Test
    public void testMyRealm() {
        // 构建 SecurityManager 环境
        MyRealm myRealm = new MyRealm();
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(myRealm);
        // 使用加盐策略对用户密码进行 md5 处理
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        myRealm.setCredentialsMatcher(matcher);
        // 注册 SecurityManager 并获取主题对象
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        // 用户 token
        UsernamePasswordToken token = new UsernamePasswordToken("jas", "123456");
        subject.login(token);
        
        System.out.println("用户信息认证结果：" + subject.isAuthenticated());
        boolean[] hasRoles = subject.hasRoles(Arrays.asList(new String[]{"admin", "user"}));
        System.out.println("admin 角色验证：" + hasRoles[0]);
        System.out.println("user 角色验证：" + hasRoles[1]);
        boolean[] hasPermissions = subject.isPermitted("user:delete", "user:update");
        System.out.println("delete 权限认证：" + hasPermissions[0]);
        System.out.println("update 权限认证：" + hasPermissions[1]);
    }
}
