package agreement.server_api.protocol.common.Login;

import agreement.server_api.protocol.Command;
import agreement.server_api.protocol.Packet;
import agreement.server_api.protocol.common.Login.dto.ChatTalkDto;
import agreement.server_api.protocol.common.Login.dto.GroupsDto;
import agreement.server_api.protocol.common.Login.dto.UserFriendDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: LoginRespnse
 * @author: wenzhuo4657
 * @date: 2024/4/27 19:12
 * @Version: 1.0
 * @description:
 */
public class LoginResponse extends Packet {
    private boolean success;                    // 登陆反馈

    private String userId;                      // 用户ID

    private String userHead;                    // 用户头像

    private String userNickName;                // 用户昵称
    private List<ChatTalkDto> chatTalkList = new ArrayList<>();     // 聊天对话框数据[success is true]
    private List<GroupsDto> groupsList = new ArrayList<>();         // 群组列表
    private List<UserFriendDto> userFriendList = new ArrayList<>(); // 好友列表


    public LoginResponse(boolean success) {
        this.success = success;
    }

    public LoginResponse() {

    }

    public List<ChatTalkDto> getChatTalkList() {
        return chatTalkList;
    }

    public void setChatTalkList(List<ChatTalkDto> chatTalkList) {
        this.chatTalkList = chatTalkList;
    }

    public List<GroupsDto> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<GroupsDto> groupsList) {
        this.groupsList = groupsList;
    }

    public List<UserFriendDto> getUserFriendList() {
        return userFriendList;
    }

    public void setUserFriendList(List<UserFriendDto> userFriendList) {
        this.userFriendList = userFriendList;
    }

    @Override
    public Byte getCommand() {
        return Command.loginResponse;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
}