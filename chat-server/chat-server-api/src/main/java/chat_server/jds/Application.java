package chat_server.jds;

import chat_server.jds.socket.NettyServer;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Observable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @className: Application
 * @author: wenzhuo4657
 * @date: 2024/4/28 13:48
 * @Version: 1.0
 * @description:
 */

@SpringBootApplication
@Configuration
@ImportResource(locations = {"classpath:spring-config.xml"})
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
    private Logger log = LoggerFactory.getLogger(Application.class);

    @Resource
    private NettyServer nettyServer;
    @Override
    public void run(String... args) throws Exception {
        log.info("NettyServer启动服务开始，port:7397");
        Future<Channel> future= Executors.newFixedThreadPool(2).submit(nettyServer);
        Channel channel =future.get();
        if (Objects.isNull(channel)){
            throw  new RuntimeException("服务端启动失败");
        }
        while (!channel.isActive()) {
            logger.info("NettyServer启动服务 ...");
            Thread.sleep(500);
        }
        log.info("NettyServer启动服务完成 {}", channel.localAddress());
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}