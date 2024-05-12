package chat_server.jds.infrastructure.repository;

import chat_server.jds.domain.user.model.LuckUserInfo;
import chat_server.jds.domain.user.model.UserInfo;
import chat_server.jds.domain.user.repository.IUserRepository;
import chat_server.jds.infrastructure.common.BeancopyUtils;
import chat_server.jds.infrastructure.dao.ITalkBoxDao;
import chat_server.jds.infrastructure.dao.IUserDao;
import chat_server.jds.infrastructure.dao.IUserFriendDao;
import chat_server.jds.infrastructure.po.TalkBox;
import chat_server.jds.infrastructure.po.User;
import chat_server.jds.infrastructure.po.UserFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @className: UserRepository
 * @author: wenzhuo4657
 * @date: 2024/4/28 20:02
 * @Version: 1.0
 * @description:
 */
@Component
public class UserRepository implements IUserRepository {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUserFriendDao userFriendDao;

    @Autowired
    private ITalkBoxDao talkBoxDao;

    @Override
    public String selectUserPasswordByUserId(String userId) {
        return userDao.selectUserPasswordByUserId(userId);
    }

    @Override
    public UserInfo selectUserInfo(String userId) {
        User user=userDao.selectUserById(userId);
        return BeancopyUtils.copyBean(user,UserInfo.class);
    }

    @Override
    public List<LuckUserInfo> queryFuzzyUserInfoList(String userId, String searchKey) {
        List<LuckUserInfo> luckUserInfoList=new ArrayList<>();

        List<User> userList=userDao.queryFuzzyUserList(userId,searchKey);

//        判断用户关系关系
        UserFriend req=new UserFriend();
        req.setUserId(userId);
        for (User user :userList){
            LuckUserInfo userInfo=new LuckUserInfo(user.getUserId(),user.getUserNickName(),user.getUserHead(),0);
            req.setUserFriendId(user.getUserId());
            if (!Objects.isNull(userFriendDao.queryUserFrinedById(req))){
                userInfo.setStatus(2);
            }
            luckUserInfoList.add(userInfo);
        }
        return luckUserInfoList;

    }

    @Override
    public void addUserFriend(List<UserFriend> userFriendList) {
        try {
            userFriendDao.addUserFriendList(userFriendList);
        } catch (DuplicateKeyException ignored) {
        }

    }

    @Override
    public void deleteUserTalk(String userId, String talkId) {
        talkBoxDao.deleteUserTalk(userId, talkId);
    }

    @Override
    public void addTalkBoxInfo(String userId, String talkId, Integer talkType) {
        try {
            if (null != talkBoxDao.queryTalkBox(userId, talkId)) {
                return;
            }
            TalkBox talkBox = new TalkBox();
            talkBox.setUserId(userId);
            talkBox.setTalkId(talkId);
            talkBox.setTalkType(talkType);
            talkBoxDao.addTalkBox(talkBox);
        } catch (DuplicateKeyException ignored) {
        }
    }
}