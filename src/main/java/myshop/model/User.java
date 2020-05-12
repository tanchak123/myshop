package myshop.model;

import static myshop.model.Role.RoleName.USER;

import java.util.HashSet;
import java.util.Set;

public class User {
    private static Long idNum = 0L;
    private Long id;
    private String login;
    private String password;
    private Set<Role.RoleName> roles = new HashSet<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.id = ++idNum;
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
        return "User{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + '}';
    }

    public Set<Role.RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Role.RoleName role) {
        roles.add(role);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
