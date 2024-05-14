package chat_server.jds.domain.inet.service;

import chat_server.jds.application.InetService;
import chat_server.jds.domain.inet.model.ChannelUserInfo;
import chat_server.jds.domain.inet.model.ChannelUserReq;
import chat_server.jds.domain.inet.model.InetServerInfo;
import chat_server.jds.domain.inet.repository.IInetRepository;
import chat_server.jds.infrastructure.common.SocketChannnelUtil;
import chat_server.jds.socket.NettyServer;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className: InetServiceImpl
 * @author: wenzhuo4657
 * @date: 2024/5/14 11:08
 * @Version: 1.0
 * @description:
 */
@Service("inetService")
public class InetServiceImpl  implements InetService {
    private Logger log= LoggerFactory.getLogger(InetServiceImpl.class);


    @Value("${netty.port}")
    private int port;


    @Value("${netty.ip}")
    private String ip;


    @Resource
    private NettyServer nettyServer;
    @Resource
    private IInetRepository inetRepository;


    @Override
    public InetServerInfo queryNettyServerInfo() {
        InetServerInfo inetServerInfo = new InetServerInfo();
        inetServerInfo.setIp(ip);
        inetServerInfo.setPort(port);
        inetServerInfo.setStatus(nettyServer.channel().isActive());
        return inetServerInfo;
    }

    @Override
    public Long queryChannelUserCount(ChannelUserReq req) {
        return inetRepository.queryChannelUserCount(req);
    }

    @Override
    public List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req) {
        List<ChannelUserInfo> channelUserInfoList =inetRepository.queryChannelUserList(req);
        for (ChannelUserInfo channelUserInfo : channelUserInfoList) {
            Channel channel = SocketChannnelUtil.getChannel(channelUserInfo.getUserId());
            if (null == channel || !channel.isActive()) {
                channelUserInfo.setStatus(false);
            } else {
                channelUserInfo.setStatus(true);
            }
        }
        return channelUserInfoList;

    }
}