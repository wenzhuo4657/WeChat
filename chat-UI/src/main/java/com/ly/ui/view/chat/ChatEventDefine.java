package com.ly.ui.view.chat;

import com.ly.ui.view.chat.IChat.IChatEvent;
import com.ly.ui.view.chat.IChat.IChatMethod;
import com.ly.ui.view.chat.InitSupport.ChatInit;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * @className: ChatEventDefine
 * @author: wenzhuo4657
 * @date: 2024/4/27 12:01
 * @Version: 1.0
 * @description:
 */
public class ChatEventDefine {


    private ChatInit chatInit;


    public ChatEventDefine(ChatInit chatInit) {
        this.chatInit = chatInit;


        chatInit.move();
//        min();               // 最小化
//        quit();              // 退出
        barChat();           // 聊天
        barFriend();         // 好友
    }



      /**
         *  des: 最小化
         * */
    private void min() {
        chatInit.$("group_bar_chat_min", Button.class).setOnAction(event -> {
            chatInit.setIconified(true);
        });
        chatInit.$("group_bar_friend_min", Button.class).setOnAction(event -> {
            chatInit.setIconified(true);
        });
    }

      /**
         *  des: 退出
         * */
    private void quit() {
        chatInit.$("group_bar_chat_close", Button.class).setOnAction(event -> {
            chatInit.close();
            System.exit(0);
        });
        chatInit.$("group_bar_friend_close", Button.class).setOnAction(event -> {
            chatInit.close();
            System.exit(0);
        });
    }

    /**
     *  des:  为id为bar_friend的button设置设置点击、移入、移除事件
     * */

    private void barFriend() {
        Button bar_friend = chatInit.$("bar_friend", Button.class);
        Pane group_bar_friend = chatInit.$("group_bar_friend", Pane.class);
        bar_friend.setOnAction(event -> {
            switchBarChat(chatInit.$("bar_chat", Button.class), chatInit.$("group_bar_chat", Pane.class), false);
            switchBarFriend(bar_friend, group_bar_friend, true);
        });
        bar_friend.setOnMouseEntered(event -> {
            boolean visible = group_bar_friend.isVisible();
            if (visible) return;
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_1.png')");
        });
        bar_friend.setOnMouseExited(event -> {
            boolean visible = group_bar_friend.isVisible();
            if (visible) return;
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_0.png')");
        });
    }


  /**
     *  des:  为id为bar_chat的button设置设置点击、移入、移除事件
     * */
    private void barChat() {
        Button bar_chat = chatInit.$("bar_chat", Button.class);
        Pane group_bar_chat = chatInit.$("group_bar_chat", Pane.class);
        bar_chat.setOnAction(event -> {
            switchBarChat(bar_chat, group_bar_chat, true);
            switchBarFriend(chatInit.$("bar_friend", Button.class), chatInit.$("group_bar_friend", Pane.class), false);
        });
        bar_chat.setOnMouseEntered(event -> {
            boolean visible = group_bar_chat.isVisible();
            if (visible) {
                return;
            }
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_1.png')");
        });
        bar_chat.setOnMouseExited(event -> {
            boolean visible = group_bar_chat.isVisible();
            if (visible) {
                return;
            }
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_0.png')");
        });
    }


      /**
         *  des: 切换聊天窗体和对应图标的方法
         * */
    private void switchBarChat(Button bar_chat, Pane group_bar_chat, boolean toggle) {
        if (toggle) {
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_2.png')");
            group_bar_chat.setVisible(true);
        } else {
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_0.png')");
            group_bar_chat.setVisible(false);
        }
    }


      /**
         *  des: 切换好友窗体和对应图标的方法
         * */
    private void switchBarFriend(Button bar_friend, Pane group_bar_friend, boolean toggle) {
        if (toggle) {
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_2.png')");
            group_bar_friend.setVisible(true);
        } else {
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_0.png')");
            group_bar_friend.setVisible(false);
        }
    }


}