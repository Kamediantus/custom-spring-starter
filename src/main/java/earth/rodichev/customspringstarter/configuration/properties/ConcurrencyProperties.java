package earth.rodichev.customspringstarter.configuration.properties;

import org.springframework.boot.context.properties.*;

@ConfigurationProperties(prefix = "concurrency")
public class ConcurrencyProperties {
    private Boolean enabled;
    private Boolean lockEnabled;
    private Boolean cyclicBarrierEnabled;
    private Boolean semaphoreEnabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLockEnabled() {
        return lockEnabled;
    }

    public void setLockEnabled(Boolean lockEnabled) {
        this.lockEnabled = lockEnabled;
    }

    public Boolean getCyclicBarrierEnabled() {
        return cyclicBarrierEnabled;
    }

    public void setCyclicBarrierEnabled(Boolean cyclicBarrierEnabled) {
        this.cyclicBarrierEnabled = cyclicBarrierEnabled;
    }

    public Boolean getSemaphoreEnabled() {
        return semaphoreEnabled;
    }

    public void setSemaphoreEnabled(Boolean semaphoreEnabled) {
        this.semaphoreEnabled = semaphoreEnabled;
    }
}
