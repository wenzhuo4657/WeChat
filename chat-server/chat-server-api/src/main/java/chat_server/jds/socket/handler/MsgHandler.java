package chat_server.jds.socket.handler;

import agreement.server_api.protocol.common.msg.MsgRequest;
import agreement.server_api.protocol.common.msg.MsgResponse;
import chat_server.jds.application.UserService;
import chat_server.jds.domain.user.model.ChatRecordInfo;
import chat_server.jds.infrastructure.common.Constants;
import chat_server.jds.infrastructure.common.SocketChannnelUtil;
import chat_server.jds.socket.support.MyBizHandler;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;

import java.util.Objects;

/**
 * @className: MsgHandler
 * @author: wenzhuo4657
 * @date: 2024/5/13 10:42
 * @Version: 1.0
 * @description:
 */
public class MsgHandler extends MyBizHandler<MsgRequest> {
    public MsgHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, MsgRequest msg) {
        log.info("消息处理：{}", JSON.toJSONString(msg));
//        异步写入:方法内部使用了线程池执行任务
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(),msg.getFriendId(),msg.getMsgText(),msg.getMsgType(),msg.getMsgDate()));
//        确保好友的对话框已经建立，
        userService.addTalkBoxInfo(msg.getFriendId(),msg.getUserId(), Constants.TalkType.Friend.getCode());
        Channel friendChannel = SocketChannnelUtil.getChannel(msg.getFriendId());
        if (Objects.isNull(friendChannel)){
            log.info("好友用户id:{}未登录!",msg.getFriendId());
            return;
        }
        friendChannel.writeAndFlush(new MsgResponse(msg.getUserId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate()));
    }
}