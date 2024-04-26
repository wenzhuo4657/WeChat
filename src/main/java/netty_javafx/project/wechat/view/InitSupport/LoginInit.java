package netty_javafx.project.wechat.view.InitSupport;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import netty_javafx.project.wechat.view.ILogin.ILoginEvent;

import java.io.IOException;

//初始化页面
public abstract class LoginInit extends UIObject {

    private static final String RESOURCE_NAME = "/netty_javafx/project/wechat/Login.fxml";

    protected ILoginEvent loginEvent;

    public Button login_button;       // 登陆按钮
    public TextField userId;          // 用户账户窗口
    public PasswordField userPassword;// 用户密码窗口

/**
* @Author wenzhuo4657
* @Description 登录页面初始化模版方法，
* @Date 16:04 2024-04-26
* @Param [loginEvent]
* @return
**/
  protected   LoginInit(ILoginEvent loginEvent) {
        this.loginEvent = loginEvent;
        try {
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
//        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("D:/学习/编译/idea——java/WeChat/src/main/resources/netty_javafx/project/wechat/img/logo.png"));
        obtain();
        initView();
        initEventDefine();
    }

/**
* @Author wenzhuo4657
* @Description 初始化成员变量
* @Date 15:48 2024-04-26
* @Param []
* @return void
**/
    private void obtain() {
        login_button = $("login", Button.class);
        userId = $("username", TextField.class);
        userPassword = $("password", PasswordField.class);
    }

}