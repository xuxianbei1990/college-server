package college.utils.secret;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * Name  秘钥生产工具
 *
 * @author xuxb
 * Date 2018-11-27
 * VersionV1.0
 * @description
 */
public class GeneratorUtils {


    /**
     * 生成秘钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String genKey(SymmetricSecretEnums secretEnums) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(secretEnums.getName());
        keyGenerator.init(secretEnums.getLength());
        SecretKey key = keyGenerator.generateKey();
        String base64Str = CodeUtils.base64Encode(key.getEncoded());
        return base64Str;
    }

    /**
     * 生产公钥：
     *
     * @return
     * @throws Exception 参考书籍：大型分布式网站架构 160;
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }


    /**
     * 获取公钥
     *
     * @param keyPair
     * @return
     */
    public static String getPublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return CodeUtils.base64Encode(bytes);
    }

    /** 获取私钥
     * @param keyPair
     * @return
     */
    public static String getPrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return CodeUtils.base64Encode(bytes);
    }

    /**
     * 加载秘钥
     *
     * @param value
     * @param secretEnums
     * @return
     */
    public static SecretKey loadKey(String value, SymmetricSecretEnums secretEnums) {
        byte[] bytes = CodeUtils.base64Decode(value);
        return new SecretKeySpec(bytes, secretEnums.getName());
    }

    public static void main(String[] args) throws Exception {
        testSymmetric();
//        KeyPair keyPair = getKeyPair();
//        System.out.println(getPublicKey(keyPair));
//        System.out.println("----------");
//        System.out.println(getPrivateKey(keyPair));
    }

    //测试对称加密
    private static void testSymmetric() throws NoSuchAlgorithmException {
        String str = genKey(SymmetricSecretEnums.AES);
        System.out.println(str);
        System.out.println(loadKey(str, SymmetricSecretEnums.AES));
    }
}
