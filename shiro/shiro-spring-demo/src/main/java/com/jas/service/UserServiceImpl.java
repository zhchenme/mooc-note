package com.jas.service;

import com.jas.bean.User;
import com.jas.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Jas
 * @create 2018-06-27 14:22
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserName(String username) {
        if (username != null && !"".equals(username)) {
            User user = userMapper.getUserByUserName(username);
            if (user != null){
                return user;
            }
        }
        return null;
    }

    @Override
    public Set<String> getRolesByUserName(String username) {
        Set<String> userRolesSet = userMapper.getRolesByUserName(username);
        if (userRolesSet != null && userRolesSet.size() > 0) {
            return userRolesSet;
        }
        return null; 
    }

    @Override
    public Set<String> getPermissionsByUserRole(String role) {
        Set<String> userPermissions = userMapper.getPermissionsByUserRole(role);
        if (userPermissions != null &&userPermissions.size() >0) {
            return userPermissions;
        }
        return null;
    }
}
