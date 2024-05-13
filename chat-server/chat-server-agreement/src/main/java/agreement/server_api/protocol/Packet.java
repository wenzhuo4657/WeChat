package agreement.server_api.protocol;

import agreement.server_api.protocol.common.Demo01;
import agreement.server_api.protocol.common.Demo02;
import agreement.server_api.protocol.common.Demo03;
import agreement.server_api.protocol.common.Login.LoginRequest;
import agreement.server_api.protocol.common.Login.LoginResponse;
import agreement.server_api.protocol.common.friend.AddFriendRequest;
import agreement.server_api.protocol.common.friend.AddFriendResponse;
import agreement.server_api.protocol.common.friend.SearchFriendRequest;
import agreement.server_api.protocol.common.friend.SearchFriendResponse;
import agreement.server_api.protocol.common.msg.MsgRequest;
import agreement.server_api.protocol.common.msg.MsgResponse;
import agreement.server_api.protocol.common.talk.DelTalkRequest;
import agreement.server_api.protocol.common.talk.TalkNoticeRequest;
import agreement.server_api.protocol.common.talk.TalkNoticeResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Packet {



    private final static Map<Byte, Class<? extends Packet>> packetType = new ConcurrentHashMap<>();



    static {

        packetType.put(Command.Demo01, Demo01.class);

        packetType.put(Command.Demo02, Demo02.class);

        packetType.put(Command.Demo03, Demo03.class);
        packetType.put(Command.loginRequest, LoginRequest.class);
        packetType.put(Command.searchFriendRequest, SearchFriendRequest.class);
        packetType.put(Command.searchFriendResponse, SearchFriendResponse.class);
        packetType.put(Command.loginResponse, LoginResponse.class);
        packetType.put(Command.addFriendRequest, AddFriendRequest.class);
        packetType.put(Command.addFriendResponse, AddFriendResponse.class);
        packetType.put(Command.DelTalkRequest, DelTalkRequest.class);
        packetType.put(Command.TalkNoticeRequest, TalkNoticeRequest.class);
        packetType.put(Command.TalkNoticeResponse, TalkNoticeResponse.class);
        packetType.put(Command.msgRequest, MsgRequest.class);
        packetType.put(Command.msgResponse, MsgResponse.class);
    }



    public static Class<? extends Packet> get(Byte command) {
        return packetType.get(command);
    }



    /**

     * 获取协议指令

     *

     * @return 返回指令值

     */

    public abstract Byte getCommand();



}