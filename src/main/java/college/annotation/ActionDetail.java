package college.annotation;

import java.lang.annotation.*;

/**
 * Name
 *
 * @author xxb
 * Date 2019/4/11
 * VersionV1.0
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionDetail {

    String value() default "";
}
