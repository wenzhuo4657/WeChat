package chat_server.jds.domain.user.model;

  /**
     *  des: 客户端用户发来某个请求需要返回其他用户信息时使用，
   *  其中字段status表示查询用户和客户端用户之间的关系，且注意，该类在协议包中也有字段相似的类定义
     * */
public class LuckUserInfo {

    private String userId;       //用户ID
    private String userNickName; //用户昵称
    private String userHead;     //用户头像
    private Integer status;      // 状态；0添加、1[保留]、2已添加

    public LuckUserInfo() {
    }

    public LuckUserInfo(String userId, String userNickName, String userHead) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
    }

    public LuckUserInfo(String userId, String userNickName, String userHead, Integer status) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
        this.status = status;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
