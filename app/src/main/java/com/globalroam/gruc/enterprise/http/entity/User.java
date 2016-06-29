package com.globalroam.gruc.enterprise.http.entity;

import io.realm.RealmObject;

/**
 * Created by shang guangneng on 2016/6/21 0021.
 */

public class User extends RealmObject {
    private String username;
    private String password;
    private String email;
    private String domain;
    private String mobile;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", domain='" + domain + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
