package college.utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :         XLY
 * @createDate :     2018/10/12 20:11
 * @description :
 *                  jwt第一段信息:
 *                  {"alg":"HS256","typ":"JWT"}
 *                  jwt第二段使用Base64解码的信息:
 *                  {"exp":1548731364,"iat":1548729564,"info":"{jwtBean自定义信息)}"}
 */
public class JWTUtil {


    /**
     * 创建 JWT
     *
     * @param info   用户信息
     * @param expire 过期时间(当前时间的多少秒后)
     * @return 生成的 JWT
     * @throws UnsupportedEncodingException 无法加密异常
     */
    public static String createToken(JWTBean info, long expire)
            throws UnsupportedEncodingException {
        //jwt签名secret
        Algorithm al = Algorithm.HMAC256("881989e0-074e-48cd-8db7-ba70427dbc77");
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return JWT.create()
                .withHeader(header)
                .withClaim("info", JSONObject.toJSONString(info))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expire * 1000))
                .sign(al);
    }


    /**
     * 检验JWT
     *
     * @param token 令牌
     * @return 包装好的 JWT bean
     */
    public static JWTBean verifyToken(String token) {
        if (token == null || "".equals(token)) {
            return null;
        }
        // JWT 解码
        DecodedJWT decode = JWT.decode(token);
        // 获取过期时间
        Date expiresTime = decode.getExpiresAt();
        Claim infoClaim = decode.getClaim("info");
        // 获取信息 json 串
        String info = infoClaim.asString();
        // json 转换成 bean
        JWTBean jwtBean = JSONObject.parseObject(info, JWTBean.class);
        if (jwtBean != null) {
            jwtBean.setExpiresTime(expiresTime);
        }
        return jwtBean;
    }



}
