package com.mzw;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/1/11 14:44
 */
@SpringBootApplication
public class MzwApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MzwApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }
}
