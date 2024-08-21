package org.scoula.advice;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j
@Component
public class LogAdvice {

    @Before("execution(* org.scoula.sample.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
        log.info("str1: " + str1);
        log.info("str2: " + str2);
        log.info("==================================");
    }

    @AfterThrowing(pointcut = "execution(* org.scoula.sample.service.SampleService*.*(..))", throwing = "exception")
    public void logException(Exception exception) {
        log.info("Exception...!!!!");
        log.info("exception: " + exception);
    }

    @Around("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();

        log.info("Target:" + pjp.getTarget());
        log.info("Param:" + Arrays.toString(pjp.getArgs()));

        Object result;
        try {
            result = pjp.proceed(); // 실제 메서드 호출
        } catch (Throwable e) {
            log.error("Exception in logTime: ", e);
            throw e; // 예외를 다시 던져서 원래 메서드 호출에 영향을 주지 않도록 함
        }

        long end = System.currentTimeMillis();
        log.info("TIME: " + (end - start));
        return result;
    }
}
