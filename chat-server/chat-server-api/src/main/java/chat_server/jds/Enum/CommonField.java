package chat_server.jds.Enum;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public enum CommonField{;


    @Value("${netty.port}")
    public static int inetPort;
}
