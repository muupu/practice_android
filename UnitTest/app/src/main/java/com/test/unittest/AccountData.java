package com.test.unittest;

/**
 * Created by qiaoda.zqd on 2016/10/28.
 */

public class AccountData {
    private boolean isLogin;
    private String userName;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
