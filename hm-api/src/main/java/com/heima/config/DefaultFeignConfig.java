package com.heima.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * Author:  cxw
 * Date:  2024/11/18 19:32
 */
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feginLoggerLevel(){
        return Logger.Level.FULL;
    }
}
