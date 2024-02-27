package earth.rodichev.customspringstarter.aspect;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

import earth.rodichev.customspringstarter.annotation.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;

@Aspect
public class LockAspect {
    private Map<String, Lock> locks = new ConcurrentHashMap<>();

    @Pointcut("@annotation(lockable)")
    public void lockPointcut(Lockable lockable) {}

    @Around("lockPointcut(lockable)")
    public Object lockAdvice(ProceedingJoinPoint joinPoint, Lockable lockable) throws Throwable {
        var lock = locks.computeIfAbsent(lockable.resource(), (k) -> new ReentrantLock());

        try {
            lock.lock();

            return joinPoint.proceed();
        } finally {
            lock.unlock();
        }
    }
}
