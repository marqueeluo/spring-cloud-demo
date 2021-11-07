package com.luo.demo.sc.base.enums;

/**
 * 通用响应码
 *
 * @author luohq
 * @date 2020/10/24
 */
public enum RespCodeEnum {
    //1000 成功
    //1000+ 用户错误（参数校验、用户权限、访问限制）
    //2000+ 服务错误（系统异常）
    //3000+ 第三方服务错误（三方服务、中间件异常）
    SUCCESS(1000, "操作成功"),

    VALIDATE_FAILED(1100, "校验失败"),
    VALIDATE_EMPTY(1101, "参数为空"),
    VALIDATE_INVALID(1102, "参数无效"),
    VALIDATE_REPEAT(1103, "重复"),
    VALIDATE_NOT_EXIST(1104, "不存在"),

    LOGIN_FAILED(1201, "登录失败（用户名或密码错误）"),
    UNAUTHORIZED(1202, "暂未登录或token已经过期"),
    FORBIDDEN(1203, "没有相关权限"),

    FAILED(2000, "操作失败");

    private Integer code;
    private String message;

    RespCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * code是否相等
     *
     * @param code
     * @return
     */
    public Boolean equalCode(Integer code) {
        for (RespCodeEnum curEnum : values()) {
            if (curEnum.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据code转换为枚举类
     *
     * @param code
     * @return
     */
    public static RespCodeEnum ofCode(String code) {
        for (RespCodeEnum curEnum : values()) {
            if (curEnum.getCode().equals(code)) {
                return curEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
