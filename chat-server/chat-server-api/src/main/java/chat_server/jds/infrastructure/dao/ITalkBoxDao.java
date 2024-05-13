package chat_server.jds.infrastructure.dao;

import chat_server.jds.infrastructure.po.TalkBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ITalkBoxDao {


    void deleteUserTalk(String userId, String talkId);

    TalkBox queryTalkBox(String userId, String talkId);

    void addTalkBox(TalkBox talkBox);

    List<TalkBox> queryTalkBoxList(@Param("userId") String userId);
}
