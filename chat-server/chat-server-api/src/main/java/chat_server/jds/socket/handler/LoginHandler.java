package chat_server.jds.socket.handler;

import agreement.server_api.protocol.common.Login.LoginRequest;
import agreement.server_api.protocol.common.Login.LoginResponse;
import chat_server.jds.application.UserService;
import chat_server.jds.infrastructure.common.SocketChannnelUtil;
import chat_server.jds.socket.support.MyBizHandler;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: LoginHandler
 * @author: wenzhuo4657
 * @date: 2024/4/28 15:51
 * @Version: 1.0
 * @description:
 */
public class LoginHandler  extends MyBizHandler<LoginRequest> {

    private Logger log= LoggerFactory.getLogger(LoginHandler.class);
    public LoginHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, LoginRequest msg) {
        log.info("登录请求处理：{}", JSON.toJSON(msg));
        boolean auth=userService.checkAuth(msg.getUserId(),msg.getUserPassword());
        if (!auth){
            channel.writeAndFlush(new LoginResponse(false));
            return;
        }
        SocketChannnelUtil.BindClientAndUser(msg.getUserId(),channel);








    }
}