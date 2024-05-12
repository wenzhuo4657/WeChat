package chat_server.jds.infrastructure.dao;

import chat_server.jds.infrastructure.po.TalkBox;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ITalkBoxDao {


    void deleteUserTalk(String userId, String talkId);

    TalkBox queryTalkBox(String userId, String talkId);

    void addTalkBox(TalkBox talkBox);
}
