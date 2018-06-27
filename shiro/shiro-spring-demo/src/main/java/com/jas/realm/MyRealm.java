package com.jas.realm;

import com.jas.bean.User;
import com.jas.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author Jas
 * @create 2018-06-26 16:50
 **/
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    
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
        Set<String> rolesSet = userService.getRolesByUserName(username);
        // 根据角色获取当前用户的操作权限，这里只获取 admin 角色的权限
        Set<String> permissionsSet = null;
        if (rolesSet.contains("admin")) {
            permissionsSet = userService.getPermissionsByUserRole("admin");
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(rolesSet);
        authorizationInfo.setStringPermissions(permissionsSet);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String username = (String) token.getPrincipal();
        // 根据用户名从数据库中获取用户密码
        User user = userService.getUserByUserName(username);
        String password = user.getPassword();
        if (user == null || password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, this.getName());
        // 设置加盐策略值 -> 使用当前用户名
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return authenticationInfo;
    }
}

