package chat_server.jds.socket.handler;

import agreement.server_api.protocol.common.friend.AddFriendRequest;
import agreement.server_api.protocol.common.friend.AddFriendResponse;
import chat_server.jds.application.UserService;
import chat_server.jds.domain.user.model.UserInfo;
import chat_server.jds.infrastructure.common.SocketChannnelUtil;
import chat_server.jds.infrastructure.po.UserFriend;
import chat_server.jds.socket.support.MyBizHandler;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @className: AddFriendHandler
 * @author: wenzhuo4657
 * @date: 2024/5/5 14:02
 * @Version: 1.0
 * @description:
 */
public class AddFriendHandler extends MyBizHandler<AddFriendRequest> {
    public AddFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, AddFriendRequest msg) {
        List<UserFriend>  userFriendList =new ArrayList<>();
        userFriendList.add(new UserFriend(msg.getUserId(),msg.getFriendId()));
        userFriendList.add(new UserFriend(msg.getFriendId(),msg.getUserId()));
        userService.addUserFriend(userFriendList);//数据库层面完成添加好友
//        告知客户端添加完成
        UserInfo userInfo = userService.queryUserInfo(msg.getFriendId());
        channel.writeAndFlush(new AddFriendResponse(userInfo.getUserId(),userInfo.getUserNickName(),userInfo.getUserHead()));

        Channel friendChannel = SocketChannnelUtil.getChannel(msg.getFriendId());
          if (Objects.isNull(friendChannel)){
  /**
     *  des: 此处推送给客户端的实质含义是希望客户端处理该请求，新增好友。
   *  但假若无连接或者channel连接未建立，意味着不需要客户端处理，但无须担心添加好友失效，
   *  该数据已经添加在数据库中，在登录/channel连接处将被加载
   *
     * */
              return;
          }
          UserInfo friendInfo =userService.queryUserInfo(msg.getUserId());
          friendChannel.writeAndFlush(new AddFriendResponse(friendInfo.getUserId(),friendInfo.getUserNickName(),friendInfo.getUserHead()));
    }
}