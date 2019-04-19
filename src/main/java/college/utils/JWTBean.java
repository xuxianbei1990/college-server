package college.utils;


import lombok.AllArgsConstructor;

import java.util.Date;

/**
 * @author :         sp
 * @createDate :     2019/1/27
 * @description :     payload 未序列化的jwt自定义第二段鉴权信息
 */
@AllArgsConstructor
public class JWTBean {

    /**
     * 用户编号
     */
    private int id;

    /**
     * 用户角色
     * role : -1 商家   -3学生  非后台token做区分,以应对加需求
     */
    private Integer role;

    /**
     * 刷新用token
     */
    private String refreshToken;

    /**
     * 是否是后台用户
     */
    private boolean sys;

    /**
     * 过期时间
     */
    private Date expiresTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean getSys() {
        return sys;
    }

    public void setSys(boolean sys) {
        this.sys = sys;
    }

    public Date getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Date expiresTime) {
        this.expiresTime = expiresTime;
    }


    public JWTBean(int id, Integer role, boolean sys) {
        this.id = id;
        this.role = role;
        this.sys = sys;
    }
}
