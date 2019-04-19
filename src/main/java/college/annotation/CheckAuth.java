package college.annotation;

import java.lang.annotation.*;

/**
 * @author :         XLY
 * @createDate :     2018/10/15 19:09
 * @description :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
@Inherited
public @interface CheckAuth {

    /**
     * 配置back_menu这张表的权限id到此数组 , 不要配置title字段为true的权限,配了也没用
     * @return
     */

    int[] value() default{};
}
