package chat_client.ids.event;

import agreement.server_api.protocol.common.Login.LoginRequest;
import chat_client.ids.Enum.CommonField;
import chat_client.ids.infrastructure.util.BeanUtil;
import com.ly.ui.view.login.ILoginEvent;
import io.netty.channel.Channel;


/**
 * @className: LoginEvent
 * @author: wenzhuo4657
 * @date: 2024/4/27 14:22
 * @Version: 1.0
 * @description:
 *  事件：此处为桌面后端接收桌面前端传来的数据，然后通过channel提交给服务端的的api
 */
public class LoginEvent implements ILoginEvent {

    @Override
    public void doLoginCheck(String userId, String userPassword) {
        Channel channel = BeanUtil.getBean(CommonField.channel, Channel.class);
        channel.writeAndFlush(new LoginRequest(userId, userPassword));//提交给服务端处理
        CommonField.userId=userId;
    }
}