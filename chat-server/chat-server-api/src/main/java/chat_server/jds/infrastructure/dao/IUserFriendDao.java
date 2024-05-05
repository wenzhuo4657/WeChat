package chat_server.jds.infrastructure.dao;

import chat_server.jds.infrastructure.po.UserFriend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface IUserFriendDao {


    UserFriend queryUserFrinedById(UserFriend req);

    void addUserFriendList(List<UserFriend> userFriendList);
}
