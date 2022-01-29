package org.springframework.lock.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Field;
import java.util.concurrent.locks.Lock;

@Aspect
public class WriteLockAspect {

    private static final Log LOGGER = LogFactory.getLog(WriteLockAspect.class);

    @Around("@annotation(org.springframework.lock.annotation.WriteLock)")
    public Object aroundWriteLock(ProceedingJoinPoint jp) throws Throwable {
        Object obj = jp.getTarget();
        Class<?> clz = obj.getClass();
        Lock writeLock = null;
        for (Field field : clz.getDeclaredFields()) {
            if ("$writeLock".equals(field.getName())){
                field.setAccessible(true);
                Object unknownLock = field.get(obj);
                writeLock = (Lock) unknownLock;
            }
        }
        Object result = null;
        if (writeLock != null) {
            writeLock.lock();
            try {
                LOGGER.info(clz.getSimpleName() + "获得写锁");
                result = jp.proceed();
                LOGGER.info(clz.getSimpleName() + "释放写锁");
            } finally {
                writeLock.unlock();
            }
        }else {
            LOGGER.warn(clz.getSimpleName() + "生成读锁失败,未能加锁");
            result = jp.proceed();
        }
        return result;
    }

}