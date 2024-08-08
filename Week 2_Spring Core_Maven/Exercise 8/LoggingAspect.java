package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.library.BookService.manageBooks(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Before executing: " + joinPoint.getSignature());
    }

    @After("execution(* com.library.BookService.manageBooks(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("After executing: " + joinPoint.getSignature());
    }
}
