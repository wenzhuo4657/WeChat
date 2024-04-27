package chat_client.ids.socket;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;

import java.util.Objects;
import java.util.concurrent.Callable;


import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


  /**
     *  des: 创建客户端并返回channel通道
     * */
public class NettyClient implements Callable<Channel>{

    private Logger log =  LoggerFactory.getLogger(NettyClient.class);

    private String inetHost = "127.0.0.1";
    private int inetPort = 7397;

    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;


      /**
         *  des: 创建客户端连接
         * */
    @Override
    public Channel call() throws Exception {
        ChannelFuture future =null;
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new MyChannelInitializer());
            future = b.connect(inetHost, inetPort).syncUninterruptibly();
            this.channel =future.channel();
        }catch (Exception e){
            log.info("socket client start error", e.getMessage());
        }finally {
            if (Objects.isNull(future) &&future.isSuccess()) {
                log.info("socket client start done. ");
            } else {
                log.error("socket client start error. ");
            }
        }

        return channel;
    }

      /**
         *  des: 如果channel！=null,则进行销毁
         * */
      public void destroy() {
          if (Objects.isNull(channel)){
              return;
          }
          channel.close();
          workerGroup.shutdownGracefully();
      }

        /**
           *  des: 判断通道是否处于连接状态
           * */
      public boolean isActive(){
        return channel.isActive();
      }
      public Channel channel(){
        return channel;
      }

}