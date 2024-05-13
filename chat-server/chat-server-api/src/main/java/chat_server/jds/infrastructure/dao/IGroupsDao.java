package chat_server.jds.infrastructure.dao;

import chat_server.jds.infrastructure.po.Groups;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IGroupsDao {


    Groups queryGroupsById(String talkId);
}
