package chat_server.jds.infrastructure.dao;

import chat_server.jds.infrastructure.po.ChatRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface IChatRecordDao {


    void appendChatRecord(ChatRecord chatRecord);


      /**
         *  des:
       *  查询结果的
       *    private String userId;     // 用户ID
       *     private String friendId;   // 好友ID/TalkId/群组ID
       *     其中userId代表消息发送者，friendId表示消息的接受者
         * */
    List<ChatRecord> queryUserChatRecordList(String talkId, String userId);

    List<ChatRecord> queryGroupsChatRecordList(String talkId, String userId);
}
