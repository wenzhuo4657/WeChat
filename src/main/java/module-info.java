module netty_javafx.project.wechat {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens netty_javafx.project.wechat to javafx.fxml;
    exports netty_javafx.project.wechat;
}