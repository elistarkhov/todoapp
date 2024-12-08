package io.demo.todoapp.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ControllerLogger {
    private static final Logger log = LoggerFactory.getLogger(ControllerLogger.class);

    @Pointcut("within(io.demo.todoapp.controller.ToDoController)")
    private void toDoControllerMethods() {}

    @Before(value = "toDoControllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        log.debug("Invoking {}() with args - {}", methodName, Arrays.toString(args));
    }

    @AfterReturning(value = "toDoControllerMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.debug("Executed {}() - {}", methodName, result);
    }

    @AfterThrowing(value = "toDoControllerMethods()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        log.error("Exception in {}() - {}", methodName, exception.getMessage());
    }
}
