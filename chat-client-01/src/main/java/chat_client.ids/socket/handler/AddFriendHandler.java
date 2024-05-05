package chat_client.ids.socket.handler;

import agreement.server_api.protocol.common.friend.AddFriendResponse;
import chat_client.ids.application.UIService;
import chat_client.ids.socket.support.MyBizHandler;
import com.ly.ui.view.chat.IChatMethod;
import io.netty.channel.Channel;
import javafx.application.Platform;

/**
 * @className: AddFriendHandler
 * @author: wenzhuo4657
 * @date: 2024/4/27 18:38
 * @Version: 1.0
 * @description:
 */
public class AddFriendHandler extends MyBizHandler<AddFriendResponse> {

    public AddFriendHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, AddFriendResponse msg) {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            chat.addFriendUser(true, msg.getFriendId(), msg.getFriendNickName(), msg.getFriendHead());
        });
    }
}