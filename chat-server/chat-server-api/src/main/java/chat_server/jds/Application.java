package chat_server.jds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @className: Application
 * @author: wenzhuo4657
 * @date: 2024/4/28 13:48
 * @Version: 1.0
 * @description:
 */

@SpringBootApplication
@Configuration
@ImportResource(locations = {"classpath:spring-config.xml"})
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
    private Logger log = LoggerFactory.getLogger(Application.class);
    @Override
    public void run(String... args) throws Exception {

    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}