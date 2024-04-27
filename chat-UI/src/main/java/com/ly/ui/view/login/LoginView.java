package com.ly.ui.view.login;

import com.ly.ui.view.login.Ilogin.ILoginEvent;
import com.ly.ui.view.login.InitSupport.LoginInit;

public class LoginView {

    private LoginInit loginInit;
    private ILoginEvent loginEvent;

    public LoginView(LoginInit loginInit, ILoginEvent loginEvent) {
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
    }

}
