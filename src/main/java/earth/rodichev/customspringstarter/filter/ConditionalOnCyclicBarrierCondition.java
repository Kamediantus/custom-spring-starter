package earth.rodichev.customspringstarter.filter;

import java.lang.annotation.*;

import org.springframework.context.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(ConditionalOnCyclicBarrier.class)
public @interface ConditionalOnCyclicBarrierCondition {
}
