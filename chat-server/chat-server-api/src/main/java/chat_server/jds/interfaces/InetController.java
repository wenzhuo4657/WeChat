package chat_server.jds.interfaces;

import chat_server.jds.application.InetService;
import chat_server.jds.domain.inet.model.ChannelUserInfo;
import chat_server.jds.domain.inet.model.ChannelUserReq;
import chat_server.jds.domain.inet.model.InetServerInfo;
import chat_server.jds.infrastructure.common.EasyResult;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: InetController
 * @author: wenzhuo4657
 * @date: 2024/5/14 12:34
 * @Version: 1.0
 * @description:
 */
@RestController
public class InetController {
    private Logger log= LoggerFactory.getLogger(InetController.class);


    @Resource
    private InetService inetService;


    @RequestMapping("api/queryNettyServerInfo")
    public EasyResult  queryNettyServerInfo(){
        try {
            return EasyResult.buildEasyResultSuccess(new ArrayList<InetServerInfo>() {{
                add(inetService.queryNettyServerInfo());
            }});
        } catch (Exception e) {
            log.info("查询NettyServer失败。", e);
            return EasyResult.buildEasyResultError(e);
        }

    }

    @RequestMapping("api/queryChannelUserList")
    @ResponseBody
    public EasyResult queryChannelUserList(String json, String page, String limit) {
        try {
            log.info("查询通信管道用户信息列表开始。req：{}", json);
            ChannelUserReq req = JSON.parseObject(json, ChannelUserReq.class);
            if (null == req) req = new ChannelUserReq();
            req.setPage(page, limit);
            Long count = inetService.queryChannelUserCount(req);
            List<ChannelUserInfo> list = inetService.queryChannelUserList(req);
            log.info("查询通信管道用户信息列表完成。list：{}", JSON.toJSONString(list));
            return EasyResult.buildEasyResultSuccess(count, list);
        } catch (Exception e) {
            log.info("查询通信管道用户信息列表失败。req：{}", json, e);
            return EasyResult.buildEasyResultError(e);
        }
    }

}