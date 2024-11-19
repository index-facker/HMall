package com.heima.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author:  cxw
 * Date:  2024/11/18 21:04
 */
@FeignClient("user-service")
public interface UserClient {

    @PutMapping("/money/deduct")
    public void deductMoney(@RequestParam("pw") String pw, @RequestParam("amount") Integer amount);
}
