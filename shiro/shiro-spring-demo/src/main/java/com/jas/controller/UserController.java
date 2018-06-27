package com.jas.controller;

import com.jas.bean.ShiroInfo;
import com.jas.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jas
 * @create 2018-06-26 17:02
 **/
@Controller
public class UserController {
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ShiroInfo userLogin(User user) {
        // 获得主题对象
        Subject subject = SecurityUtils.getSubject();
        // 用来封装用户的登录状态、角色信息、权限信息，最后以 JSON 数据返回
        ShiroInfo shiroInfo = new ShiroInfo();
        Set<String> rolesSet = new HashSet<>();
        Set<String> permissionsSet = new HashSet<>();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            shiroInfo.setStatus("登录成功！");
        } catch (AuthenticationException e) {
            shiroInfo.setStatus("用户登录失败");
            return shiroInfo;
        }

        // 下面将角色信息与权限信息验证通过后封装在 ShiroInfo 对象中
        if (subject.hasRole("admin")) {
            rolesSet.add("admin");
        } 
        if (subject.hasRole("user")) {
            rolesSet.add("user");
        }
        if (rolesSet.size() > 0){
            shiroInfo.setRoles(rolesSet);
        }
        
        if (subject.isPermitted("update")) {
            permissionsSet.add("update");
        }
        if (subject.isPermitted("delete")) {
            permissionsSet.add("delete");
        }
        // 模拟一个没有的权限
        if (!subject.isPermitted("add")) {
            permissionsSet.add("no add permissions");
        }
        if (permissionsSet.size() > 0) {
            shiroInfo.setPermissions(permissionsSet);
        }
        
        return shiroInfo;
    }
    
    @RequiresRoles("admin")
    @ResponseBody
    @RequestMapping("/testRole")
    public String testRole() throws AuthorizationException {
        return "welcome admin!";
    }

    /**
     * 权限不够会跳到 403.jsp（在 spring-shiro.xml 的配置文件的中有配置） 页面
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/testPer")
    public String testPer() {
        return "you have update permission!";
    }
}
