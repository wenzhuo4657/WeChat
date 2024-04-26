package netty_javafx.project.wechat.view;

import netty_javafx.project.wechat.view.ILogin.ILoginEvent;
import netty_javafx.project.wechat.view.InitSupport.LoginInit;

public class LoginView {

    private LoginInit loginInit;
    private ILoginEvent loginEvent;

    public LoginView(LoginInit loginInit, ILoginEvent loginEvent) {
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
    }

}