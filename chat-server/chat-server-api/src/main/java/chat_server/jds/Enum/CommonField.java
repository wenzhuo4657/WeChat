package chat_server.jds.Enum;

import org.springframework.beans.factory.annotation.Value;

public enum CommonField{;


    @Value("${netty.port}")
    public static int inetPort;
}
