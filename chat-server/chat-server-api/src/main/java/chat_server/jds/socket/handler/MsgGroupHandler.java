package chat_server.jds.socket.handler;

import agreement.server_api.protocol.common.msg.MsgGroupRequest;
import agreement.server_api.protocol.common.msg.MsgGroupResponse;
import chat_server.jds.application.UserService;
import chat_server.jds.domain.user.model.ChatRecordInfo;
import chat_server.jds.domain.user.model.UserInfo;
import chat_server.jds.infrastructure.common.Constants;
import chat_server.jds.infrastructure.common.SocketChannnelUtil;
import chat_server.jds.socket.support.MyBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Objects;

/**
 * @className: MsgGroupHandler
 * @author: wenzhuo4657
 * @date: 2024/5/13 18:57
 * @Version: 1.0
 * @description:
 */
public class MsgGroupHandler extends MyBizHandler<MsgGroupRequest> {
    public MsgGroupHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, MsgGroupRequest msg) {
        ChannelGroup channelGroup = SocketChannnelUtil.getChannelGroupByTalkId(msg.getTalkId());
          /**
             *  des: 注意：
           *  这里的添加是在服务端通道连接层面添加，具体的群组信息是在数据库中，
           *  该处仅仅表明某个用户向某个群聊发送消息，但该群聊的channel可能由于服务端重启原因未建立
           *  并不意味着跳过数据库直接在服务端层面建立群调，这里仅仅是建立群聊通道连接组
             * */
        if (Objects.isNull(channelGroup)){
            SocketChannnelUtil.addChannelGroup(msg.getTalkId(), channel);
            channelGroup = SocketChannnelUtil.getChannelGroupByTalkId(msg.getTalkId());
        }
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(), msg.getTalkId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate(), Constants.TalkType.Group.getCode()));
        UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
        MsgGroupResponse msgGroupResponse = new MsgGroupResponse();
        msgGroupResponse.setTalkId(msg.getTalkId());
        msgGroupResponse.setUserId(msg.getUserId());
        msgGroupResponse.setUserNickName(userInfo.getUserNickName());
        msgGroupResponse.setUserHead(userInfo.getUserHead());
        msgGroupResponse.setMsg(msg.getMsgText());
        msgGroupResponse.setMsgType(msg.getMsgType());
        msgGroupResponse.setMsgDate(msg.getMsgDate());
        channelGroup.writeAndFlush(msgGroupResponse);

    }
}