package college.utils.secret;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Name 摘要工具
 *
 * @author xuxb
 * Date 2018-12-03
 * VersionV1.0
 * @description
 */
public class SignUtils {
    private static String secret = "cEq6yAc4NEo=";

    /**
     * 生成签名 默认使用 md5
     *
     * @param value 要转换成签名的数据
     * @return 生成后的签名
     * @throws NoSuchAlgorithmException 无指定签名类型异常
     */
    public static byte[] generateMD5Sign(String value) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(value.getBytes("utf8"));
            return bytes;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 参数摘要
     *  主要针对 请求参数是否发生变化
     * @param params
     * @return
     */
    public static String getSign(Map<String, String> params) {
        Set<String> set = params.keySet();
        TreeSet<String> sortSet = new TreeSet<>();
        sortSet.addAll(set);
        StringBuilder keyValue = new StringBuilder();
        Iterator<String> iterator = sortSet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = params.get(key);
            keyValue.append(key).append(value);
        }
        keyValue.append(secret);
        return CodeUtils.base64Encode(generateMD5Sign(keyValue.toString()));
    }

    /**
     * 校验
     *  校验一般发生在服务器，所以线程安全问题存在
     * @param params
     * @param sign
     * @return
     */
    public static synchronized boolean validate(Map<String, String> params, String sign) {
        Set<String> set = params.keySet();
        TreeSet<String> sortSet = new TreeSet<>();
        sortSet.addAll(set);
        StringBuilder keyValue = new StringBuilder();
        Iterator<String> iterator = sortSet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = params.get(key);
            keyValue.append(key).append(value);
        }
        keyValue.append(secret);
        return CodeUtils.base64Encode(generateMD5Sign(keyValue.toString())).equals(sign);
    }

    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("冰女", "库拉");
        hashMap.put("草薙", "草薙");
        hashMap.put("鬼烧", "鬼彻");
        hashMap.put("泪滴", "十二天帝");
        String sign = getSign(hashMap);
        System.out.println(sign);
        System.out.println(validate(hashMap, sign));

    }
}
