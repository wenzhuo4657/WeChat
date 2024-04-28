package chat_server.jds.socket.support;

import chat_server.jds.application.UserService;
import chat_server.jds.infrastructure.util.SocketChannnelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: My
 * @author: wenzhuo4657
 * @date: 2024/4/28 15:52
 * @Version: 1.0
 * @description:
 */
public abstract class MyBizHandler<T> extends SimpleChannelInboundHandler<T> {
    protected Logger log= LoggerFactory.getLogger(MyBizHandler.class);
    protected UserService userService;

    public MyBizHandler(UserService userService) {
        this.userService=userService;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        channelRead(ctx.channel(),msg);
    }

    public abstract void channelRead(Channel channel, T msg);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("客户端数量+1");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("服务端异常断开", cause.getMessage());
        SocketChannnelUtil.removechannelBychannelId(ctx.channel().id().toString());
        SocketChannnelUtil.removeUserChannelOnAllChannelGroup(ctx.channel());

    }

}