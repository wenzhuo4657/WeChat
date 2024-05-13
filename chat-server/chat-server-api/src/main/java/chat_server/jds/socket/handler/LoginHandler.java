package chat_server.jds.socket.handler;

import agreement.server_api.protocol.common.Login.LoginRequest;
import agreement.server_api.protocol.common.Login.LoginResponse;
import agreement.server_api.protocol.common.Login.dto.ChatRecordDto;
import agreement.server_api.protocol.common.Login.dto.ChatTalkDto;
import agreement.server_api.protocol.common.Login.dto.GroupsDto;
import agreement.server_api.protocol.common.Login.dto.UserFriendDto;
import chat_server.jds.application.UserService;
import chat_server.jds.domain.user.model.*;
import chat_server.jds.infrastructure.common.BeancopyUtils;
import chat_server.jds.infrastructure.common.Constants;
import chat_server.jds.infrastructure.common.SocketChannnelUtil;
import chat_server.jds.socket.support.MyBizHandler;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static chat_server.jds.infrastructure.common.Constants.TalkType.Friend;
import static chat_server.jds.infrastructure.common.Constants.TalkType.Group;

/**
 * @className: LoginHandler
 * @author: wenzhuo4657
 * @date: 2024/4/28 15:51
 * @Version: 1.0
 * @description:  对于本项目的服务端而言，只有接收请求，发出响应，不会出现发出请求，接收响应的局面
 */
public class LoginHandler  extends MyBizHandler<LoginRequest> {

    private Logger log= LoggerFactory.getLogger(LoginHandler.class);
    public LoginHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, LoginRequest msg) {
        log.info("登录请求处理：{}", JSON.toJSON(msg));
        boolean auth=userService.checkAuth(msg.getUserId(),msg.getUserPassword());
        if (!auth){
            channel.writeAndFlush(new LoginResponse(false));
            return;
        }
//        1,在服务端绑定群组和用户id的channel通道
        SocketChannnelUtil.BindClientAndUser(msg.getUserId(),channel);

        List<String> groupsIdList = userService.queryUserGroupsIdList(msg.getUserId());
        for (String groupId : groupsIdList) {
            SocketChannnelUtil.addChannelGroup(groupId, channel);
        }

//        2，反馈消息：登录成功、对话框、关系列表等
        UserInfo userInfo =userService.queryUserInfo(msg.getUserId());

        LoginResponse loginResponse = new LoginResponse();


//        2.1：对话框
        List<TalkBoxInfo> talkBoxInfoList=userService.queryTalkBoxInfoList(msg.getUserId());
        for (TalkBoxInfo talkBoxInfo:talkBoxInfoList){
            ChatTalkDto chatTalk = BeancopyUtils.copyBean(talkBoxInfo, ChatTalkDto.class);
            loginResponse.getChatTalkList().add(chatTalk);//添加对话框到响应体中


//            2.1.1：对话框内消息
            if (Friend.getCode().equals(talkBoxInfo.getTalkType())){

                List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
//                查询info
                List<ChatRecordInfo> chatRecordInfoList =
                        userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), Friend.getCode());

//                 info->dto
                for (ChatRecordInfo chatRecordInfo:chatRecordInfoList){
                    ChatRecordDto chatRecordDto = new ChatRecordDto();
                    chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                    boolean msgType =msg.getUserId().equals(chatRecordInfo.getUserId());

//                    使用0/1来区分消息的发送者，这是因为此处为好友对话框
                    if (msgType) {
                        chatRecordDto.setUserId(chatRecordInfo.getUserId());
                        chatRecordDto.setMsgUserType(0); // 消息类型[0自己/1好友]
                    } else {
                        chatRecordDto.setUserId(chatRecordInfo.getFriendId());
                        chatRecordDto.setMsgUserType(1); // 消息类型[0自己/1好友]
                    }
                    chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                    chatRecordDto.setMsgType(chatRecordInfo.getMsgType());
                    chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());
                    chatRecordDtoList.add(chatRecordDto);

                }
            chatTalk.setChatRecordList(chatRecordDtoList);//设置好友对话框的消息记录
            }else if(Group.getCode().equals(talkBoxInfo.getTalkType())){
                List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
                List<ChatRecordInfo> chatRecordInfoList =
                        userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), Group.getCode());

//              info->dto
                for (ChatRecordInfo chatRecordInfo:chatRecordInfoList){
                      /**
                         *  des:    在info中userid为消息的发送者，friendId为消息的接受者，且要注意此处为群组聊天，
                         * */
                      UserInfo memberInfo =userService.queryUserInfo(chatRecordInfo.getUserId());
                      ChatRecordDto chatRecordDto = new ChatRecordDto();
                      chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                      chatRecordDto.setUserId(memberInfo.getUserId());
                      chatRecordDto.setUserNickName(memberInfo.getUserNickName());
                      chatRecordDto.setUserHead(memberInfo.getUserHead());
                      chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                      chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());



                    boolean msgType = msg.getUserId().equals(chatRecordInfo.getUserId());
                    chatRecordDto.setMsgUserType(msgType ? 0 : 1);//这里的语义为0：用户发送，1群友
                    chatRecordDto.setMsgType(chatRecordInfo.getMsgType());
                    chatRecordDtoList.add(chatRecordDto);

                }
                chatTalk.setChatRecordList(chatRecordDtoList);//设置群调对话框消息列表
            }

        }

        // 3.3 群组列表
        List<GroupsInfo> groupsInfoList = userService.queryUserGroupInfoList(msg.getUserId());
        for (GroupsInfo groupsInfo : groupsInfoList) {
            GroupsDto groups = new GroupsDto();
            groups.setGroupId(groupsInfo.getGroupId());
            groups.setGroupName(groupsInfo.getGroupName());
            groups.setGroupHead(groupsInfo.getGroupHead());
            loginResponse.getGroupsList().add(groups);
        }
        // 3.4 好友好友列表
        List<UserFriendInfo> userFriendInfoList = userService.queryUserFriendInfoList(msg.getUserId());
        for (UserFriendInfo userFriendInfo : userFriendInfoList) {
            UserFriendDto userFriend = new UserFriendDto();
            userFriend.setFriendId(userFriendInfo.getFriendId());
            userFriend.setFriendName(userFriendInfo.getFriendName());
            userFriend.setFriendHead(userFriendInfo.getFriendHead());
            loginResponse.getUserFriendList().add(userFriend);
        }
        loginResponse.setSuccess(true);

        loginResponse.setUserId(userInfo.getUserId());

        loginResponse.setUserNickName(userInfo.getUserNickName());

        loginResponse.setUserHead(userInfo.getUserHead());
        channel.writeAndFlush(loginResponse);



    }
}