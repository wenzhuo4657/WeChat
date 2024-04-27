package chat_client.ids.infrastructure.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: BeanUtil
 * @author: wenzhuo4657
 * @date: 2024/4/27 19:20
 * @Version: 1.0
 * @description:
 */
public class BeanUtil {
    private static Map<String,Object> cache = new ConcurrentHashMap<>();

    public static synchronized void addBean(String key,Object value){
        cache.put(key, value);
    }

      /**
       *   des: clazz仅仅起到定义T的作用,
       *   且注意，此处由于客户端仅仅在初始化时添加一次channl，
       *   所以如果有getBean("channel",Clannel.class)的调用，其返回值一定为本客户端与服务端的channel通道链接
         * */
    public  static<T> T getBean(String key,Class<T> clazz) {
        return (T)cache.get(key);
    }
}