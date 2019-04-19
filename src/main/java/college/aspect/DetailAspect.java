package college.aspect;

import college.annotation.ActionDetail;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Name
 *
 * @author xxb
 * Date 2019/4/11
 * VersionV1.0
 * @description  注解加执行方法的
 */
@Aspect
@Component
public class DetailAspect {

    @Pointcut("@annotation(college.annotation.ActionDetail)")
    private void detailAction() {
    }

    @Pointcut("execution(* college.controller.AopController.*(..))")
    private void aopAction() {
    }

    @Around("aopAction()")
    private void doAopAction(ProceedingJoinPoint joinPoint) {
        System.out.println("arround" + joinPoint.getArgs());
    }


    //proceedingJoinPoint 这个只有Around有。
//    @Before("detailAction()&&@annotation(actionDetail)")
//    public void beforeDetail(JoinPoint point, ActionDetail actionDetail) {
//        System.out.println("before" + point.getArgs() + actionDetail.value());
//    }

    @Around("detailAction()&&@annotation(actionDetail)")
    public void beforeDetail2(ProceedingJoinPoint joinPoint, ActionDetail actionDetail) {
        System.out.println("arround" + joinPoint.getArgs() + actionDetail.value());
    }

}
