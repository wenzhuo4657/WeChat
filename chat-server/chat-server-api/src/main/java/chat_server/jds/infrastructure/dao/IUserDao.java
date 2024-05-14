package chat_server.jds.infrastructure.dao;

import chat_server.jds.domain.inet.model.ChannelUserReq;
import chat_server.jds.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserDao {

    String selectUserPasswordByUserId(String userId);

    User selectUserById(String userId);

    List<User> queryFuzzyUserList(String userId, String searchKey);

    User queryUserById(String userId);


    //  wenzhuo TODO 2024/5/14 :不懂，这里为什么要用模糊查询
    Long queryChannelUserCount(ChannelUserReq req);

    List<User> queryChannelUserList(ChannelUserReq req);
}
