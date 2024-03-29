package io.stricker.framework;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackgroundTaskExecutor {

    private static ScheduledExecutorService executor;

    private static ScheduledExecutorService getExecutor() {
        return executor;
    }

    public static void submit(Runnable runnable, long loop) {
        getExecutor().scheduleAtFixedRate(runnable, 0, loop, TimeUnit.MILLISECONDS);
    }

    public static void shutdown() {
        getExecutor().shutdown();
    }
}
