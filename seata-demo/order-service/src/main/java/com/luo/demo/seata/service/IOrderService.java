package com.luo.demo.seata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.model.entity.Order;

/**
 * <p>
 * 订单信息 服务类
 * </p>
 *
 * @author luohq
 * @since 2021-11-02
 */
public interface IOrderService extends IService<Order> {

    /**
     * 创建订单（用户扣款、创建订单）
     *
     * @param userId
     * @param commodityCode
     * @param count
     */
    RespResult create(String userId, String commodityCode, Integer count);

}
