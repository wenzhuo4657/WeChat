package chat_server.jds.domain.user.service;

import chat_server.jds.application.UserService;
import chat_server.jds.domain.user.model.*;
import chat_server.jds.domain.user.repository.IUserRepository;
import chat_server.jds.infrastructure.po.UserFriend;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className: UserServiceImpl
 * @author: wenzhuo4657
 * @date: 2024/4/28 19:49
 * @Version: 1.0
 * @description:
 */
public class UserServiceImpl implements UserService {

    @Resource
    public IUserRepository userRepository;
    @Override
    public boolean checkAuth(String userId, String userPassword) {

        return false;
    }

    @Override
    public UserInfo queryUserInfo(String userId) {
        return null;
    }

    @Override
    public List<TalkBoxInfo> queryTalkBoxInfoList(String userId) {
        return null;
    }

    @Override
    public void addTalkBoxInfo(String userId, String talkId, Integer talkType) {

    }

    @Override
    public List<UserFriendInfo> queryUserFriendInfoList(String userId) {
        return null;
    }

    @Override
    public List<GroupsInfo> queryUserGroupInfoList(String userId) {
        return null;
    }

    @Override
    public List<LuckUserInfo> queryFuzzyUserInfoList(String userId, String searchKey) {
        return null;
    }

    @Override
    public void addUserFriend(List<UserFriend> userFriendList) {

    }

    @Override
    public void asyncAppendChatRecord(ChatRecordInfo chatRecordInfo) {

    }

    @Override
    public List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType) {
        return null;
    }

    @Override
    public void deleteUserTalk(String userId, String talkId) {

    }

    @Override
    public List<String> queryUserGroupsIdList(String userId) {
        return null;
    }

    @Override
    public List<String> queryTalkBoxGroupsIdList(String userId) {
        return null;
    }
}