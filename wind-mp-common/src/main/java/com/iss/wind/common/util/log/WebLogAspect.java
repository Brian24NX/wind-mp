package com.iss.wind.common.util.log;

import com.google.gson.Gson;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.SocketTimeoutException;

@Component
@Aspect
@Order(1)
@Slf4j
public class WebLogAspect {

    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 以自定义 @WebLog 注解为切点
     */
    @Pointcut("@annotation(com.iss.wind.common.util.log.WebLog)")
    public void WebLogAspect() {
    }


    /**
     * 在切点之前织入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("WebLogAspect()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);
        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("Description    : {}", methodDescription);
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()));
    }

    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("WebLogAspect()")
    public void doAfter() throws Throwable {
        // 接口结束后换行，方便分割查看
        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);
        MDC.clear();
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("WebLogAspect()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            log.error("exception      :{}", e);
            int statusCode = (e instanceof ResourceAccessException || e instanceof SocketTimeoutException || e instanceof ConnectTimeoutException)?408:500;
            if(408 == statusCode){
                throw new BusinessException(statusCode,"请求超时!");
            }else {
                throw new BusinessException(statusCode,"请求异常!");
            }
        } finally {
            // 打印出参
            log.info("Response Args  : {}", new Gson().toJson(result));
            // 执行耗时
            log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        }
        return result;
    }

    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }


}
