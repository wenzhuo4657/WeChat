package chat_client.ids.socket.handler;

import agreement.server_api.protocol.common.msg.MsgGroupResponse;
import chat_client.ids.application.UIService;
import chat_client.ids.socket.support.MyBizHandler;
import com.ly.ui.view.chat.IChatMethod;
import io.netty.channel.Channel;
import javafx.application.Platform;

/**
 * @className: MsgGroupHandler
 * @author: wenzhuo4657
 * @date: 2024/5/13 19:30
 * @Version: 1.0
 * @description:
 */
public class MsgGroupHandler extends MyBizHandler<MsgGroupResponse> {
    public MsgGroupHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, MsgGroupResponse msg) {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            chat.addTalkMsgGroupLeft(msg.getTalkId(), msg.getUserId(), msg.getUserNickName(), msg.getUserHead(), msg.getMsg(), msg.getMsgType(), msg.getMsgDate(), true, false, true);
        });

    }
}