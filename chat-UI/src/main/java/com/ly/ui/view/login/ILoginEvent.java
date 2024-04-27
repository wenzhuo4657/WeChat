package com.ly.ui.view.login;

public interface ILoginEvent {
    /**
     * 登录认证
     * @param userId        用户ID
     * @param userPassword  用户密码
     */
    void doLoginCheck(String userId, String userPassword);
}
