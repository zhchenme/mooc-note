package com.jas.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Jas
 * @create 2018-06-26 10:06
 **/
public class JdbcRealmTest {
    
    private DruidDataSource dataSource = new DruidDataSource();
    
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
    }
    
    @Test
    public void testJdbcRealm() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        // 自定义认证结果，角色认证，权限认证的 SQL
        String authenticatedSQL = "SELECT password FROM t_user WHERE user_name = ?";
        jdbcRealm.setAuthenticationQuery(authenticatedSQL);
        String roleSQL = "SELECT role_name FROM t_roles WHERE user_name = ?";
        jdbcRealm.setUserRolesQuery(roleSQL);
        String permissionSQL = "SELECT permission FROM t_permissions where role_name = ?";
        jdbcRealm.setPermissionsLookupEnabled(true);
        jdbcRealm.setPermissionsQuery(permissionSQL);
        // 构建 SecurityManager 环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);
        // 注册 SecurityManager 并获取主题对象
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        // 用户 token
        UsernamePasswordToken token = new UsernamePasswordToken("jas", "123456");
        subject.login(token);

        System.out.println("用户信息认证：" + subject.isAuthenticated());
        boolean[] hasRoles = subject.hasRoles(Arrays.asList(new String[]{"admin", "user"}));
        System.out.println("admin 角色验证：" + hasRoles[0]);
        System.out.println("user 角色验证：" + hasRoles[1]);
        boolean [] hasPermissions =  subject.isPermitted("delete", "update");
        System.out.println("delete 权限认证：" + hasPermissions[0]);
        System.out.println("update 权限认证：" + hasPermissions[1]);
        
    }
}
