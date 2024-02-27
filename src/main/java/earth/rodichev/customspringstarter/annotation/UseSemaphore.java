package earth.rodichev.customspringstarter.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UseSemaphore {

    String target() default "default";
    int permits();
}
