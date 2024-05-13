package chat_client.ids.socket.handler;

import agreement.server_api.protocol.common.Login.LoginResponse;
import agreement.server_api.protocol.common.Login.dto.ChatRecordDto;
import agreement.server_api.protocol.common.Login.dto.ChatTalkDto;
import agreement.server_api.protocol.common.Login.dto.GroupsDto;
import agreement.server_api.protocol.common.Login.dto.UserFriendDto;
import chat_client.ids.application.UIService;
import com.alibaba.fastjson.JSON;
import com.ly.ui.view.chat.IChatMethod;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

import java.util.List;

/**
 * @className: LoginHandler
 * @author: wenzhuo4657
 * @date: 2024/4/29 17:34
 * @Version: 1.0
 * @description:  对于本项目的客户端来说只有接收响应，发出请求，不会出现发出响应，接收请求的局面
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginResponse> {
    private UIService uiService;


    public LoginHandler(UIService uiService) {
        this.uiService = uiService;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponse msg) throws Exception {
        System.out.println("\r\n> msg handler ing ...");
        System.out.println("消息内容：" + JSON.toJSONString(msg));
        if (!msg.isSuccess()){
            System.out.println("登录失败！！！");
            return;
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                uiService.getLogin().doLoginSuccess();
                IChatMethod chat = uiService.getChat();
                // 设置用户信息
                chat.setUserInfo(msg.getUserId(), msg.getUserNickName(), msg.getUserHead());
                // 对话框
                List<ChatTalkDto> chatTalkList = msg.getChatTalkList();
                if (null != chatTalkList) {
                    chatTalkList.forEach(talk -> {
                                chat.addTalkBox(0, talk.getTalkType(), talk.getTalkId(), talk.getTalkName(), talk.getTalkHead(), talk.getTalkSketch(), talk.getTalkDate(), true);
                                switch (talk.getTalkType()) {
                                    // 好友
                                    case 0:
                                        List<ChatRecordDto> userRecordList = talk.getChatRecordList();
                                        if (null == userRecordList || userRecordList.isEmpty()) return;
                                        for (int i = userRecordList.size() - 1; i >= 0; i--) {
                                            ChatRecordDto chatRecord = userRecordList.get(i);
                                            //  自己的消息
                                            if (0 == chatRecord.getMsgUserType()) {
                                                chat.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                                continue;
                                            }
                                            // 好友的消息
                                            if (1 == chatRecord.getMsgUserType()) {
                                                chat.addTalkMsgUserLeft(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                            }
                                        }
                                        break;
                                    // 群组
                                    case 1:
                                        List<ChatRecordDto> groupRecordList = talk.getChatRecordList();
                                        if (null == groupRecordList || groupRecordList.isEmpty()) return;
                                        for (int i = groupRecordList.size() - 1; i >= 0; i--) {
                                            ChatRecordDto chatRecord = groupRecordList.get(i);
                                            //  自己的消息
                                            if (0 == chatRecord.getMsgUserType()) {
                                                chat.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                                continue;
                                            }
                                            // 他人的消息
                                            if (1 == chatRecord.getMsgUserType()) {
                                                chat.addTalkMsgGroupLeft(chatRecord.getTalkId(), chatRecord.getUserId(), chatRecord.getUserNickName(), chatRecord.getUserHead(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                            }
                                        }
                                        break;
                                    default:
                                        break;
                                }

                            }
                    );

                }
                // 群组
                List<GroupsDto> groupsList = msg.getGroupsList();
                if (null != groupsList) {
                    groupsList.forEach(groups -> chat.addFriendGroup(groups.getGroupId(), groups.getGroupName(), groups.getGroupHead()));
                }
                // 好友
                List<UserFriendDto> userFriendList = msg.getUserFriendList();
                if (null != userFriendList) {
                    userFriendList.forEach(friend -> chat.addFriendUser(false, friend.getFriendId(), friend.getFriendName(), friend.getFriendHead()));
                }
            }
        });
    }
}