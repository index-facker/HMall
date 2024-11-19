package com.heima.gateway.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Author:  cxw
 * Date:  2024/11/19 15:47
 */

@Component
public class PrintAnyGatewayFilterFactory extends AbstractGatewayFilterFactory<PrintAnyGatewayFilterFactory.Config> {
    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter(new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String name = config.getName();
                System.out.println("name:"+name);
                String age = config.getAge();
                System.out.println("age:"+age);
                String address = config.getAddress();
                System.out.println("address:"+address);
                System.out.println("print any filter running");
                return chain.filter(exchange);
            }
        },1);
    }
    @Data
    public static class Config{
        private String name;
        private String age;
        private String address;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("name","age","address");
    }

    public PrintAnyGatewayFilterFactory() {
        super(Config.class);
    }
}
