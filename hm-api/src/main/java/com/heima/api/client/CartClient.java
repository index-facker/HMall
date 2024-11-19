package com.heima.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;


/**
 * Author:  cxw
 * Date:  2024/11/18 20:48
 */
@FeignClient("cart-service")
public interface CartClient {
    @DeleteMapping
    void deleteCartItemByIds(@RequestParam("ids") Collection<Long> ids);



}
