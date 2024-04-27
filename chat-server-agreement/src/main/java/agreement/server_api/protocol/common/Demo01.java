package agreement.server_api.protocol.common;

import agreement.server_api.protocol.Command;
import agreement.server_api.protocol.Packet;

/**
 * @className: Demo01
 * @author: wenzhuo4657
 * @date: 2024/4/27 13:54
 * @Version: 1.0
 * @description:
 */
public class Demo01 extends Packet {
    @Override
    public Byte getCommand() {
        return Command.Demo01;
    }
}