package com.jas.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义 Realm
 * 
 * @author Jas
 * @create 2018-06-26 14:59
 **/
public class MyRealm extends AuthorizingRealm {
    
    private Map<String, String> userMap = new HashMap<>(16);
    
    {
        userMap.put("jas", "d4cfbe5d3cf6b49653c4b641b044df76");
    }

    /**
     * 设置realm的名称
     * 
     * @param name
     */
    @Override
    public void setName(String name) {
        super.setName("myRealm");
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        // 获取当前用户的角色身份
        Set<String> rolesSet = getRolesByUserName(username);
        // 获取当前用户的操作权限
        Set<String> permissionsSet = getPermissionByUsename(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(rolesSet);
        authorizationInfo.setStringPermissions(permissionsSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
            throws AuthenticationException {
        String username = (String) token.getPrincipal();
        // 实际开发流程中从数据库中获取用户密码
        String password = getPasswordByUserName(username);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("jas", password, this.getName());
        // 设置加盐策略值
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("jas"));
        return authenticationInfo;
    }

    /**
     * 模拟从数据库中获取权限信息
     *
     * @param username
     * @return
     */
    private Set<String> getPermissionByUsename(String username) {
        Set<String> permissionsSet = new HashSet<>();
        permissionsSet.add("user:delete");
        permissionsSet.add("user:update");
        return permissionsSet;
    }

    /**
     * 模拟从从数据库中获取数据
     *
     * @param username
     * @return
     */
    private Set<String> getRolesByUserName(String username) {
        Set<String> rolesSet = new HashSet<>();
        rolesSet.add("admin");
        rolesSet.add("user");
        return rolesSet;
    }
    
    /**
     * 模拟从数据库中根据用户名获取密码
     * 
     * @param username
     * @return
     */
    private String getPasswordByUserName(String username) {
        return userMap.get(username);
    }
}
