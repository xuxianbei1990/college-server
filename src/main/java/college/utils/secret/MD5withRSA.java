package college.utils.secret;

import javax.crypto.Cipher;
import java.security.*;

/**
 * Name  MD5withRSA
 *
 * @author xuxb}
 * "
 * Date 2018-12-03
 * VersionV1.0
 * @description 如果是SHA1withRSA; 只需要把MD5 摘要改为SHA1；
 */
public class MD5withRSA {

    /**
     * 摘要
     *
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    private static byte[] signPrinciple(byte[] content, PrivateKey privateKey) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(content);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(bytes);
    }

    /**
     * 校验
     *
     * @param content
     * @param sign
     * @param publicKey
     * @return
     * @throws Exception
     */
    private static boolean verifyPrinciple(byte[] content, byte[] sign, PublicKey publicKey) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(content);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptBytes = cipher.doFinal(sign);
        return CodeUtils.base64Encode(bytes).equals(CodeUtils.base64Encode(decryptBytes));
    }

    private static byte[] sign(byte[] content, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(content);
        return signature.sign();
    }

    private static boolean verify(byte[] content, byte[] sign, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        signature.update(content);
        return signature.verify(sign);
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
        byte[] bytes = sign("草薙京".getBytes(), AsymmetricEnDecryptUtils.getPrivateKey(privateKey));
        System.out.println(verify("草薙京".getBytes(), bytes, AsymmetricEnDecryptUtils.getPublicKey(publicKey)));
    }
}
