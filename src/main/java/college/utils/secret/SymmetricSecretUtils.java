package college.utils.secret;

import javax.crypto.SecretKey;

/**
 * Name  对称加密工具
 *
 * @author xuxb
 * Date 2018-11-27
 * VersionV1.0
 * @description 封装对称加密算法直接使用
 */
public class SymmetricSecretUtils {

    /**
     * 秘钥
     */
    private static String PRIVATEKEY = "4EW8Po+b+08=";

    private static SymmetricSecretEnums secretEnums = SymmetricSecretEnums.DES;

    /**
     * 加载私钥
     *
     * @return
     */
    private static SecretKey loadKey() {
        return GeneratorUtils.loadKey(PRIVATEKEY, secretEnums);
    }

    /**
     * 加密
     *
     * @return
     * @throws Exception
     */
    public static String encrypt(String value) {
        try {
            return CodeUtils.base64Encode(SymmetricEnDecryptUtils.
                    encrypt(value.getBytes("UTF-8"), loadKey(), secretEnums));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解密
     *
     * @return
     * @throws Exception
     */
    public static String decrypt(String value) {
        try {
            return new String(SymmetricEnDecryptUtils.decrypt(CodeUtils.base64Decode(value),
                    loadKey(), secretEnums), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String str = encrypt("xx测试001");
        System.out.println(str);
        System.out.println(decrypt(str));
    }
}
