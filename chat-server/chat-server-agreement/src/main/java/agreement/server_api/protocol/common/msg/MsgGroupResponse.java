package agreement.server_api.protocol.common.msg;


import agreement.server_api.protocol.Command;
import agreement.server_api.protocol.Packet;

import java.util.Date;

public class MsgGroupResponse extends Packet {

    private String talkId;      // 对话框ID
    private String userId;      // 群员用户ID
    private String userNickName;// 群员用户昵称
    private String userHead;    // 群员用户头像
    private String msg;         // 群员用户发送消息内容
    private Integer msgType;     // 消息类型；0文字消息、1固定表情
    private Date msgDate;       // 群员用户发送消息时间

    @Override
    public Byte getCommand() {
        return Command.msgGroupResponse;
    }

    public MsgGroupResponse(String talkId, String userId, String userNickName, String userHead, String msg, Integer msgType, Date msgDate) {
        this.talkId = talkId;
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
        this.msg = msg;
        this.msgType = msgType;
        this.msgDate = msgDate;
    }

    public MsgGroupResponse() {
    }

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }


}