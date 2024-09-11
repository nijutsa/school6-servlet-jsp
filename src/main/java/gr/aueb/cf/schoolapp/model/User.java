package gr.aueb.cf.schoolapp.model;

import gr.aueb.cf.schoolapp.core.RoleType;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private RoleType roleType;

    public User() {
    }

    public User(Integer userId, String username, String password, RoleType roleType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roleType = roleType;
    }





    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleType=" + roleType +
                '}';
    }
}
