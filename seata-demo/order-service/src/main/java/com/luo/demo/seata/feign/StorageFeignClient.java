package com.luo.demo.seata.feign;

import com.luo.demo.sc.base.model.result.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存服务 - feign
 *
 * @author luohq
 * @date 2021-11-02
 */
@FeignClient(name = "storage-service")
public interface StorageFeignClient {

    @PostMapping("/storage/deduct")
    RespResult deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);
}
