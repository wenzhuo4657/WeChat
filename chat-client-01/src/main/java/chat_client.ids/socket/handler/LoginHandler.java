package chat_client.ids.socket.handler;

import agreement.server_api.protocol.common.Login.LoginResponse;
import chat_client.ids.application.UIService;
import com.alibaba.fastjson.JSON;
import com.ly.ui.view.chat.IChatMethod;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import javafx.application.Platform;

/**
 * @className: LoginHandler
 * @author: wenzhuo4657
 * @date: 2024/4/29 17:34
 * @Version: 1.0
 * @description:  对于本项目的客户端来说只有接收响应，发出请求，不会出现发出响应，接收请求的局面
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginResponse> {
    private UIService uiService;


    public LoginHandler(UIService uiService) {
        this.uiService = uiService;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponse msg) throws Exception {
        System.out.println("\r\n> msg handler ing ...");
        System.out.println("消息内容：" + JSON.toJSONString(msg));
        if (!msg.isSuccess()){
            System.out.println("登录失败！！！");
            return;
        }

        Platform.runLater(new Runnable()
        {
            @Override
            public void run() {
                uiService.getLogin().doLoginSuccess();
                IChatMethod chat= uiService.getChat();
                chat.setUserInfo(msg.getUserId(),msg.getUserNickName(),msg.getUserHead());
            }
        });
    }
}