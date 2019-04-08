package college.utils.secret;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Name 非对称加密工具
 *
 * @author xuxb]
 * Date 2018-12-03
 * VersionV1.0
 * @description 参考网址：https://www.cnblogs.com/frank-quan/p/7073457.html
 * 秘钥已经内置了base64解码
 */
public class AsymmetricEnDecryptUtils {
    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 公钥加密
     *
     * @param content
     * @param pubStr
     * @return
     * @throws Exception
     */
    public static byte[] publicEncrypt(byte[] content, String pubStr) throws Exception {
        PublicKey pubKey = getPublicKey(pubStr);

        //数据加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(content);
    }

    /**
     * 私钥加密
     *
     * @param content
     * @param priStr
     * @return
     * @throws Exception
     */
    public static byte[] privateEncrypt(byte[] content, String priStr) throws Exception {
        PrivateKey privateKey = getPrivateKey(priStr);
        //数据加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    /**
     * 私钥解密
     *
     * @param content
     * @param priStr
     * @return
     * @throws Exception
     */
    public static byte[] privateDecrypt(byte[] content, String priStr) throws Exception {
        PrivateKey privateKey = getPrivateKey(priStr);
        //数据解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    /**
     * 获得私钥
     *
     * @param priStr
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String priStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] key = CodeUtils.base64Decode(priStr);
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //生成私钥
        return keyFactory.generatePrivate(pkcs8KeySpec);
    }

    /**
     * 公钥解密
     *
     * @param content
     * @param priStr
     * @return
     * @throws Exception
     */
    public static byte[] publicDecrypt(byte[] content, String priStr) throws Exception {
        PublicKey pubKey = getPublicKey(priStr);
        //数据解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(content);
    }

    /**
     * 获取公钥
     *
     * @param priStr
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String priStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] key = CodeUtils.base64Decode(priStr);
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //产生公钥
        return keyFactory.generatePublic(x509KeySpec);
    }

    public static void main(String[] args) throws Exception {
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKn+Te3e8yj5tQ9H1v1ptjiqhDxV/Bodgm9KIoCH1po3\n" +
                "vnN/PPUDE5QLQuWDr6YwCMxXGMORX0HVtpsFltBAbh0CAwEAAQ==";
        String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAqf5N7d7zKPm1D0fW/Wm2OKqEPFX8" +
                "Gh2Cb0oigIfWmje+c3889QMTlAtC5YOvpjAIzFcYw5FfQdW2mwWW0EBuHQIDAQABAkBJJjYQetS1" +
                "uCYSaj54E4om/jEi6XkvH62mCAequUk+WLTZ2dD1mSOeG3DG2akdfuVS+FtbXI9Hz+aRxVABnOk9" +
                "AiEA1Gc7J7E3vcFK6xp1ZywVtoVoYQGpDbTYZ/LDz8w32dsCIQDM4qOAGwHfP4myP++qFDJQvC7P" +
                "+rLDb6PAmnpjTpuFZwIhAMoJD4mn1j+Wlq6e0kG6+0RMDYcIT0eMV7Q4hO0pcY61AiA7ZhlwsmPA" +
                "jXMmMlMRQ/myXlxT/6KD9oHK/P4ufEOqUQIgFL4dTWkhPlcBnpZjxCpCxQcjDCPy+Fk/IG2bk/lL" +
                "zo4=";
        /*
         * 公钥加密，私钥解密
         */
        byte[] bytes = publicEncrypt("卢卡尔".getBytes(), publicKey);
        System.out.print(new String(privateDecrypt(bytes, privateKey)));
        /*
         * 私钥加密，公钥解密
         */
        byte[] bytes2 = privateEncrypt("库拉".getBytes(), privateKey);
        System.out.print(new String(publicDecrypt(bytes2, publicKey)));
    }
}
