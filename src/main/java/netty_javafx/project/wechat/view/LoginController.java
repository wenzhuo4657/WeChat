package netty_javafx.project.wechat.view;

import netty_javafx.project.wechat.view.ILogin.*;
import netty_javafx.project.wechat.view.ILogin.ILoginEvent;
import netty_javafx.project.wechat.view.InitSupport.LoginInit;

public class LoginController extends LoginInit implements ILoginMethod {

    private LoginView loginView;
    private LoginEventDefine loginEventDefine;

//    注意，LoginController 要求我们必须实现登录事件方法
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

    @Override
    public void doLoginError() {
        System.out.println("登陆失败，执行提示操作");
    }

    @Override
    public void doLoginSuccess() {
        System.out.println("登陆成功，执行跳转操作");
        // 关闭原窗口
        close();
    }

}