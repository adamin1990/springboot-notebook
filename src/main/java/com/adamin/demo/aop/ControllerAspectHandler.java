package com.adamin.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Classname ControllerAspectHandler
 * @Description 切面类
 * @Date 2022/4/10 20:24
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Aspect
@Component
public class ControllerAspectHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 切面
     * 第一个*标表示返回值任意
     * 后面包名
     * ..表示包名或子包
     * 第二个*表示类名任意
     * 第三个*表示方法名任意
     * 括号内..表示任意参数
     */
    @Pointcut("execution(* com.adamin.demo.controller..*.*(..))")
    public void controllerCut() {

    }

    /**
     * 方法执行之前
     * @param joinPoint
     */
    @Before("controllerCut()")
    public void doBeforeController(JoinPoint joinPoint) {
        logger.info("----entrance doBeforeController----");
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String declaringTypeName = signature.getDeclaringTypeName();
        logger.info("---切入方法名{},位于包名{}下",methodName,declaringTypeName);

        // 也可以用来记录一些信息，比如获取请求的url和ip
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
// 获取请求url
        String url = request.getRequestURL().toString();
// 获取请求ip
        String ip = request.getRemoteAddr();
        logger.info("用户请求的url为：{}，ip地址为：{}", url, ip);
    }

    /**
     * 方法执行之后
     */
    @After("controllerCut()")
    public void doAfterController(JoinPoint joinPoint){
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("{}.{}方法执行完成,参数{}",declaringTypeName,name,args==null?"无参数":
                Arrays.toString(args));


    }

    /**
     * 方法返回之后
     * @param joinPoint
     * @param res
     */
    @AfterReturning(pointcut = "controllerCut()",returning = "res")
    public void doAfterControllerReturn(JoinPoint joinPoint,Object res){
        Signature signature = joinPoint.getSignature();
        logger.info("{}.{}执行完返回之后，返回值{}",signature.getDeclaringTypeName()
        ,signature.getName(),res);
    }

    /**
     * 抛出异常后
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "controllerCut()",throwing = "ex")
    public void doAfterControllerThrow(JoinPoint joinPoint,Throwable ex){
        String name = joinPoint.getSignature().getName();
        logger.info("{}方法抛出异常，异常内容：{}",name,ex
        .getMessage());
    }

}
