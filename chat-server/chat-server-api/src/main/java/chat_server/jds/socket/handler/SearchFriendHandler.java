package chat_server.jds.socket.handler;

import agreement.server_api.protocol.common.friend.SearchFriendRequest;
import agreement.server_api.protocol.common.friend.SearchFriendResponse;
import agreement.server_api.protocol.common.friend.dto.UserDto;
import chat_server.jds.application.UserService;
import chat_server.jds.domain.user.model.LuckUserInfo;
import chat_server.jds.infrastructure.common.BeancopyUtils;
import chat_server.jds.socket.support.MyBizHandler;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: SearchFriendHandler
 * @author: wenzhuo4657
 * @date: 2024/5/5 12:12
 * @Version: 1.0
 * @description:
 */
public class SearchFriendHandler extends MyBizHandler<SearchFriendRequest>{

    public SearchFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, SearchFriendRequest msg) {
        log.info("搜索好友请求：{}", JSON.toJSONString(msg));

        List<LuckUserInfo> luckUserInfoList =
                userService.queryFuzzyUserInfoList(msg.getUserId(), msg.getSearchKey());
        List<UserDto> userDtos = BeancopyUtils.copyBeanList(luckUserInfoList, UserDto.class);
        SearchFriendResponse resp=new SearchFriendResponse();
        resp.setList(userDtos);
        channel.writeAndFlush(resp);


    }
}