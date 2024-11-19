package com.heima.gateway.filter;

import com.heima.gateway.config.AuthProperties;
import com.heima.gateway.utils.JwtTool;
import com.hmall.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Author:  cxw
 * Date:  2024/11/19 16:15
 */
@RequiredArgsConstructor
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;

    private final JwtTool jwtTool;

    private final AntPathMatcher antpathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取request
        ServerHttpRequest request = exchange.getRequest();
        // 2. 判断是否需要拦截
        if (isExclude(request.getPath().toString())){
            return chain.filter(exchange);
        }
        // 3. 获取token
        String token = null;
        List<String> headers = request.getHeaders().get("Authorization");
        if (headers != null && !headers.isEmpty()){
            token = headers.get(0);
        }
        // 4. 校验并解析token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        }catch (UnauthorizedException e){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        String userInfo = userId.toString();
        // 5. 传递用户信息
        ServerWebExchange build = exchange.mutate().request(builder -> builder.header("user-info", userInfo)).build();
        // 6. 放行
        return chain.filter(build);
    }

    private boolean isExclude(String path) {
        for (String excludePath : authProperties.getExcludePaths()){
            if (antpathMatcher.match(excludePath,path)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
