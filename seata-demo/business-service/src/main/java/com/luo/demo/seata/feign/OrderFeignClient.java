package com.luo.demo.seata.feign;

import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.model.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 订单服务 - feign
 *
 * @author luohq
 * @date 2021-11-02
 */
@FeignClient(name = "order-service")
public interface OrderFeignClient {

    @PostMapping("/order/create")
    RespResult<Order> createOrder(@RequestParam("userId") String userId, @RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);
}
