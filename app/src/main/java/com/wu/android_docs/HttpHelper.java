package com.wu.android_docs;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {

    //    headerBase = {
//        "Accept", "*/*",
//                "Accept-Language", "zh-CN,zh;q=0.9",
//                "Accept-Encoding", "gzip, deflate, br",
//                "Connection", "keep-alive",
//    # "Accept-chareset", "utf-8",
//                "Content-Type", "application/json;charset=UTF-8",
//                "Server", "SwiftWaf",
//    # "Sec-Fetch-Dest", "empty",
//    # "Sec-Fetch-Mode", "cors",
//    # "Sec-Fetch-Site", "cross-site",
//    # "Request-No", "APP170841901119965909",
//                "X-Api-Key", "gc8U4S37ZhhoQZNeZZ0CfUCNJaKgVYqsOUJ6Uo0KugUP3H54KgmzMjoQ1dlnHjXY05FrPvFi3+Cp+eX8jCB1iB3J/inL7yLP/b3I2kUtXWK3Up6p8e4mb0xMrxd9yoyNKfonEiZe52z8QHWb8Y/TaUUyKfUBpNQC58yDbZiXxXGk7z8T+BlU/OnB1INVjTqCxGYt2GSwD9SM/0brx6sgVu6kPEvh4+7rs2FOmR/ZTBtPcVzx8dVMddWW5gikENV5VGY1wEaqZo5q51miTUqokg==",
//                "X-Api-Ver", "2.15.0",
//                "X-Auth-Token", "110015384&&&&&1&&&&&2&&&&&eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOjoxMTAwMTUzODQ6OjI6Om92S1ZGNWFjZVZ3S2JWX1ozSkxlLVM1MkhXYlUiLCJpYXQiOjE3MDkwMTU1NzYsImV4cCI6MTcwOTYyMDM3Nn0.btf5_MqTQqbxGu2Vag2v8qyjaiJpMfAmtIxMpWU2fcRYRU1H_akdm2Mkahob-iV9CSUlIIkebJJ46piiJJ0mOw&&&&&ovKVF5aceVwKbV_Z3JLe-S52HWbU",
//    # "X-Main-User-Id", 110015384,
//                "X-Serv-Identity", "release-2.27.0",
//                "Sec-Fetch-Site", "cross-site",
//                "Sec-Fetch-Mode", "cors",
//                "Sec-Fetch-Dest", "empty",
//                "Host", "gateway-jd1fy-prod.swifthealth.cn",
//                "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF WindowsWechat(0x6309062f) XWEB/8531",
//                "Referer", "https,//servicewechat.com/wxbd30792a1f4d46e2/146/page-frame.html"
//    }
    public String apiKey = "gc8U4S37ZhhoQZNeZZ0CfUCNJaKgVYqsOUJ6Uo0KugUP3H54KgmzMjoQ1dlnHjXY05FrPvFi3+Cp+eX8jCB1iB3J/inL7yLP/b3I2kUtXWK3Up6p8e4mb0xMrxd9yoyNKfonEiZe52z8QHWb8Y/TaUUyKfUBpNQC58yDbZiXxXGk7z8T+BlU/OnB1INVjTqCxGYt2GSwD9SM/0brx6sgVu6kPEvh4+7rs2FOmR/ZTBtPcVzx8dVMddWW5gikENV5VGY1wEaqZo5q51miTUqokg==";
    public String token = "110015384&&&&&1&&&&&2&&&&&eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOjoxMTAwMTUzODQ6OjI6Om92S1ZGNWFjZVZ3S2JWX1ozSkxlLVM1MkhXYlUiLCJpYXQiOjE3MDkwMTU1NzYsImV4cCI6MTcwOTYyMDM3Nn0.btf5_MqTQqbxGu2Vag2v8qyjaiJpMfAmtIxMpWU2fcRYRU1H_akdm2Mkahob-iV9CSUlIIkebJJ46piiJJ0mOw&&&&&ovKVF5aceVwKbV_Z3JLe-S52HWbU";
    public String host = "gateway-jd1fy-prod.swifthealth.cn";
    public String referer = "https,//servicewechat.com/wxbd30792a1f4d46e2/146/page-frame.html";


//    private void setHeader(HttpUriRequest request) {
//        request.setHeader("Accept", "*/*");
//        request.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
//        request.setHeader("Connection", "keep-alive");
//        request.setHeader("Content-Type", "application/json;charset=UTF-8");
//        request.setHeader("Server", "SwiftWaf");
//        request.setHeader("X-Api-Key", apiKey);
//        request.setHeader("X-Api-Ver", "2.15.0");
//        request.setHeader("X-Auth-Token", token);
//        request.setHeader("Host", host);
//        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF WindowsWechat(0x6309062f) XWEB/8531");
//        request.setHeader("Referer", referer);
//    }

    private void setHeader(HttpURLConnection connection) {
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        connection.setRequestProperty("Server", "SwiftWaf");
        connection.setRequestProperty("X-Api-Key", apiKey);
        connection.setRequestProperty("X-Api-Ver", "2.15.0");
        connection.setRequestProperty("X-Auth-Token", token);
        connection.setRequestProperty("Host", host);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF WindowsWechat(0x6309062f) XWEB/8531");
        connection.setRequestProperty("Referer", referer);
    }

//    public String Post(String url, String json) throws Exception {
//        HttpPost httpPost = new HttpPost(url);
//        setHeader(httpPost);
//        StringEntity requestEntity = new StringEntity(json, "utf-8");
////        requestEntity.setContentEncoding("UTF-8");
//        httpPost.setEntity(requestEntity);
//        String result = null;
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();//HttpClients.createDefault();
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        int status = response.getStatusLine().getStatusCode();
//        if (status == HttpStatus.SC_OK) {
//            HttpEntity responseEntity = response.getEntity();
//            result = EntityUtils.toString(responseEntity, "UTF-8");
//        }
//        response.close();
//        httpClient.close();
//        return result;
//    }

    public String doPost(String urlString, String json) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        setHeader(connection);
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(3000);
        // 设置是否向 HttpUrlConnection 输出，对于post请求，参数要放在 http 正文内，因此需要设为true，默认为false。
        connection.setDoOutput(true);
        // 设置是否从 HttpUrlConnection读入，默认为true
        connection.setDoInput(true);
        // 设置是否使用缓存
        connection.setUseCaches(false);
        // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
        connection.setInstanceFollowRedirects(true);
        // 连接
        connection.connect();
        /* 4. 处理输入输出 */
        // 写入参数到请求中
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(json.getBytes());
        outputStream.flush();
        outputStream.close();
        // 从连接中读取响应信息
        String result = "";
        if (connection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            reader.close();
        }
        // 5. 断开连接
        connection.disconnect();
        return result;
    }

}
