package earth.rodichev.customspringstarter.init;

import earth.rodichev.customspringstarter.exception.*;
import org.apache.commons.logging.*;
import org.springframework.boot.*;
import org.springframework.boot.env.*;
import org.springframework.boot.logging.*;
import org.springframework.core.env.*;

public class ConcurrencyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private final Log log;

    public ConcurrencyEnvironmentPostProcessor(DeferredLogFactory logFactory) {
        this.log = logFactory.getLog(ConcurrencyEnvironmentPostProcessor.class);
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("sout -> ConcurrencyEnvironmentPostProcessor");
        log.info("Вызов ConcurrencyEnvironmentPostProcessor");

        String enablePropertyValue = environment.getProperty("concurrency.enabled");

        boolean isBoolValue = Boolean.TRUE.toString().equals(enablePropertyValue) ||
                Boolean.FALSE.equals(enablePropertyValue);

        if (!isBoolValue) {
            throw new ConcurrencyStartupException("Ошибка при считывании значения 'concurrency.enabled'");
        }
    }
}
