package chat_server.jds.socket;

import agreement.server_api.codec.ObjDecoder;
import agreement.server_api.codec.ObjEncoder;
import chat_server.jds.application.UserService;
import chat_server.jds.socket.handler.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: MyChannelInitializer
 * @author: wenzhuo4657
 * @date: 2024/4/28 14:35
 * @Version: 1.0
 * @description:  channel通道初始化
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    private Logger log= LoggerFactory.getLogger(MyChannelInitializer.class);
    private UserService userService;

    public MyChannelInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pip = ch.pipeline();
        pip.addLast(new ObjDecoder());
        pip.addLast(new ObjEncoder());

        //业务处理
        pip.addLast(new LoginHandler(userService));
        pip.addLast(new SearchFriendHandler(userService));
        pip.addLast(new DelTalkHandler(userService));
        pip.addLast(new AddFriendHandler(userService));
        pip.addLast(new TalkNoticeHandler(userService));

        log.info("客户端+1");

    }
}