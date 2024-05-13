package chat_server.jds.infrastructure.repository;

import chat_server.jds.domain.user.model.*;
import chat_server.jds.domain.user.repository.IUserRepository;
import chat_server.jds.infrastructure.common.BeancopyUtils;
import chat_server.jds.infrastructure.common.Constants;
import chat_server.jds.infrastructure.dao.*;
import chat_server.jds.infrastructure.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static chat_server.jds.infrastructure.common.Constants.TalkType.Friend;
import static chat_server.jds.infrastructure.common.Constants.TalkType.Group;

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

    @Autowired
    private IChatRecordDao chatRecordDao;

    @Autowired
    private IGroupsDao groupsDao;

    @Autowired
    private IUserGroupDao userGroupDao;

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
              /**
                 *  des: 此处补货的异常有是插入相同的唯一主键，实际意义为某个用户和他的某个好友的对话框已经存在，所以此处直接捕获但不做其他操作，
                 * */
        }
    }

    @Override
    public void appendChatRecord(ChatRecordInfo chatRecordInfo) {
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setUserId(chatRecordInfo.getUserId());
        chatRecord.setFriendId(chatRecordInfo.getFriendId());
        chatRecord.setMsgContent(chatRecordInfo.getMsgContent());
        chatRecord.setMsgType(chatRecordInfo.getMsgType());
        chatRecord.setMsgDate(chatRecordInfo.getMsgDate());
        chatRecord.setTalkType(chatRecordInfo.getTalkType());
        chatRecordDao.appendChatRecord(chatRecord);
    }

    @Override
    public List<TalkBoxInfo> queryTalkBoxInfoList(String userId) {
        List<TalkBoxInfo> talkBoxInfoList=new ArrayList<>();
        List<TalkBox>   talkBoxList=talkBoxDao.queryTalkBoxList(userId);
        for (TalkBox talkBox:talkBoxList){
            TalkBoxInfo  talkBoxInfo=new TalkBoxInfo();
            if (Friend.getCode().equals(talkBox.getTalkType())){
                User user = userDao.queryUserById(talkBox.getTalkId());
                talkBoxInfo.setTalkType(Friend.getCode());
                talkBoxInfo.setTalkId(user.getUserId());
                talkBoxInfo.setTalkName(user.getUserNickName());
                talkBoxInfo.setTalkHead(user.getUserHead());
            }else if(Group.getCode().equals(talkBox.getTalkType())){
                Groups groups = groupsDao.queryGroupsById(talkBox.getTalkId());
                talkBoxInfo.setTalkType(Group.getCode());
                talkBoxInfo.setTalkId(groups.getGroupId());
                talkBoxInfo.setTalkName(groups.getGroupName());
                talkBoxInfo.setTalkHead(groups.getGroupHead());

            }
            talkBoxInfoList.add(talkBoxInfo);
        }
        return talkBoxInfoList;
    }

    @Override
    public List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType) {
        List<ChatRecordInfo> chatRecordInfoList = new ArrayList<>();
        List<ChatRecord> list=new ArrayList<>();

//        查询po
        if (Friend.getCode().equals(talkType)){
            list=chatRecordDao.queryUserChatRecordList(talkId,userId);
        }else  if (Group.getCode().equals(talkType)){
            list =  chatRecordDao.queryGroupsChatRecordList(talkId, userId);
        }

//        po-》info
        for (ChatRecord chatRecord : list) {
            ChatRecordInfo chatRecordInfo = new ChatRecordInfo();
            chatRecordInfo.setUserId(chatRecord.getUserId());
            chatRecordInfo.setFriendId(chatRecord.getFriendId());
            chatRecordInfo.setMsgContent(chatRecord.getMsgContent());
            chatRecordInfo.setMsgType(chatRecord.getMsgType());
            chatRecordInfo.setMsgDate(chatRecord.getMsgDate());
            chatRecordInfoList.add(chatRecordInfo);
        }
        return chatRecordInfoList;
    }

    @Override
    public List<GroupsInfo> queryUserGroupInfoList(String userId) {
        List<GroupsInfo> groupsList =new ArrayList<>();
          /**
             *  des: 这里使用索引先去查找id,提高效率，但其实可以使用子查询的方式
             * */
        List<String> groupsIdList = userGroupDao.queryUserGroupsIdList(userId);

        for (String groupsId : groupsIdList) {
            Groups groups = groupsDao.queryGroupsById(groupsId);
            GroupsInfo groupsInfo = new GroupsInfo();
            groupsInfo.setGroupId(groups.getGroupId());
            groupsInfo.setGroupName(groups.getGroupName());
            groupsInfo.setGroupHead(groups.getGroupHead());
            groupsList.add(groupsInfo);
        }
        return groupsList;

    }

    @Override
    public List<UserFriendInfo> queryUserFriendInfoList(String userId) {

        List<UserFriendInfo> userFriendInfoList = new ArrayList<>();
        List<String> friendIdList = userFriendDao.queryUserFriendIdList(userId);
        for (String friendId : friendIdList) {
            User user = userDao.queryUserById(friendId);
            UserFriendInfo friendInfo = new UserFriendInfo();
            friendInfo.setFriendId(user.getUserId());
            friendInfo.setFriendName(user.getUserNickName());
            friendInfo.setFriendHead(user.getUserHead());
            userFriendInfoList.add(friendInfo);
        }
        return userFriendInfoList;
    }

    @Override
    public List<String> queryUserGroupsIdList(String userId) {
        return userGroupDao.queryUserGroupsIdList(userId);
    }
}