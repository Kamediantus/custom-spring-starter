package earth.rodichev.customspringstarter.analyser;

import earth.rodichev.customspringstarter.exception.*;
import org.springframework.boot.diagnostics.*;

public class ConcurrencyFailureAnalyzer extends AbstractFailureAnalyzer<ConcurrencyStartupException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, ConcurrencyStartupException cause) {
        return new FailureAnalysis(cause.getMessage(), "Укажите валидные значения свойств.", cause);
    }
}
