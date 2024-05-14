package chat_server.jds.infrastructure.repository;

import chat_server.jds.domain.inet.model.ChannelUserInfo;
import chat_server.jds.domain.inet.model.ChannelUserReq;
import chat_server.jds.domain.inet.repository.IInetRepository;
import chat_server.jds.infrastructure.dao.IUserDao;
import chat_server.jds.infrastructure.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: InetRepository
 * @author: wenzhuo4657
 * @date: 2024/5/14 11:18
 * @Version: 1.0
 * @description:
 */
@Service("inetRepository")
public class InetRepository implements IInetRepository {
    @Autowired
    private IUserDao userDao;
    @Override
    public Long queryChannelUserCount(ChannelUserReq req) {

        return userDao.queryChannelUserCount(req);
    }

    @Override
    public List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req) {
        List<ChannelUserInfo> channelUserInfoList =new ArrayList<>();
        List<User> userList =userDao.queryChannelUserList(req);
        for (User user:userList){
            ChannelUserInfo channelUserInfo = new ChannelUserInfo();
            channelUserInfo.setUserId(user.getUserId());
            channelUserInfo.setUserNickName(user.getUserNickName());
            channelUserInfo.setUserHead(user.getUserHead());
            channelUserInfoList.add(channelUserInfo);
        }
        return channelUserInfoList;
    }
}