package agreement.server_api.protocol.common;

import agreement.server_api.protocol.Command;
import agreement.server_api.protocol.Packet;

/**
 * @className: Demo02
 * @author: wenzhuo4657
 * @date: 2024/4/27 13:55
 * @Version: 1.0
 * @description:
 */
public class Demo02 extends Packet  {
    @Override
    public Byte getCommand() {
        return Command.Demo02;
    }
}