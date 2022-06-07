package org.zhou.server;

import org.zhou.server.model.R;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;

/**
 * @author zhouxp
 * @date 2022/6/7
 * @apiNote
 */
@WebService(name = "Login",// 定义Port名称
        serviceName = "MyService", // 修改WebService服务名称
        targetNamespace = "http://cn.piesat.ws/my"// 定义命名空间，默认为倒置的包名
)
public interface MyService {
    /**
     * 提供一个对外公开的服务
     * @param userId 用户ID
     * @param password 密码
     * @return
     */
    @WebMethod
    R authorization(@WebParam(name = "userId") String userId,
                    @WebParam(name = "password") String password) throws IOException;
}
