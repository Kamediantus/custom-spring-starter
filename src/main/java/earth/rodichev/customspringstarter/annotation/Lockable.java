package earth.rodichev.customspringstarter.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Lockable {
    String resource() default "default";
}
