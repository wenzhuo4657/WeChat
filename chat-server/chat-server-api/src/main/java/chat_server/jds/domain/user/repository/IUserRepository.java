package chat_server.jds.domain.user.repository;

import chat_server.jds.domain.user.model.*;
import chat_server.jds.infrastructure.po.UserFriend;

import java.util.List;

/**
 * @className: UserRepository
 * @author: wenzhuo4657
 * @date: 2024/4/28 20:01
 * @Version: 1.0
 * @description:
 */
public interface IUserRepository {
    String selectUserPasswordByUserId(String userId);

    UserInfo selectUserInfo(String userId);

    List<LuckUserInfo> queryFuzzyUserInfoList(String userId, String searchKey);

    void addUserFriend(List<UserFriend> userFriendList);

    void deleteUserTalk(String userId, String talkId);

    void addTalkBoxInfo(String userId, String talkId, Integer talkType);

    void appendChatRecord(ChatRecordInfo chatRecordInfo);

    List<TalkBoxInfo> queryTalkBoxInfoList(String userId);

    List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType);

    List<GroupsInfo> queryUserGroupInfoList(String userId);

    List<UserFriendInfo> queryUserFriendInfoList(String userId);

    List<String> queryUserGroupsIdList(String userId);
}