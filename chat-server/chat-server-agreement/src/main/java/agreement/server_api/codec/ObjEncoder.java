package agreement.server_api.codec;
import agreement.server_api.Utils.ProtostuffUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import agreement.server_api.protocol.Packet;
  /**
     *  des: 对象的编码器
     * */
public class ObjEncoder extends MessageToByteEncoder<Packet> {



    @Override

    protected void encode(ChannelHandlerContext ctx, Packet in, ByteBuf out) {
        byte[] data = ProtostuffUtil.serialize(in);
        out.writeInt(data.length + 1);
        out.writeByte(in.getCommand()); //添加指令
        out.writeBytes(data);

    }



}