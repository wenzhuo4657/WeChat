package com.ly.ui;

import com.ly.ui.view.chat.ChatController;
import com.ly.ui.view.chat.IChat.IChatMethod;
import com.ly.ui.view.login.Ilogin.ILoginMethod;
import com.ly.ui.view.login.LoginController;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        IChatMethod chat = new ChatController();
        chat.doShow();

    }

    public static void main(String[] args) {
        launch(args);
    }

}