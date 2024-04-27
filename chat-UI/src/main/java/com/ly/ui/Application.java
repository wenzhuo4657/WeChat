package com.ly.ui;

import com.ly.ui.view.login.ILoginMethod;
import com.ly.ui.view.login.LoginController;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Date;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ILoginMethod login = new LoginController((userId, userPassword) -> {
            System.out.println("登陆 userId：" + userId + "userPassword：" + userPassword);
        });
        login.doShow();
    }

    public static void main(String[] args) {
        launch(args);
    }

}