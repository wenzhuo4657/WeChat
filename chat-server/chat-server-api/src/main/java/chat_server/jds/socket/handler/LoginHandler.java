package chat_server.jds.socket.handler;

import agreement.server_api.protocol.common.Login.LoginRequest;
import chat_server.jds.application.UserService;
import chat_server.jds.socket.support.MyBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * @className: LoginHandler
 * @author: wenzhuo4657
 * @date: 2024/4/28 15:51
 * @Version: 1.0
 * @description:
 */
public class LoginHandler  extends MyBizHandler<LoginRequest> {
    public LoginHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, LoginRequest msg) {

    }
}