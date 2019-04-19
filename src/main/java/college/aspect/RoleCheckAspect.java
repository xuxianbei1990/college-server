package college.aspect;

import college.annotation.CheckAuth;
import college.utils.JWTBean;
import college.utils.JWTUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author :         sp
 * @createDate :     2019/1/29
 * @description :   角色权限拦截器AOP  注解加获取注解值
 */
@Aspect
@Component
public class RoleCheckAspect {


    @Pointcut("@annotation(college.annotation.CheckAuth)")
    private void pointCut() {
    }

    @Before("pointCut()&&@annotation(checkAuth)")
    public void around(JoinPoint joinPoint, CheckAuth checkAuth) throws Throwable {
        JWTBean jwtBean = getCurrentUserInfo();
        if (jwtBean.getRole() == null) {
            throw new RuntimeException("无此角色");
        }

        //取出接口权限菜单
        int[] menuIds = checkAuth.value();
        //根据用户角色取出对应角色
        // 从缓存中获取所有的菜单，判断当前的菜单id是不是在列表中，存在返回，不存在直接异常
//        List<MenuVO> menuVOS = JSON.parseArray(RedisUtil.get(RedisPrefixEnum.SYS_MENU_ROLE_.getPrefix() + jwtBean.getRole()), MenuVO.class);
//        for (int i : menuIds) {
//            for (MenuVO menuVO : menuVOS) {
//                if (i == menuVO.getId()) {
//                    return;
//                }
//            }
//        }
//        throw new EatingException(ReturnCode.INVALID_PRIVILEGE.getReturnCode(), ReturnCode.INVALID_PRIVILEGE.getDesc());
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static JWTBean getCurrentUserInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String header = request.getHeader("college-token");
        JWTBean jwtBean = JWTUtil.verifyToken(header);
        return jwtBean;
    }

}
