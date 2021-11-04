package com.luo.demo.seata.service;

import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.model.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author luohq
 * @since 2021-11-02
 */
public interface IAccountService extends IService<Account> {

    /**
     * 用户扣款
     *
     * @param userId
     * @param money
     * @return
     */
    RespResult debit(String userId, BigDecimal money);
}
