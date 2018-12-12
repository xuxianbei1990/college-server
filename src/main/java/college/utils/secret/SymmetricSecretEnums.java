package college.utils.secret;

/**
 * Name 对称加密算法
 *
 * @author xuxb
 * Date 2018-11-27
 * VersionV1.0
 * @description
 */
public enum SymmetricSecretEnums {
    DES("DES", 56),
    AES("AES", 128),
    AES192("AES", 192),
    AES256("AES", 256);
    private String name;
    private int length;

    SymmetricSecretEnums(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
}
