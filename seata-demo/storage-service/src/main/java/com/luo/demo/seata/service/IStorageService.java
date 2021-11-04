package com.luo.demo.seata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.model.entity.Storage;

/**
 * <p>
 * 库存信息 服务类
 * </p>
 *
 * @author luohq
 * @since 2021-11-02
 */
public interface IStorageService extends IService<Storage> {

    /**
     * 减库存
     *
     * @param commodityCode
     * @param count
     * @return
     */
    RespResult deduct(String commodityCode, int count);
}
