package agreement.server_api.protocol.common.friend;


import agreement.server_api.protocol.Command;
import agreement.server_api.protocol.Packet;
import agreement.server_api.protocol.common.friend.dto.UserDto;

import java.util.List;


public class SearchFriendResponse extends Packet {

    private List<UserDto> list;

    public List<UserDto> getList() {
        return list;
    }

    public void setList(List<UserDto> list) {
        this.list = list;
    }

    @Override
    public Byte getCommand() {
        return Command.searchFriendResponse;
    }
}
