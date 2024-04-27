package com.ly.ui.view.login;

public interface ILoginMethod {
    /**
     * 打开登录窗口
     */
    void doShow();

    /**
     * 登录失败
     */
    void doLoginError();

    /**
     * 登录成功，跳转聊天窗口（关闭登录窗口，打开新窗口）
     */
    void doLoginSuccess();
}
