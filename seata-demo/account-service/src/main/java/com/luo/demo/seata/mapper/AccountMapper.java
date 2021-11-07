package com.luo.demo.seata.mapper;

import com.luo.demo.seata.model.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author luohq
 * @since 2021-11-02
 */
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 用户扣款
     *
     * @param userId
     * @param money
     * @return
     */
    int debit(@Param("userId") String userId, @Param("money") BigDecimal money);
}
