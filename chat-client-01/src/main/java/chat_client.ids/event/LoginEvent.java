package chat_client.ids.event;

import agreement.server_api.protocol.common.Login.LoginRequest;
import chat_client.ids.Enum.CommonField;
import chat_client.ids.infrastructure.util.BeanUtil;
import chat_client.ids.infrastructure.util.CacheUtil;
import com.ly.ui.view.login.ILoginEvent;
import io.netty.channel.Channel;


/**
 * @className: LoginEvent
 * @author: wenzhuo4657
 * @date: 2024/4/27 14:22
 * @Version: 1.0
 * @description:
 */
public class LoginEvent implements ILoginEvent {
    @Override
    public void doLoginCheck(String userId, String userPassword) {
        Channel channel = BeanUtil.getBean(CommonField.channel, Channel.class);
        channel.writeAndFlush(new LoginRequest(userId, userPassword));
        CacheUtil.userId=userId;

    }
}