package com.globalroam.gruc.enterprise.http.entity;

/**
 * Created by shang guangneng on 2016/6/22 0022.
 */

public class Token {

    /**
     * name : guangneng
     * exp : 1469080498
     * iat : 1466488498
     * type : SubUser
     * nbf : 1466488498
     * identity : SUBUSR432065b2-f746-11e5-97b6-fa163e17f4ed
     *       1469080498      1466488498
     * , exp=1469166401, iat=1466574401
     *       1469152731,"iat":1466560731
     *
     *
     */

    private String name;
    private int exp;
    private int iat;
    private String type;
    private int nbf;
    private String identity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getIat() {
        return iat;
    }

    public void setIat(int iat) {
        this.iat = iat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbf() {
        return nbf;
    }

    public void setNbf(int nbf) {
        this.nbf = nbf;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public boolean isVisiable(){
        return getExp()-getIat()>3000;
    }

    @Override
    public String toString() {
        return "Token{" +
                "name='" + name + '\'' +
                ", exp=" + exp +
                ", iat=" + iat +
                ", type='" + type + '\'' +
                ", nbf=" + nbf +
                ", identity='" + identity + '\'' +
                '}';
    }
}
