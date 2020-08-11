package college.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * User: xuxianbei
 * Date: 2019/11/6
 * Time: 9:52
 * Version:V1.0
 */
@Data
@Slf4j
public class SignUtil {

    String sessionkey;
    String method;
    String appkey;
    String sign;
    int page_no = 1;
    int page_size = 10;

    // 得到sign的字符串
    public static String sign(String str, String secret) {
        log.info("source: " + str);
        log.info("secret: " + secret);
        StringBuilder enValue = new StringBuilder();
        enValue.append(secret);
        enValue.append(str);
        enValue.append(secret);
        log.info("append secret: " + enValue.toString());
        log.info("sign: " + encryptByMD5(enValue.toString()));
        return encryptByMD5(enValue.toString());
    }

    private static String encryptByMD5(String data) {
        StringBuilder sign = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(data.getBytes("UTF-8"));
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(bytes[i] & 0xFF);
                if (hex.length() == 1) {
                    sign.append("0");
                }
                sign.append(hex.toUpperCase());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign.toString();
    }

    public static void main(String[] args) {
//        yingfengSign();
        String interfacename = "SkuListSynchro";
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String key = "20050b8a014011ea865a7cd30adfe8ac";
        String content = "{\"PageNo\":1,\"PageNum\":100}";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(key).append(date).append(interfacename).append(content);
        String token = encryptByMD5(stringBuilder.toString());
        Map<String, String> headers = new HashMap<>();
        headers.put("interfacename", interfacename);
        headers.put("parenter", "6000836_6000700");
        headers.put("token", token);
//        sendPost("http://121.41.84.251:9090/api/SkuListSynchro.shtml", content, ContentType.create("application/json"), headers);
        headers.put("content-type", "application/json");
        String result = HttpUtil.postDataHeads("http://121.41.84.251:9090/api/SkuListSynchro.shtml", content, headers);
        System.out.println(result);
    }

    //盈丰
    private static void yingfengSign() {
        SignUtil signUtil = new SignUtil();
        signUtil.setAppkey("173854");
        signUtil.setSessionkey("101d2e3b8e504d6d86af11c99604be7e");
        signUtil.setMethod("gy.erp.items.get");
        String sign = sign(JSONObject.toJSONString(signUtil), "05ab3980e9034df4b3d655abde34f100");
        signUtil.setSign(sign);
        sendPost("http://v2.api.guanyierp.com/rest/erp_open", JSONObject.toJSONString(signUtil),
                ContentType.create("text/json", "UTF-8"), new HashMap<>());
    }


    public static void sendPost(String url, String data, ContentType contentType, Map<String, String> headers) {
        log.info("url: " + url);
        log.info("request: " + data);
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpPost httppost = new HttpPost(url);
                StringEntity stringentity = new StringEntity(
                        data, contentType);
                httppost.setEntity(stringentity);
                Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> map = iterator.next();
                    httppost.setHeader(map.getKey(), map.getValue());
                }
                httpresponse = httpclient.execute(httppost);
                String response = EntityUtils
                        .toString(httpresponse.getEntity());
                log.info("response: " + response);
            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
