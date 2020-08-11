package college.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds  
    private final static String DEFAULT_ENCODING = "UTF-8";
    // HTTP内容类型。如果未指定ContentType，默认为TEXT/HTML
    public static final String CONTENT_TYPE_TEXT_HTML = "text/html";

    // HTTP内容类型。相当于form表单的形式，提交数据  api
    public static final String CONTENT_TYPE_FORM_URL = "application/json;charset=UTF-8";//application/x-www-form-urlencoded";


    public static String postData(String urlStr, String data) {
        return postData(urlStr, data, null);
    }

    public static String postData(String urlStr, JSONObject data) {
        return postData(urlStr, data, null);
    }


    public static String postDataHeads(String urlStr, String data, Map<String, String> heads) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            if (heads != null && heads.size() > 0) {
                Iterator<Map.Entry<String, String>> iterator = heads.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            //conn.connect();
            // 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，
            // 所以在开发中不调用上述的connect()也可以)。
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
            if (data == null)
                data = "";
            writer.write(data);
            writer.flush();
            writer.close();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            if (!StringUtils.isEmpty(sb)) {
                logger.info("服务端返回：" + sb.toString());
                return sb.toString();
            }
        } catch (IOException e) {
            logger.error("Error connecting to " + urlStr + ": " + e.getMessage());
        } finally {

            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                logger.error("IOException:" + e.getMessage());
            }
        }
        return null;
    }

    public static String postData(String urlStr, String data, String contentType) {
        Map<String, String> heads = new HashMap<>();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(contentType)) {
            heads.put("content-type", contentType);
        }
        return postDataHeads(urlStr, data, heads);
    }

    /**
     * @param @param  urlStr 服务端URL
     * @param @param  data 请求数组
     * @param @param  contentType HTTP内容类型 application/json;charset=UTF-8
     * @param @return 参数说明
     * @return String    返回类型
     * @throws
     * @Title: postData
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author sup
     */
    public static String postData(String urlStr, JSONObject data, String contentType) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            if (contentType != null)
                conn.setRequestProperty("content-type", contentType);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING); 
            /*if(StringUtils.isEmpty(data) || data.isEmpty()){
            	data = "";  
            }*/
            writer.write(data.toString());
            //System.out.println(data.toString());
            writer.flush();
            writer.close();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            if (!StringUtils.isEmpty(sb)) {
                logger.info("服务端返回：" + sb.toString());
                return sb.toString();
            }
        } catch (IOException e) {
            logger.error("Error connecting to " + urlStr + ": " + e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                logger.error("IOException:" + e.getMessage());
            }
        }
        return null;
    }
}
