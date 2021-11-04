package com.luo.demo.seata.controller;


import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.model.entity.Order;
import com.luo.demo.seata.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单信息 前端控制器
 * </p>
 *
 * @author luohq
 * @since 2021-11-02
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private IOrderService orderService;

    @PostMapping("/create")
    RespResult createOrder(@RequestParam("userId") String userId, @RequestParam("commodityCode")String commodityCode, @RequestParam("count") Integer count) {
        return orderService.create(userId, commodityCode, count);
    }

}
