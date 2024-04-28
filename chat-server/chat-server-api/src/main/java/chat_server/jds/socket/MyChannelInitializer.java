package chat_server.jds.socket;

import agreement.server_api.codec.ObjDecoder;
import agreement.server_api.codec.ObjEncoder;
import chat_server.jds.application.UserService;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @className: MyChannelInitializer
 * @author: wenzhuo4657
 * @date: 2024/4/28 14:35
 * @Version: 1.0
 * @description:
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
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



    }
}