package com.ly.ui.view.chat;

import com.ly.ui.view.chat.IChat.IChatEvent;
import com.ly.ui.view.chat.IChat.IChatMethod;
import com.ly.ui.view.chat.InitSupport.ChatInit;
import javafx.scene.Parent;

/**
 * @className: ChatController
 * @author: wenzhuo4657
 * @date: 2024/4/27 11:22
 * @Version: 1.0
 * @description:
 */
public class ChatController  extends ChatInit implements IChatMethod {

    private ChatView chatView;
    private ChatEventDefine chatEventDefine;

    public ChatController() {
        super();
    }



    @Override
    public void doShow() {
        super.show();

    }

    @Override
    public void initView() {


    }

      /**
         *  des: 初始化各种事件
         * */
    @Override
    public void initEventDefine() {
        chatEventDefine = new ChatEventDefine(this);

    }
}