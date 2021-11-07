package com.luo.demo.seata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luo.demo.sc.base.execption.MsgRuntimeException;
import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.feign.AccountFeignClient;
import com.luo.demo.seata.mapper.OrderMapper;
import com.luo.demo.seata.model.entity.Order;
import com.luo.demo.seata.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p>
 * 订单信息 服务实现类
 * </p>
 *
 * @author luohq
 * @since 2021-11-02
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Resource
    private AccountFeignClient accountFeignClient;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespResult<Order> create(String userId, String commodityCode, Integer count) {
        //计算订单金额（假设商品单价5元）
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));

        /** 用户扣款 */
        RespResult respResult = accountFeignClient.debit(userId, orderMoney);
        log.info("RPC用户扣减余额服务，结果：{}", respResult);
        if (!RespResult.isSuccess(respResult)) {
            throw new MsgRuntimeException("RPC用户扣减余额服务失败！");
        }

        /** 创建订单 */
        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);
        log.info("保存订单信息，参数：{}", order);
        Boolean result = this.save(order);
        log.info("保存订单信息，结果：{}", result);
        if (!Boolean.TRUE.equals(result)) {
            throw new MsgRuntimeException("保存新订单信息失败!");
        }

        if ("product-3".equals(commodityCode)) {
            throw new MsgRuntimeException("异常:模拟业务异常:Order branch exception");
        }
        return RespResult.successData(order);

    }
}
