package com.sky.utils;

import org.springframework.stereotype.Component;

@Component
public class ThreadLocalUtil {
    private static ThreadLocal<Long> currentEmpId = new ThreadLocal<>();

    public static Long getCurrentEmpId() {
        return currentEmpId.get();
    }

    public static void setCurrentEmpId(Long id) {
        currentEmpId.set(id);
    }

    public static void remove() {
        currentEmpId.remove();
    }
}
