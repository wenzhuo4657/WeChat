package chat_server.jds.socket.handler;

import agreement.server_api.protocol.common.talk.DelTalkRequest;
import chat_server.jds.application.UserService;
import chat_server.jds.socket.support.MyBizHandler;
import io.netty.channel.Channel;

public class DelTalkHandler extends MyBizHandler<DelTalkRequest> {
    public DelTalkHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, DelTalkRequest msg) {
        userService.deleteUserTalk(msg.getUserId(), msg.getTalkId());
    }
}