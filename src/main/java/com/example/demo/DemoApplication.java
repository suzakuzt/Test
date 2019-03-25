package com.example.demo;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class DemoApplication extends SpringBootServletInitializer {
    //日志记录
    private static final Logger logger=Logger.getLogger(DemoApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
//        app.addListeners(new AutoTradeMatchListener());
//        app.addListeners(new TakeOutDataListener());
        app.run("--server.port=8183");
        logger.info("启动成功");
    }
}
