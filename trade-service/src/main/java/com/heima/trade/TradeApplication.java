package com.heima.trade;

import com.heima.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.heima.trade.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "com.heima.api.client",defaultConfiguration = DefaultFeignConfig.class)
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}