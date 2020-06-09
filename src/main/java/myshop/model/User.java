package myshop.model;

import static myshop.model.Role.RoleName.USER;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
    private Long id;
    private String login;
    private String password;
    private HashSet<Role.RoleName> roles = new HashSet<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        roles.add(USER);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public HashSet<Role.RoleName> getRoles() {
        return roles;
    }

    public String getRolesToString() {
        StringBuilder sb = new StringBuilder();
        for (Role.RoleName roleName : roles) sb.append(roleName).append(" ");
        return sb.toString().substring(0, sb.length() - 1);
    }

    public void setRoles(HashSet<Role.RoleName> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
