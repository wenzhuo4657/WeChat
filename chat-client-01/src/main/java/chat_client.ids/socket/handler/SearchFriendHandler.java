package chat_client.ids.socket.handler;

import agreement.server_api.protocol.common.friend.SearchFriendResponse;
import agreement.server_api.protocol.common.friend.dto.UserDto;
import chat_client.ids.application.UIService;
import chat_client.ids.socket.support.MyBizHandler;
import com.ly.ui.view.chat.IChatMethod;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

import java.util.List;
import java.util.Objects;

/**
 * @className: SearchFriendHandler
 * @author: wenzhuo4657
 * @date: 2024/5/5 14:36
 * @Version: 1.0
 * @description:
 */
public class SearchFriendHandler extends MyBizHandler<SearchFriendResponse> {



    public SearchFriendHandler(UIService uiService) {
        super(uiService);

    }


    @Override
    public void channelRead(Channel channel, SearchFriendResponse msg) {
        List<UserDto> list=msg.getList();
        if (Objects.isNull(list)||list.isEmpty()){
            return;
        }
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            for (UserDto user : list) {
                chat.addLuckFriend(user.getUserId(), user.getUserNickName(), user.getUserHead(), user.getStatus());
            }
        });

    }
}