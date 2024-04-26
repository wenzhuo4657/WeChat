package netty_javafx.project.wechat.view;

import netty_javafx.project.wechat.view.ILogin.ILoginEvent;
import netty_javafx.project.wechat.view.ILogin.ILoginMethod;
import netty_javafx.project.wechat.view.InitSupport.LoginInit;

//默认登录事件初始化，
public class LoginEventDefine {

    private LoginInit loginInit;
    private ILoginEvent loginEvent;
    private ILoginMethod loginMethod;

    public LoginEventDefine(LoginInit loginInit, ILoginEvent loginEvent, ILoginMethod loginMethod) {
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
        this.loginMethod = loginMethod;

        loginInit.move();
        doEventLogin();
    }




    // 事件；登陆
    private void doEventLogin() {
        loginInit.login_button.setOnAction(event -> {
            loginEvent.doLoginCheck(loginInit.userId.getText(), loginInit.userPassword.getText());
        });
    }
}