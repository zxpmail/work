package org.zhou.common.model;

/**
 * @author zhouxp
 * @date 2022/6/7
 * @apiNote 通用返回值
 */
public class R {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private Object data;

    public R(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public R() {

    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static R ok(String message){
        return new R(CommonResponseEnum.SUCCESS.getCode(),message,null);
    }
    public static R ok(Object data){
        return new R(CommonResponseEnum.SUCCESS.getCode(),CommonResponseEnum.SUCCESS.getMessage(),data);
    }

    public static R ok(){
        return new R(CommonResponseEnum.SUCCESS.getCode(),CommonResponseEnum.SUCCESS.getMessage(),null);
    }

    public static R error(){
        return new R(CommonResponseEnum.ERROR.getCode(),CommonResponseEnum.ERROR.getMessage(),null);
    }

    public static R error(String msg){
        return new R(CommonResponseEnum.ERROR.getCode(),msg,null);
    }
    public static R error(ResponseCode responseCode){
        return new R(responseCode.getCode(),responseCode.getMessage(),null);
    }
}
