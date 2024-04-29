package chat_server.jds.socket;

import chat_server.jds.Enum.CommonField;
import chat_server.jds.application.UserService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @className: NettyServer
 * @author: wenzhuo4657
 * @date: 2024/4/28 14:26
 * @Version: 1.0
 * @description:
 */
@Service("nettyServer")
public class NettyServer implements Callable<Channel> {
    private Logger log= LoggerFactory.getLogger(NettyServer.class);
    private final EventLoopGroup parentGroup=new NioEventLoopGroup(2);
    private final EventLoopGroup workGroup=new NioEventLoopGroup(2);

    @Autowired
    private UserService userService;

    private  Channel channel;

    private final int inetPort = CommonField.inetPort;

    @Override
    public Channel call() throws Exception {
        ChannelFuture future = null;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new MyChannelInitializer(userService));
            future = b.bind(new InetSocketAddress(8080)).syncUninterruptibly();//注意此处的阻塞方法，不同于sync;
            /**
            * syncUninterruptibly()和sync方法都是用于等待ChannelFuture完成的操作，它们的主要区别在于处理中断的策略：
             * sync():
             * 此方法会阻塞当前线程，直到ChannelFuture完成。
             * 如果在这个等待过程中有其他线程尝试中断当前线程（通过调用Thread.interrupt()），\
             * 这个方法会抛出InterruptedException并中断该线程。这意味着调用者通常需要捕获这个异常并妥善处理中断情况。
             * syncUninterruptibly():
             * 这个方法同样会阻塞当前线程直到ChannelFuture完成，
             * 但关键不同在于它不响应中断请求。即使线程收到了中断信号，这个方法也不会抛出异常或中断线程，它会无视中断并继续等待操作完成。
             * 这对于那些不希望中断操作但又需要等待异步操作完成的场景非常有用，因为它确保了操作的原子性和完整性。
            * */
            this.channel=future.channel();

        }catch (Exception e){
            log.info("socket server start error:{}",e.getMessage());
        }finally {
            if (!Objects.isNull(future)&&future.isSuccess()){
                log.info("socket server start done. ");
            }else {
                log.info("socket server start error. ");
            }
        }
        return channel;
    }
    public void destroy() {
        if (null == channel) {
            return;
        }
        channel.close();
        parentGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }


    public Channel channel() {
        return channel;
    }

}