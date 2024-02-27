package earth.rodichev.customspringstarter.configuration;

import earth.rodichev.customspringstarter.aspect.*;
import earth.rodichev.customspringstarter.bean.*;
import earth.rodichev.customspringstarter.configuration.properties.*;
import earth.rodichev.customspringstarter.filter.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@AutoConfiguration
@EnableConfigurationProperties(ConcurrencyProperties.class)
@ConditionalOnProperty(prefix = "concurrency", value = "enabled", havingValue = "true", matchIfMissing = false)
public class ConcurrencyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public BarrierAction barrierAction() {
        return new BarrierActionImpl();
    }

    @Bean
    @ConditionalOnExpression("${concurrency.lock-enabled:false}")
    public LockAspect lockAspect() {
        return new LockAspect();
    }

    @Bean
    @ConditionalOnCyclicBarrierCondition
    public UseCyclicBarrierAspect useCyclicBarrierAspect(BarrierAction barrierAction) {
        return new UseCyclicBarrierAspect(barrierAction);
    }

    @Bean
    @ConditionalOnProperty(prefix = "concurrency", value = "semaphore-enabled", havingValue = "true",
            matchIfMissing = true)
    public UseSemaphoreAspect useSemaphoreAspect() { return new UseSemaphoreAspect(); }
}
