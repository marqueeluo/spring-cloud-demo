package com.luo.demo.seata.service;


import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.model.entity.Order;

/**
 * @Author: heshouyou
 * @Description
 * @Date Created in 2019/1/14 17:17
 */
public interface BusinessService {

    /**
     * 创建订单 - AT事务处理
     *
     * @param userId
     * @param commodityCode
     * @param count
     * @return
     */
    RespResult<Order> handleBusinessAt(String userId, String commodityCode, Integer count);
}
