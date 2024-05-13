package chat_client.ids;

import agreement.server_api.protocol.common.Login.ReconnectRequest;
import chat_client.ids.Enum.CommonField;
import chat_client.ids.application.UIService;
import chat_client.ids.event.ChatEvent;
import chat_client.ids.event.LoginEvent;
import chat_client.ids.socket.NettyClient;
import com.ly.ui.view.chat.ChatController;
import com.ly.ui.view.chat.IChatMethod;
import com.ly.ui.view.login.ILoginMethod;
import com.ly.ui.view.login.LoginController;
import io.netty.channel.Channel;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * @className: Application
 * @author: wenzhuo4657
 * @date: 2024/4/27 14:21
 * @Version: 1.0
 * @description:
 */
public class Application extends  javafx.application.Application {
    private Logger log= LoggerFactory.getLogger(Application.class);
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    @Override
    public void start(Stage primaryStage) throws Exception {
        IChatMethod chatMethod =new ChatController(new ChatEvent());
        ILoginMethod loginMethod=new LoginController(new LoginEvent(),chatMethod);
        loginMethod.doShow();


        UIService uiService=new UIService();
        uiService.setChat(chatMethod);
        uiService.setLogin(loginMethod);

        log.info("NettyClient连接服务开始 inetHost：{} inetPort：{}", "127.0.0.1", 7397);

        NettyClient nettyClient = new NettyClient(uiService);

        Future<Channel> future = executorService.submit(nettyClient);

        Channel channel = future.get();

        if (null == channel) {
            throw new RuntimeException("netty client start error channel is null");
        }

        while (!nettyClient.isActive()) {

            log.info("NettyClient启动服务 ...");

            Thread.sleep(500);

        }

        log.info("NettyClient连接服务完成 {}", channel.localAddress());
        scheduledExecutorService.scheduleAtFixedRate(
                ()->{
                    while (!nettyClient.isActive()){
                        System.out.println("通信管道巡检：通信管道状态 " + nettyClient.isActive());
                        try {
                            System.out.println("通信管道巡检：断线重连[Begin]");
                            Channel FreakChannel =executorService.submit(nettyClient).get();
                            if (Objects.isNull(CommonField.userId)){
                                continue;
                            }
                            FreakChannel.writeAndFlush(new ReconnectRequest(CommonField.userId));
                        }catch (InterruptedException | ExecutionException e) {
                            System.out.println("通信管道巡检：断线重连[Error]");
                        }

                    }

        },3,5,TimeUnit.SECONDS);

    }

    public static void main(String[] args) {

        launch(args);

    }


}