package org.zhou;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.zhou.common.model.R;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author zhouxp
 * @date 2022/6/7
 * @apiNote
 */
public class SoapClient {
    public static void main(String[] args) {
        //soap服务地址
        String url = "http://localhost:8089/myservice?wsdl";
        StringBuilder soapBuilder = new StringBuilder(64);
        soapBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        soapBuilder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:my=\"http://cn.piesat.ws/my\">");
        soapBuilder.append("   <soapenv:Header/>");
        soapBuilder.append("        <soapenv:Body>");
        soapBuilder.append("              <my:authorization>");
        soapBuilder.append("                     <userId>").append("admin").append("</userId>");
        soapBuilder.append("                     <password>").append("123456").append("</password>");
        soapBuilder.append("                </my:authorization>");
        soapBuilder.append("    </soapenv:Body>");
        soapBuilder.append("</soapenv:Envelope>");
        //创建httpcleint对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建http Post请求
        HttpPost httpPost = new HttpPost(url);
        /**
         * 构建请求配置信息
         * 创建连接的最长时间 1000
         * 从连接池中获取到连接的最长时间 500
         * 数据传输的最长时间3s
         */
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(3 * 1000)
                .build();
        httpPost.setConfig(config);
        CloseableHttpResponse response = null;

        //采用SOAP1.1调用服务端，这种方式能调用服务端为soap1.1和soap1.2的服务
        httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");

        //采用SOAP1.2调用服务端，这种方式只能调用服务端为soap1.2的服务
        StringEntity stringEntity = new StringEntity(soapBuilder.toString(), StandardCharsets.UTF_8);
        httpPost.setEntity(stringEntity);
        try {
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                Document soapRes = Jsoup.parse(content);
                Elements returnEle = soapRes.getElementsByTag("return");

                JSONObject aReturn = XML.toJSONObject(returnEle.toString()).getJSONObject("return");
                R r =new R();
                BeanUtils.populate(r,aReturn.toMap());
                System.out.println(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
