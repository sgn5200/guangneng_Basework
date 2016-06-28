package com.globalroam.gruc.enterprise.http.entity;

/**
 * Created by shang guangneng on 2016/6/21 0021.
 */

public class UserData {

    /**
     * code : 200
     * result : done
     * userid : SUBUSR30d371ba-377e-11e6-8030-fa163e17f4ed
     */

    private int code;
    private String result;
    private String userid;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "code=" + code +
                ", result='" + result + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
