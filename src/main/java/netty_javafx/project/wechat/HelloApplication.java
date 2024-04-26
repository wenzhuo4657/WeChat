package netty_javafx.project.wechat;

import javafx.application.Application;
import javafx.stage.Stage;
import netty_javafx.project.wechat.view.ILogin.ILoginEvent;
import netty_javafx.project.wechat.view.ILogin.ILoginMethod;
import netty_javafx.project.wechat.view.LoginController;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ILoginMethod login = new LoginController(
                new ILoginEvent() {
                    @Override
                    public void doLoginCheck(String userId, String userPassword) {
                        System.out.println("登陆 userId：" + userId + "userPassword：" + userPassword);
                    }
                });
//注意此处使用匿名内部类的方式实现了登录事件
        login.doShow();
    }

    public static void main(String[] args) {
        launch();
    }
}