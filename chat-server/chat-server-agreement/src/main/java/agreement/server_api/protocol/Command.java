package agreement.server_api.protocol;

public interface Command {



    Byte Demo01 = 1;

    Byte Demo02 = 2;

    Byte Demo03 = 3;

    Byte loginRequest =5 ;//登录请求指令代码
    Byte loginResponse = 6;//登录相应指令代码

    Byte searchFriendRequest = 7;
    Byte searchFriendResponse = 8;
    Byte addFriendRequest=9;//客户端后端请求添加好友指令代码

    Byte addFriendResponse = 10;
    Byte DelTalkRequest = 11;
    Byte TalkNoticeRequest = 12;
    Byte TalkNoticeResponse = 13;




}