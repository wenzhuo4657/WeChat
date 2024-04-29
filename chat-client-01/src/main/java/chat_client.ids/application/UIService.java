package chat_client.ids.application;

import com.ly.ui.view.chat.IChatMethod;
import com.ly.ui.view.login.ILoginMethod;

/**
 * @className: UIService
 * @author: wenzhuo4657
 * @date: 2024/4/29 17:36
 * @Version: 1.0
 * @description:
 */
public class UIService {

    private ILoginMethod login;
    private IChatMethod chat;

    public ILoginMethod getLogin() {
        return login;
    }

    public void setLogin(ILoginMethod login) {
        this.login = login;
    }

    public IChatMethod getChat() {
        return chat;
    }

    public void setChat(IChatMethod chat) {
        this.chat = chat;
    }
}