
package agreement.server_api.protocol.common.Login.dto;

import java.util.List;
import java.util.Date;

public class ChatTalkDto {

    private String talkId;      // 对话框ID
    private Integer talkType;   // 对话框类型；0好友、1群组
    private String talkName;    // 用户昵称
    private String talkHead;    // 用户头像
    private String talkSketch;  // 消息简述
    private Date talkDate;      // 消息时间

    private List<ChatRecordDto> chatRecordList;  // 聊天记录     1，好友聊天 2，群聊聊天

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public Integer getTalkType() {
        return talkType;
    }

    public void setTalkType(Integer talkType) {
        this.talkType = talkType;
    }

    public String getTalkName() {
        return talkName;
    }

    public void setTalkName(String talkName) {
        this.talkName = talkName;
    }

    public String getTalkHead() {
        return talkHead;
    }

    public void setTalkHead(String talkHead) {
        this.talkHead = talkHead;
    }

    public String getTalkSketch() {
        return talkSketch;
    }

    public void setTalkSketch(String talkSketch) {
        this.talkSketch = talkSketch;
    }

    public Date getTalkDate() {
        return talkDate;
    }

    public void setTalkDate(Date talkDate) {
        this.talkDate = talkDate;
    }

    public List<ChatRecordDto> getChatRecordList() {
        return chatRecordList;
    }

    public void setChatRecordList(List<ChatRecordDto> chatRecordList) {
        this.chatRecordList = chatRecordList;
    }
}
