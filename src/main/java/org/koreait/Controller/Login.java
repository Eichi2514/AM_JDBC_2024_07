package org.koreait.Controller;

public class Login {
    String loginChack;

    Login(){

    }

    Login(String loginChack) {
        this.loginChack = loginChack;
    }

    public String getLoginChack() {
        return loginChack;
    }

    public void setLoginChack(String loginChack) {
        this.loginChack = loginChack;
    }
}
