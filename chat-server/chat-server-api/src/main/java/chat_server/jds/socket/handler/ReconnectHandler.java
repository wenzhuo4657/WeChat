package chat_server.jds.socket.handler;

import agreement.server_api.protocol.common.Login.ReconnectRequest;
import chat_server.jds.application.UserService;
import chat_server.jds.infrastructure.common.SocketChannnelUtil;
import chat_server.jds.socket.support.MyBizHandler;
import io.netty.channel.Channel;

import java.util.List;

/**
 * @className: ReconnectHandler
 * @author: wenzhuo4657
 * @date: 2024/5/13 20:48
 * @Version: 1.0
 * @description:
 */
public class ReconnectHandler extends MyBizHandler<ReconnectRequest> {
    public ReconnectHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, ReconnectRequest msg) {
          /**
             *  des：
           *  1，由于是断线重连，如果服务端没有重启，此期间的消息服务端会阻塞发送，等待客户端上线，
           *  2，数据库层面是已经将消息写入了
           *  所以此处直接将通道连接即可
             * */
        log.info("客户端断线重连处理。userId：{}", msg.getUserId());
        // 添加用户Channel
        SocketChannnelUtil.removeChannelByUserId(msg.getUserId());
        SocketChannnelUtil.BindClientAndUser(msg.getUserId(), channel);
        // 添加群组Channel
        List<String> groupsIdList = userService.queryTalkBoxGroupsIdList(msg.getUserId());
        for (String groupsId : groupsIdList) {
            SocketChannnelUtil.addChannelGroup(groupsId, channel);
        }

    }
}