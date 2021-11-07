package com.luo.demo.seata.service.impl;

import com.luo.demo.sc.base.execption.MsgRuntimeException;
import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.feign.OrderFeignClient;
import com.luo.demo.seata.feign.StorageFeignClient;
import com.luo.demo.seata.model.entity.Order;
import com.luo.demo.seata.service.BusinessService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 业务服务 - 实现类
 *
 * @author luo
 * @date 2021-11-03
 */
@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private StorageFeignClient storageFeignClient;

    @Resource
    private OrderFeignClient orderFeignClient;


    /**
     * 下单操作 - AT全局事务通过@GlobalTransctional注解发起
     *
     * @param userId
     * @param commodityCode
     * @param count
     * @return
     */
    @Override
    @GlobalTransactional
    public RespResult<Order> handleBusinessAt(String userId, String commodityCode, Integer count) {
        log.info("开始AT全局事务，XID={}", RootContext.getXID());
        /** 扣减库存 */
        log.info("RPC扣减库存，参数：commodityCode={}, count={}", commodityCode, count);
        RespResult storageResult = this.storageFeignClient.deduct(commodityCode, count);
        log.info("RPC扣减库存，结果：{}", storageResult);
        if (!RespResult.isSuccess(storageResult)) {
            throw new MsgRuntimeException("RPC扣减库存 - 返回失败结果！");
        }

        /** 创建订单 */
        log.info("RPC创建订单，参数：userId={}, commodityCode={}, count={}", userId, commodityCode, count);
        RespResult<Order> orderResult = this.orderFeignClient.createOrder(userId, commodityCode, count);
        log.info("RPC创建订单，结果：{}", orderResult);
        if (!RespResult.isSuccess(orderResult)) {
            throw new MsgRuntimeException("RPC创建订单 - 返回失败结果！");
        }
        return orderResult;
    }
}
