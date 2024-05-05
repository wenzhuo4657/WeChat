package chat_client.ids.event;

import agreement.server_api.protocol.common.friend.AddFriendRequest;
import agreement.server_api.protocol.common.friend.SearchFriendRequest;
import chat_client.ids.Enum.CommonField;
import chat_client.ids.infrastructure.util.BeanUtil;
import com.ly.ui.view.chat.IChatEvent;
import io.netty.channel.Channel;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Date;

/**
 * @className: ChatEvent
 * @author: wenzhuo4657
 * @date: 2024/4/27 14:22
 * @Version: 1.0
 * @description:   使用协议包向服务端提出请求，
 */
public class ChatEvent implements IChatEvent {
    @Override
    public void doQuit() {

    }

    @Override
    public void doSendMsg(String userId, String talkId, Integer talkType, String msg, Integer msgType, Date msgDate) {

    }

    @Override
    public void doEventAddTalkUser(String userId, String userFriendId) {

    }

    @Override
    public void doEventAddTalkGroup(String userId, String groupId) {

    }

    @Override
    public void doEventDelTalkUser(String userId, String talkId) {

    }


      /**
         *  des: 向服务端提出搜索好友的请求
         * */
    @Override
    public void addFriendLuck(String userId, ListView<Pane> listView) {
        Channel channel = BeanUtil.getBean(CommonField.channel, Channel.class);
        channel.writeAndFlush(( new SearchFriendRequest(userId, "")) );
    }

      /**
         *  des: 向服务端提出模糊搜索的请求
         * */
    @Override
    public void doFriendLuckSearch(String userId, String text) {
        Channel channel = BeanUtil.getBean(CommonField.channel, Channel.class);
        channel.writeAndFlush(new AddFriendRequest(userId,text));
    }

      /**
         *  des:向服务端提出添加好友的请求
         * */
    @Override
    public void doEventAddLuckUser(String userId, String friendId) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new AddFriendRequest(userId, friendId));

    }
}