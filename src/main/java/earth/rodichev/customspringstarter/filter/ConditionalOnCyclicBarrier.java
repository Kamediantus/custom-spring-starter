package earth.rodichev.customspringstarter.filter;

import java.util.*;

import org.springframework.context.annotation.*;
import org.springframework.core.env.*;
import org.springframework.core.type.*;

public class ConditionalOnCyclicBarrier implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return canUseBarrier(context.getEnvironment());
    }

    private boolean canUseBarrier(Environment environment) {
        var enabled = Optional.ofNullable(environment.getProperty("concurrency.enabled"));
        var cyclicBarrierEnabled = Optional.ofNullable(environment.getProperty("concurrency.cyclicBarrierEnabled"));
        boolean hasProp = enabled.isPresent() && cyclicBarrierEnabled.isPresent();

        return hasProp && Boolean.parseBoolean(cyclicBarrierEnabled.get());
    }
}
