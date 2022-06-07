package org.zhou.common.model;

/**
 * @author zhouxp
 * @date 2022/6/7
 * @apiNote 统一响应代码接口
 */
public interface ResponseCode {
    Integer getCode();
    String getMessage();
}
