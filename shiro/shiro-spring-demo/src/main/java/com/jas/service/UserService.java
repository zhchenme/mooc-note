package com.jas.service;

import com.jas.bean.User;

import java.util.Set;

/**
 * @author Jas
 * @create 2018-06-27 14:21
 **/
public interface UserService {
    /**
     * 根据用户名获取密码
     * 
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 根据用户名获取用户角色
     * 
     * @param username
     * @return
     */
    Set<String> getRolesByUserName(String username);

    /**
     * 根据用户角色获取所有权限
     * 
     * @param role
     * @return
     */
    Set<String> getPermissionsByUserRole(String role);
}
