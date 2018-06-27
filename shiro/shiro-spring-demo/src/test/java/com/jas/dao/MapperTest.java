package com.jas.dao;

import com.jas.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * @author Jas
 * @create 2018-06-27 9:35
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-mybatis.xml" })
public class MapperTest {
    
    @Autowired
    private UserMapper userMapper;
    
    @Test
    public void getUserByUserNameTest() {
        User user = userMapper.getUserByUserName("jas");
        System.out.println(user);
    }
    
    @Test
    public void getRolesByUserNameTest() {
        Set<String> rolesSet = userMapper.getRolesByUserName("jas");
        System.out.println(rolesSet.size());
    }
    
    @Test
    public void getPermissionsByUserRoleTest() {
        Set<String> permissionsSet = userMapper.getPermissionsByUserRole("admin");
        System.out.println(permissionsSet.size());
    }
}
