package chat_client.ids.socket;

import agreement.server_api.codec.ObjDecoder;
import agreement.server_api.codec.ObjEncoder;
import chat_client.ids.application.UIService;
import chat_client.ids.socket.handler.AddFriendHandler;
import chat_client.ids.socket.handler.LoginHandler;
import chat_client.ids.socket.handler.SearchFriendHandler;
import chat_client.ids.socket.handler.TalkNoticeHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @className: MyChannelInitializer
 * @author: wenzhuo4657
 * @date: 2024/4/27 18:30
 * @Version: 1.0
 * @description: channel通道初始化
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    private UIService uiService;


    public MyChannelInitializer(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjDecoder())
                .addLast(new ObjEncoder())
                .addLast(new LoginHandler(uiService))
                .addLast(new TalkNoticeHandler(uiService))
                .addLast(new AddFriendHandler(uiService))
                .addLast(new SearchFriendHandler(uiService));
    }
}