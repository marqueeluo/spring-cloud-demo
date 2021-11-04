package com.luo.demo.seata.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.luo.demo.sc.base.execption.MsgRuntimeException;
import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.model.entity.Account;
import com.luo.demo.seata.mapper.AccountMapper;
import com.luo.demo.seata.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author luohq
 * @since 2021-11-02
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespResult debit(String userId, BigDecimal money) {
        log.info("用户扣款，参数：userId={}, money={}", userId, money);
        int retCount = this.baseMapper.debit(userId, money);
        log.info("用户扣款，结果：", retCount);
        if (0 >= retCount) {
            throw new MsgRuntimeException("用户扣款失败！");
        }
        return RespResult.success();
    }

}
