package org.zhou.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhouxp
 * @date 2022/6/7
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R implements Serializable {
    private Integer code;
    private String msg;
    private List<String> data;
}
