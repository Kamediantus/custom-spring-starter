package earth.rodichev.customspringstarter.bean;

import java.lang.management.*;
import java.util.concurrent.*;

import earth.rodichev.customspringstarter.aspect.*;
import org.slf4j.*;

public class ThreadsInfo {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadsInfo.class);

    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    public void printThreadsInfo() {
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
        for (ThreadInfo threadInfo : threadInfos) {
            LOG.info("ID потока: " + threadInfo.getThreadId());
            LOG.info("Имя потока: " + threadInfo.getThreadName());
            LOG.info("Статус потока: " + threadInfo.getThreadState());
            LOG.info("--------------------------------");
        }
    }
}
