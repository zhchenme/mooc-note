package com.jas.bean;

import java.util.Set;

/**
 * 用来封装用户登录状态，角色，权限信息
 * 
 * @author Jas
 * @create 2018-06-27 14:50
 **/
public class ShiroInfo {
    private String status;
    private Set<String> roles;
    private Set<String> permissions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
