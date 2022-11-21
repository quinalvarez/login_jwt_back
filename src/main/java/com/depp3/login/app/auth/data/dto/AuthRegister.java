package com.depp3.login.app.auth.data.dto;

import com.depp3.login.app.role.data.entity.Role;
import java.util.Set;

public class AuthRegister {

    private String username;
    private String email;
    private String password;
    private Boolean isActive;
    private Set<Role> roles;

    public AuthRegister() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
