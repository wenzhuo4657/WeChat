package agreement.server_api.protocol.common.friend;


import agreement.server_api.protocol.Command;
import agreement.server_api.protocol.Packet;

public class SearchFriendRequest extends Packet {

    private String userId;     // 用户ID
    private String searchKey;  // 搜索字段

    public SearchFriendRequest() {
    }

    public SearchFriendRequest(String userId, String searchKey) {
        this.userId = userId;
        this.searchKey = searchKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public Byte getCommand() {
        return Command.searchFriendRequest;
    }

}
