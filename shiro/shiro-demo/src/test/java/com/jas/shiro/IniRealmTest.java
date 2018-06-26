package com.jas.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author Jas
 * @create 2018-06-26 9:32
 **/
public class IniRealmTest {
    
    @Test
    public void testInirealm() {
        // 加载配置文件
        IniRealm iniRealm = new IniRealm("classpath:user.ini");
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);
        // 注册 SecurityManager 并获取主题对象
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        // 用户 token 
        UsernamePasswordToken token = new UsernamePasswordToken("jas", "123456");
        subject.login(token);

        System.out.println("用户信息认证结果：" + subject.isAuthenticated());
        System.out.println("admin 角色验证结果：" + subject.hasRole("admin"));
        System.out.println("user:delete 权限认证:" + subject.isPermitted("user:delete"));
    }
}
