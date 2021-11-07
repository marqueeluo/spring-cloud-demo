package com.luo.demo.sc.base.model.result;

import com.luo.demo.sc.base.enums.RespCodeEnum;

import java.util.List;

/**
 * 通用返回结果
 *
 * @author luohq
 * @date 2019/4/19
 */
public class RespResult<T> {
    /**
     * 响应码
     */
    private Integer respCode;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 结果总数
     */
    private Integer total;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 响应列表
     */
    private List<T> rows;


    public RespResult() {
    }

    public RespResult(Integer respCode, String message) {
        this.respCode = respCode;
        this.message = message;
    }

    public RespResult(Integer respCode, String message, T data, Integer total, List<T> rows) {
        this.respCode = respCode;
        this.message = message;
        this.data = data;
        this.total = total;
        this.rows = rows;
    }

    protected RespResult(Integer respCode, String message, T data) {
        this.respCode = respCode;
        this.message = message;
        this.data = data;
    }

    protected RespResult(Integer respCode, Integer total, List<T> rows) {
        this.respCode = respCode;
        this.total = total;
        this.rows = rows;
    }


    /**
     * 成功返回
     */
    public static <T> RespResult<T> success() {
        return new RespResult<T>(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), null);
    }


    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> RespResult<T> successData(T data) {
        return new RespResult<T>(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> RespResult<T> successData(T data, String message) {
        return new RespResult<T>(RespCodeEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 成功返回结果
     *
     * @param total 总记录数
     * @param rows  列表记录
     * @param <T>
     * @return
     */
    public static <T> RespResult<T> successRows(Integer total, List<T> rows) {
        return new RespResult<T>(RespCodeEnum.SUCCESS.getCode(), total, rows);
    }


    /**
     * 失败返回结果
     */
    public static <T> RespResult<T> failed() {
        return failed(RespCodeEnum.FAILED);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> RespResult<T> failed(String message) {
        return new RespResult<T>(RespCodeEnum.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> RespResult<T> failed(RespCodeEnum errorCode) {
        return new RespResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static <T> RespResult<T> failed(RespCodeEnum errorCode, String message) {
        return new RespResult<T>(errorCode.getCode(), message, null);
    }


    /**
     * 参数验证失败返回结果
     */
    public static <T> RespResult<T> validateFailed() {
        return failed(RespCodeEnum.VALIDATE_FAILED);
    }


    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> RespResult<T> validateFailed(String message) {
        return new RespResult<T>(RespCodeEnum.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 登录失败返回结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RespResult<T> loginFail(T data) {
        return new RespResult<T>(RespCodeEnum.LOGIN_FAILED.getCode(), RespCodeEnum.LOGIN_FAILED.getMessage(), data);
    }

    /**
     * 未登录返回结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RespResult<T> unauthorized(T data) {
        return new RespResult<T>(RespCodeEnum.UNAUTHORIZED.getCode(), RespCodeEnum.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> RespResult<T> forbidden(T data) {
        return new RespResult<T>(RespCodeEnum.FORBIDDEN.getCode(), RespCodeEnum.FORBIDDEN.getMessage(), data);
    }


    /**
     * 根据数字生成返回结果（数字>0则成功，否则失败）
     *
     * @param retRows
     * @param <T>
     * @return
     */
    public static <T> RespResult<T> ofRetRows(Integer retRows) {
        Boolean success = (null != retRows && 0 < retRows);
        return RespResult.ofBoolean(success);
    }

    /**
     * 根据数字生成返回结果（数字>0则成功，否则失败）
     *
     * @param retRows
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RespResult<T> ofRetRows(Integer retRows, T data) {
        RespResult respResult = RespResult.ofRetRows(retRows);
        respResult.setData(data);
        return respResult;
    }

    /**
     * 根据bool值生成返回结果(true则成功，否则失败)
     *
     * @param success
     * @param <T>
     * @return
     */
    public static <T> RespResult<T> ofBoolean(Boolean success) {
        if (Boolean.TRUE.equals(success)) {
            return RespResult.success();
        }
        return RespResult.failed();
    }


    /**
     * 是否是成功结果
     *
     * @param respResult
     * @return
     */
    public static Boolean isSuccess(RespResult respResult) {
        Boolean success = (null != respResult && RespCodeEnum.SUCCESS.equalCode(respResult.getRespCode()));
        return success;
    }

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "RespResult{" +
                "respCode=" + respCode +
                ", message='" + message + '\'' +
                ", total=" + total +
                ", data=" + data +
                ", rows=" + rows +
                '}';
    }
}
