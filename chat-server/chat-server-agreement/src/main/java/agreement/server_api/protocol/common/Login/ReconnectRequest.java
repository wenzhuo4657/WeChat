package agreement.server_api.protocol.common.Login;

import agreement.server_api.protocol.Command;
import agreement.server_api.protocol.Packet;

/**
 * @className: ReconnectRequest
 * @author: wenzhuo4657
 * @date: 2024/5/13 20:39
 * @Version: 1.0
 * @description:
 */
public class ReconnectRequest extends Packet {
    private String userId;

    public ReconnectRequest() {
    }

    public ReconnectRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommand() {
        return Command.reconnectRequest;
    }
}