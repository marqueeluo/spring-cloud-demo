package com.luo.demo.seata.controller;


import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.model.entity.Account;
import com.luo.demo.seata.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/detail")
    RespResult<Account> getAccount(@RequestParam Long id) {
        log.info("查询用户信息，参数：{}", id);
        Account account = accountService.getById(id);
        log.info("查询用户详情，结果：{}", account);
        return RespResult.successData(account);
    }

    @GetMapping("/detail/{id}")
    RespResult<Account> getAccountWithId(@PathVariable Long id) {
        log.info("查询用户信息，path参数：{}", id);
        Account account = accountService.getById(id);
        log.info("查询用户详情，结果：{}", account);
        return RespResult.successData(account);
    }

    @PostMapping("/add")
    RespResult addAccount(@RequestBody Account account) {
        log.info("新增用户，参数：{}", account);
        Boolean result = accountService.save(account);
        log.info("新增用户，结果：{}", result);
        return RespResult.ofBoolean(result);
    }


}
