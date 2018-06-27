package com.jas.dao;

import com.jas.bean.User;

import java.util.Set;

/**
 * @author Jas
 * @create 2018-06-27 9:34
 **/
public interface UserMapper {

    /**
     * 根据用户名获取用户密码
     * 
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 获取用户角色信息
     * 
     * @param username
     * @return
     */
    Set<String> getRolesByUserName(String username);

    /**
     * 根据用户角色获取用户权限
     * 
     * @param role
     * @return
     */
    Set<String> getPermissionsByUserRole(String role);
}
