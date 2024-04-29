package chat_server.jds.domain.user.repository;

import chat_server.jds.domain.user.model.UserInfo;

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
}