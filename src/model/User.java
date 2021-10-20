package model;

import java.io.Serializable;

public class User implements Serializable {
    private String code;
    private String account;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "code='" + code + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {
    }

    public User(String code, String account, String password) {
        this.code = code;
        this.account = account;
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
