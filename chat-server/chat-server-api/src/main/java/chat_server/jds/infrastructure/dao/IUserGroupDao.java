package chat_server.jds.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface IUserGroupDao {


    List<String> queryUserGroupsIdList(String userId);
}
