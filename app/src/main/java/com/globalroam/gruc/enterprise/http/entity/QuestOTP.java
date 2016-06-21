package com.globalroam.gruc.enterprise.http.entity;

/**
 * Created by Administrator on 2015/12/15 0015.
 */
public class QuestOTP {
    private String login_id;
    private String method;
    private String provider_id;
    private String id_type;

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    @Override
    public String toString() {
        return "QuestOTP{" +
                "login_id='" + login_id + '\'' +
                ", method='" + method + '\'' +
                ", provider_id='" + provider_id + '\'' +
                ", id_type='" + id_type + '\'' +
                '}';
    }
}
