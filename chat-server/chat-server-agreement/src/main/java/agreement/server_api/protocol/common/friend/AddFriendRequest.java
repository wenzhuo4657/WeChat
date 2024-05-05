package agreement.server_api.protocol.common.friend;

import agreement.server_api.protocol.Command;
import agreement.server_api.protocol.Packet;

public class AddFriendRequest extends Packet {

    private String userId;    // 用户ID[自己的ID]
    private String friendId;  // 好友ID

    public AddFriendRequest() {
    }

    public AddFriendRequest(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    @Override
    public Byte getCommand() {
        return Command.addFriendRequest;
    }
}