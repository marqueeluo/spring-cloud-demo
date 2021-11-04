package com.luo.demo.seata.controller;

import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.model.entity.Order;
import com.luo.demo.seata.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 业务 - controller
 *
 * @author luohq
 * @date 2021-11-3
 */
@RestController
@RequestMapping("/business")
@Slf4j
public class BusinessController {

    @Resource
    private BusinessService businessService;


    @PostMapping("/buy/at")
    RespResult<Order> handleBusinessAt(@RequestParam("userId") String userId, @RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count) {
        return businessService.handleBusinessAt(userId, commodityCode, count);
    }
}
