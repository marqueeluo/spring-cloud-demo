package com.luo.demo.seata.controller;


import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author luohq
 * @since 2021-11-02
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Resource
    private IAccountService accountService;

    /**
     * 用户扣款服务
     *
     * @param userId
     * @param money
     * @return
     */
    @PostMapping("/debit")
    RespResult debit(@RequestParam("userId") String userId, @RequestParam("money") BigDecimal money) {
        return accountService.debit(userId, money);
    }
}
