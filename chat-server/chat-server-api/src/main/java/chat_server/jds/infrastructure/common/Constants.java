package chat_server.jds.infrastructure.common;

/**
 * @className: Constants
 * @author: wenzhuo4657
 * @date: 2024/5/13 10:51
 * @Version: 1.0
 * @description: 成员内部枚举类
 */
public class Constants {

    public enum TalkType {
        Friend(0, "好友"),
        Group(1, "群组");

        private Integer code;
        private String info;

        TalkType(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }
}