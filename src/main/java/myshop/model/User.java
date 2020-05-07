package myshop.model;

import java.util.Set;

public class User {
    private static Long idNum = 0L;
    private Long id;
    private String login;
    private String password;
    private Set<Role> roles;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.id = ++idNum;
    }

    public boolean obligation() {
        return false;
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
}
