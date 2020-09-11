package com.infelt.schedule.Utils;


import android.os.Handler;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUtils {
    private static volatile ThreadUtils threadUtils;

    private Handler handler;
    private ThreadPoolExecutor threadPoolExecutor;

    public static synchronized ThreadUtils getThreadUtils() {
        if (threadUtils == null) {
            synchronized (ThreadUtils.class) {
                if (threadUtils == null) {
                    threadUtils = new ThreadUtils();
                }
            }
        }
        return threadUtils;
    }

    public void init() {
        if (handler == null) {
            handler = new Handler();
        }
        if (threadPoolExecutor == null) {
            threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                    60L, TimeUnit.SECONDS,
                    new LinkedBlockingDeque<Runnable>());
        }

    }


    public Handler getHandler() {
        return handler;
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }
}
