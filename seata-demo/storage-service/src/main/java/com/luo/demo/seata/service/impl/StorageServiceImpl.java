package com.luo.demo.seata.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luo.demo.sc.base.execption.MsgRuntimeException;
import com.luo.demo.sc.base.model.result.RespResult;
import com.luo.demo.seata.mapper.StorageMapper;
import com.luo.demo.seata.model.entity.Storage;
import com.luo.demo.seata.service.IStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 库存信息 服务实现类
 * </p>
 *
 * @author luohq
 * @since 2021-11-02
 */
@Service
@Slf4j
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements IStorageService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespResult deduct(String commodityCode, int count) {
        log.info("扣减商品库存，参数: commodityCode={}, count={}", commodityCode, count);
        /** 模拟回滚异常 */
        if (commodityCode.equals("product-2")) {
            throw new MsgRuntimeException("异常:模拟业务异常:Storage branch exception");
        }
        /** 查询商品库存 */
        Storage storage = this.getOne(
                Wrappers.<Storage>lambdaQuery()
                        .eq(Storage::getCommodityCode, commodityCode)
        );
        log.info("查询商品库存，结果: {}", storage);
        //商品库存不存在，则直接回滚
        if (null == storage) {
            throw new MsgRuntimeException("商品库存不存在!");
        }

        /** 扣减商品库存 */
        Boolean result = this.update(
                Wrappers.<Storage>lambdaUpdate()
                        .set(Storage::getCount, storage.getCount() - count)
                        .eq(Storage::getId, storage.getId())
                        .ge(Storage::getCount, count)
        );
        log.info("修改商品库存，结果: {}", result);
        //修改商品库存失败，则直接回滚
        if (!Boolean.TRUE.equals(result)) {
            throw new MsgRuntimeException("修改商品库存失败!");
        }
        return RespResult.success();
    }

}
