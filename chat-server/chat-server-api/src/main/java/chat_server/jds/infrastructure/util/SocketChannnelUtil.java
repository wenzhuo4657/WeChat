package chat_server.jds.infrastructure.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: SocketChannnelUtil
 * @author: wenzhuo4657
 * @date: 2024/4/28 16:04
 * @Version: 1.0
 * @description:  ,管理客户端实例等等
 *
 * 注意：
 * 由于addchannel()方法内部实现，我们必须保证userid不会重复
 *
 * 注意：
 * 这里并没有实现功能，仅提供基本方法，并不保存任何状态
 */
public class SocketChannnelUtil {


    private  static  final Logger log= LoggerFactory.getLogger(SocketChannnelUtil.class);
      /**
         *  des:  客户端通道连接channel实例管理
         * */
    private static Map<String, Channel> userChannel = new ConcurrentHashMap<>();

      /**
         *  des: 客户端登录id管理
         * */
    private static Map<String, String> userChannelId = new ConcurrentHashMap<>();

      /**
         *  des: 群组管理
         * */
    private static Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();


    public static void addchannel(String userId,Channel channel){
        userChannel.put(userId,channel);//此处我们必须保证userid不会重复
        userChannelId.put(channel.id().toString(),userId);//注意此处channel.id()不会重复
    }

      /**
         *  des: 删除通道连接
       *  此处可以看出userChannel、userChannelId 不一定对应，userChannelId中的用户id数量可能大于userChannel中的通道连接数量
         * */
    public static void removechannelBychannelId(String channelId) {
        String userId =  userChannelId.get(channelId);
        if (Objects.isNull(userId)) {
            return;
        }
        userChannel.remove(userId);
    }
    public static  void removeChannelByUserId(String userid){
        userChannel.remove(userid);
    }
    public static Channel getChannel(String userId) {
        return userChannel.get(userId);
    }

  /**
     *  des: 根据talkId(群号)中向群组中添加用户成员（channel）
   *  如果不存在该群号对应的群组（ ChannelGroup），则进行创建并根据talkId添加进channelGroupMap
     * */
    public synchronized static void addChannelGroup(String talkId,Channel userchannel){
        ChannelGroup channelGroup=channelGroupMap.get(talkId);
        if (Objects.isNull(channelGroup)){
            channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            channelGroupMap.put(talkId,channelGroup);
        }
        channelGroup.add(userchannel);
    }
  /**
     *  des: 删除群组中某个成员
     * */
  public synchronized  static  void removeUserChannelOnChannelGroup(String talkId,Channel userchannel){
      ChannelGroup channelGroup=channelGroupMap.get(talkId);
      if (Objects.isNull(channelGroup)){
          log.info("删除群组成员出现了无效的群号");
          return;
      }
      channelGroup.remove(userchannel);
  }
  /**
     *  des:在所有群组中删除该客户端的通道连接
   *  对于实时通信有用
     * */
    public static void removeUserChannelOnAllChannelGroup(Channel channel){
        for (ChannelGroup next : channelGroupMap.values()) {
            next.remove(channel);
        }
    }
    /**
     * 获取群组通信管道
     *
     * @param talkId 对话框ID[群号]
     * @return 群组
     */
    public static ChannelGroup getChannelGroupByTalkId(String talkId) {
        return channelGroupMap.get(talkId);
    }
}