package chat_server.jds.infrastructure.repository;

import chat_server.jds.domain.user.repository.IUserRepository;
import chat_server.jds.infrastructure.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @className: UserRepository
 * @author: wenzhuo4657
 * @date: 2024/4/28 20:02
 * @Version: 1.0
 * @description:
 */
public class UserRepository implements IUserRepository {

    @Autowired
    private IUserDao userDao;

    @Override
    public String queryUserPassword(String userId) {

    }
}