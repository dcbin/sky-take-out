package com.sky.aspect;

import com.sky.annotations.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.enumeration.OperationType;
import com.sky.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.utils.Join;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    // 定义切入点表达式
    @Pointcut("@annotation(com.sky.annotations.AutoFill)")
    public void autoFillPointCut(){}

    /**
     * 切面方法
     * @param joinPoint 切入点，在Spring Boot中即方法
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        // 1.需要知道当前是insert还是update操作，通过获取连接点的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType value = annotation.value();

        LocalDateTime now = LocalDateTime.now();
        Long currentEmpId = ThreadLocalUtil.getCurrentEmpId();

        // 2. 获取连接点的参数
        Object[] args = joinPoint.getArgs();

        if(args == null || args.length == 0) {
            return;
        }

        Object entity = args[0];
        // 2. 根据注解参数的值确定执行何种操作
        if(value == OperationType.INSERT) {
            try {
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, Long.class);
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);

                setCreateUser.invoke(entity, currentEmpId);
                setUpdateUser.invoke(entity, currentEmpId);
                setCreateTime.invoke(entity, now);
                setUpdateTime.invoke(entity, now);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);

                setUpdateUser.invoke(entity, currentEmpId);
                setUpdateTime.invoke(entity, now);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
