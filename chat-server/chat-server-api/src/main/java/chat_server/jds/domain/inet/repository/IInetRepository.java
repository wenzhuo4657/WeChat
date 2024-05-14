package chat_server.jds.domain.inet.repository;

import chat_server.jds.domain.inet.model.ChannelUserInfo;
import chat_server.jds.domain.inet.model.ChannelUserReq;

import java.util.List;

  /**
     *  des: 资源库定义，
     * */
public interface IInetRepository {

      /**
         *  des: 查询好友数量，非在线数量，仅仅从数据库层面查询
         * */
    Long queryChannelUserCount(ChannelUserReq req);

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);

}