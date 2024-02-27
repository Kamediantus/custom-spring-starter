package earth.rodichev.customspringstarter.aspect;

import java.util.*;
import java.util.concurrent.*;

import earth.rodichev.customspringstarter.annotation.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;

@Aspect
public class UseSemaphoreAspect {
    private static final Logger LOG = LoggerFactory.getLogger(UseSemaphoreAspect.class);

    private final Map<String, Semaphore> semaphores = new ConcurrentHashMap<>();

    @Pointcut("@annotation(useSemaphore)")
    public void useSemaphorePointcut(UseSemaphore useSemaphore) {}

    @Around("useSemaphorePointcut(useSemaphore)")
    public Object useSemaphorePointcutAround(ProceedingJoinPoint joinPoint, UseSemaphore useSemaphore) throws Throwable {
        Semaphore semaphore = semaphores.computeIfAbsent(useSemaphore.target(), k -> new Semaphore(useSemaphore.permits()));

        try {
            semaphore.acquire();
            LOG.info("Семафор захвачен потоком " + Thread.currentThread().threadId());

            return joinPoint.proceed();

        } finally {
            semaphore.release();
            LOG.info("Семафор отпущен потоком " + Thread.currentThread().threadId());
        }
    }
}
