package org.zhou.common.model;

/**
 * @author zhouxp
 * @date 2022/6/7
 * @apiNote 通用响应枚举类
 */
public enum CommonResponseEnum  implements ResponseCode{

    SUCCESS(200,"操作成功"),
    ERROR(100, "操作失败"),
    ;
    //响应状态码
    private final Integer code;
    //响应信息
    private final String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    CommonResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResponseEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
