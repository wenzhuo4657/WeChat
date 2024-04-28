package chat_client.ids.socket;

import agreement.server_api.codec.ObjDecoder;
import agreement.server_api.codec.ObjEncoder;
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


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjDecoder())
                .addLast(new ObjEncoder());
//                .addLast(new MyBizHandler<Packet>());
    }
}