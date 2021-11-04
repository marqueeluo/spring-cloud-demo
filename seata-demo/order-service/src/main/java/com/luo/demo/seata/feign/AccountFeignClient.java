package com.luo.demo.seata.feign;

import com.luo.demo.sc.base.model.result.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 用户服务 - feign
 *
 * @author luohq
 * @date 2021-11-02
 */
@FeignClient(name = "account-service")
public interface AccountFeignClient {

    @PostMapping("/account/debit")
    RespResult debit(@RequestParam("userId") String userId, @RequestParam("money") BigDecimal money);
}
