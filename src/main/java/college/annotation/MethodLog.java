package college.annotation;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLog {
	String value() default "";
	String description() default "";
	String path() default "";
}
