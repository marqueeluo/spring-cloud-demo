package com.luo.demo.sc.base.execption;

import java.io.Serializable;

/**
 * 带有提示信息的运行时异常
 *
 * @author luohq
 * @date 2018/8/1
 */
public class MsgRuntimeException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 4097787455795205499L;

    public MsgRuntimeException(String msg) {
        super(msg);
    }
    public MsgRuntimeException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
