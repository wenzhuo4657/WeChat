package chat_server.jds.infrastructure.dao;

import chat_server.jds.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao {

    String selectUserPasswordByUserId(String userId);

    User selectUserById(String userId);
}
