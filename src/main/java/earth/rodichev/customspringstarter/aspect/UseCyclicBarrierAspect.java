package earth.rodichev.customspringstarter.aspect;

import java.util.*;
import java.util.concurrent.*;

import earth.rodichev.customspringstarter.annotation.*;
import earth.rodichev.customspringstarter.bean.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.slf4j.Logger;

@Aspect
public class UseCyclicBarrierAspect {
    private static final Logger LOG = LoggerFactory.getLogger(UseCyclicBarrierAspect.class);

    private final Map<String, CyclicBarrier> barriers = new ConcurrentHashMap<>();

    private final BarrierAction barrierAction;

    public UseCyclicBarrierAspect(BarrierAction barrierAction) {
        this.barrierAction = barrierAction;
    }

    @Pointcut("@annotation(useCyclicBarrier)")
    public void useCyclicBarrierPointcut(UseCyclicBarrier useCyclicBarrier) {}

    @Before("useCyclicBarrierPointcut(useCyclicBarrier)")
    public void beforeUseCyclicBarrier(UseCyclicBarrier useCyclicBarrier) {
        String barrierName = useCyclicBarrier.barrier();

        CyclicBarrier barrier = barriers.computeIfAbsent(barrierName, k -> new CyclicBarrier(useCyclicBarrier.parties(),
                barrierAction::action));

        try {
            LOG.info("Поток " + Thread.currentThread().getName() + " ждет у барьера.");
            barrier.await();
            LOG.info("Поток " + Thread.currentThread().getName() + " перешел барьер.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
