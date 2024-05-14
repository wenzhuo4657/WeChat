package chat_server.jds.application;

import chat_server.jds.domain.inet.model.ChannelUserInfo;
import chat_server.jds.domain.inet.model.ChannelUserReq;
import chat_server.jds.domain.inet.model.InetServerInfo;

import java.util.List;

public interface InetService {
      /**
         *  des: 查询Netty服务端状态信息
         * */
    InetServerInfo queryNettyServerInfo();
      /**
         *  des: 查询通信用户数量
         * */
    Long queryChannelUserCount(ChannelUserReq req);
      /**
         *  des:  查询通信用户列表(具体信息)：且会判断通道是否处于连接状态
         * */
    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);

}