package chat_server.jds.infrastructure.repository;

import chat_server.jds.domain.user.model.UserInfo;
import chat_server.jds.domain.user.repository.IUserRepository;
import chat_server.jds.infrastructure.common.BeancopyUtils;
import chat_server.jds.infrastructure.dao.IUserDao;
import chat_server.jds.infrastructure.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public String selectUserPasswordByUserId(String userId) {
        return userDao.selectUserPasswordByUserId(userId);
    }

    @Override
    public UserInfo selectUserInfo(String userId) {
        User user=userDao.selectUserById(userId);
        return BeancopyUtils.copyBean(user,UserInfo.class);
    }
}