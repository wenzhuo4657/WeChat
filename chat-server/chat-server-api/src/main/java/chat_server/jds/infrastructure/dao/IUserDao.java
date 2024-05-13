package chat_server.jds.infrastructure.dao;

import chat_server.jds.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserDao {

    String selectUserPasswordByUserId(String userId);

    User selectUserById(String userId);

    List<User> queryFuzzyUserList(String userId, String searchKey);

    User queryUserById(String userId);
}
