package college.utils.secret;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Name 编码
 *
 * @author xuxb
 * Date 2018-11-26
 * VersionV1.0
 * @description 一般较大的数据需要进行 ASCII 编码多采用 Base64；而较小的数据，则使用易于人工识别十六进制（用纸笔就能解码出来）。
 */
public class CodeUtils {

    /**
     * base64位解码
     *
     * @param str
     * @return
     */
    public static byte[] base64Decode(String str) {
        try {
            return new BASE64Decoder().decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * base64编码
     *
     * @param values
     * @return
     */
    public static String base64Encode(byte[] values) {
        return new BASE64Encoder().encode(values);
    }

    /**
     * 16进制编码
     *
     * @param byteArr
     * @return
     */
    public static String HexEncode(byte[] byteArr) {
        if (null == byteArr || byteArr.length < 1) return "";
        StringBuilder sb = new StringBuilder();
        for (byte t : byteArr) {
            if ((t & 0xF0) == 0) sb.append("0");
            sb.append(Integer.toHexString(t & 0xFF));  //t & 0xFF 操作是为去除Integer高位多余的符号位（java数据是用补码表示）
        }
        return sb.toString();
    }

    /**
     * 16进制解码
     *
     * @param hexStr
     * @return
     */
    public static byte[] HexDecode(String hexStr) {
        if (null == hexStr || hexStr.length() < 1) return null;

        int byteLen = hexStr.length() / 2;
        byte[] result = new byte[byteLen];
        char[] hexChar = hexStr.toCharArray();
        for (int i = 0; i < byteLen; i++) {
            result[i] = (byte) (Character.digit(hexChar[i * 2], 16) << 4 | Character.digit(hexChar[i * 2 + 1], 16));
        }

        return result;
    }
}
