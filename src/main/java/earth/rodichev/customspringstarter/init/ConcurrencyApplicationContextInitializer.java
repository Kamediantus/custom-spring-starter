package earth.rodichev.customspringstarter.init;

import earth.rodichev.customspringstarter.aspect.*;
import earth.rodichev.customspringstarter.bean.*;
import org.slf4j.*;
import org.springframework.context.*;

public class ConcurrencyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger LOG = LoggerFactory.getLogger(ConcurrencyApplicationContextInitializer.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        LOG.info("Вызов ConcurrencyApplicationContextInitializer");
        applicationContext.getBeanFactory().registerSingleton(ThreadsInfo.class.getName(), new ThreadsInfo());
    }
}
