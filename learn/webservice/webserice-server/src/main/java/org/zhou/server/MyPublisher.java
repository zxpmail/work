package org.zhou.server;

import org.zhou.server.impl.MyServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * @author zhouxp
 * @date 2022/6/6
 * @apiNote 发布服务
 */
public class MyPublisher {
    public static void main(String[] args) {
        //指定服务url
        String url = "http://127.0.0.1:8089/myservice";
        //指定服务实现类
        MyService server = new MyServiceImpl();
        //采用命令行发布者Endpoint发布服务
        Endpoint.publish(url, server);
    }
}