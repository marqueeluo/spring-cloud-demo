package com.luo.demo.seata.controller;


import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.service.IStorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 库存信息 前端控制器
 * </p>
 *
 * @author luohq
 * @since 2021-11-02
 */
@RestController
@RequestMapping("/storage")
public class StorageController {


    @Resource
    private IStorageService storageService;

    /**
     * 减库存
     *
     * @param commodityCode 商品代码
     * @param count         数量
     * @return
     */
    @PostMapping(path = "/deduct")
    public RespResult deduct(String commodityCode, Integer count) {
        //调用扣减库存服务
        return storageService.deduct(commodityCode, count);
    }
}
