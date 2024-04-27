package agreement.server_api.protocol.common;

import agreement.server_api.protocol.Command;
import agreement.server_api.protocol.Packet;

/**
 * @className: LoginRequest
 * @author: wenzhuo4657
 * @date: 2024/4/27 19:01
 * @Version: 1.0
 * @description:
 */
public class LoginRequest  extends Packet{

    private String userId;        // 用户ID

    private String userPassword;  // 用户密码
    @Override
    public Byte getCommand() {
        return Command.loginRequest;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}