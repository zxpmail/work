package org.zhou.server.impl;

import org.zhou.server.MyService;
import org.zhou.server.model.R;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxp
 * @date 2022/6/6
 * @apiNote 服务实现类
 */
@WebService(endpointInterface = "org.zhou.server.MyService",
        name = "Login",// 定义Port名称
        serviceName = "MyService", // 修改WebService服务名称
        targetNamespace = "http://org.zhou.ws/my" // 定义命名空间，默认为倒置的包名
//服务实现类和接口的注解要一样全
)
public class MyServiceImpl  implements MyService {

    @Override
    public R authorization(@WebParam(name = "userId") String userId,
                           @WebParam(name = "password") String password) throws IOException {
        R r = new R();
        if ("admin".equals(userId) && "123456".equals(password)) {


            List<String> list;
            list = new ArrayList<>();
            list.add("z1");
            list.add("z2");
            r.setCode(200);
            r.setMsg("成功");
            r.setData(list);
        }else{
            r.setCode(500);
            r.setMsg("失败");
        }
        return r;

    }
}
