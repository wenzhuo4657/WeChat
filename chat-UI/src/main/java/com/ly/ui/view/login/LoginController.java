package com.ly.ui.view.login;


import com.ly.ui.view.login.Ilogin.ILoginEvent;
import com.ly.ui.view.login.Ilogin.ILoginMethod;
import com.ly.ui.view.login.InitSupport.LoginInit;

public class LoginController extends LoginInit implements ILoginMethod {


    private LoginView loginView;
    private LoginEventDefine loginEventDefine;

    public LoginController(ILoginEvent loginEvent) {
        super(loginEvent);

    }

    @Override
    public void initView() {
        loginView = new LoginView(this, loginEvent);
    }

    @Override
    public void initEventDefine() {
        loginEventDefine = new LoginEventDefine(this, loginEvent, this);
    }

    @Override
    public void doShow() {
        super.show();
    }





}
