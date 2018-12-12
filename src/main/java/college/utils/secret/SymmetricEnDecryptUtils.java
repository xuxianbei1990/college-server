package college.utils.secret;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/**
 * Name 对称加解密工具
 *
 * @author xuxb
 * Date 2018-11-27
 * VersionV1.0
 * @description
 */
public class SymmetricEnDecryptUtils {


    /**
     * 加密
     *
     * @param bytes
     * @param key
     * @param enums
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] bytes, SecretKey key, SymmetricSecretEnums enums) throws Exception {
        Cipher cipher = Cipher.getInstance(enums.getName());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(bytes);
    }


    /**
     * 解密
     *
     * @param bytes
     * @param key
     * @param enums
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] bytes, SecretKey key, SymmetricSecretEnums enums) throws Exception {
        Cipher cipher = Cipher.getInstance(enums.getName());
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(bytes);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(
                CodeUtils.base64Encode(encrypt("dd".getBytes(), GeneratorUtils.loadKey("cEq6yAc4NEo=", SymmetricSecretEnums.DES),
                        SymmetricSecretEnums.DES))
        );
    }
}
