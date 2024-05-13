package chat_client.ids.socket.handler;

import agreement.server_api.protocol.common.msg.MsgResponse;
import chat_client.ids.application.UIService;
import chat_client.ids.socket.support.MyBizHandler;
import com.ly.ui.view.chat.IChatMethod;
import io.netty.channel.Channel;
import javafx.application.Platform;

/**
 * @className: MsgHandler
 * @author: wenzhuo4657
 * @date: 2024/5/13 11:21
 * @Version: 1.0
 * @description:
 */
public class MsgHandler extends MyBizHandler<MsgResponse> {
    public MsgHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, MsgResponse msg) {

        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            chat.addTalkMsgUserLeft(msg.getFriendId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate(), true, false, true);
        });


    }
}