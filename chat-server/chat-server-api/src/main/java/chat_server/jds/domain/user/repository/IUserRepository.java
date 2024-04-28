package chat_server.jds.domain.user.repository;

/**
 * @className: UserRepository
 * @author: wenzhuo4657
 * @date: 2024/4/28 20:01
 * @Version: 1.0
 * @description:
 */
public interface IUserRepository {
    String queryUserPassword(String userId);
}