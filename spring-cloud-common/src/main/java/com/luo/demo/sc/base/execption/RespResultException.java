package com.luo.demo.sc.base.execption;

import com.luo.demo.sc.base.enums.RespCodeEnum;
import com.luo.demo.sc.base.model.result.RespResult;

import java.io.Serializable;

/**
 * 响应结果 - 异常（带有响应码及提示信息）
 *
 * @author luohq
 * @date 2018/8/1
 */
public class RespResultException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 4097787455795205493L;

    /**
     * 响应结果
     */
    private RespResult respResult;

    public RespResultException(RespCodeEnum respCode, String msg) {
        this(respCode, msg, null);
    }

    public RespResultException(RespCodeEnum respCode, String msg, Throwable throwable) {
        this(new RespResult(respCode.getCode(), msg), throwable);
    }

    public RespResultException(RespResult respResult) {
        this(respResult, null);
    }

    public RespResultException(RespResult respResult, Throwable throwable) {
        super(throwable);
        this.respResult = respResult;
    }


    public RespResult getRespResult() {
        return respResult;
    }
}
