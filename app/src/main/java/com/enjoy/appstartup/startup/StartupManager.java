package com.enjoy.appstartup.startup;

import android.content.Context;
import android.os.Looper;

import com.enjoy.appstartup.tasks.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class StartupManager {


    private Context context;
    private List<AndroidStartup<?>> startupList;
    private StartupSortStore startupSortStore;

    public StartupManager(Context context, List<AndroidStartup<?>> startupList) {
        this.context = context;
        this.startupList = startupList;
    }

    public StartupManager start() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("请在主线程调用！");
        }
        startupSortStore = TopologySort.sort(startupList);
        for (Startup<?> startup : startupSortStore.result) {
//            if (startup.callCreateOnMainThread()) {
                Object result = startup.create(context);
//            } else {
//
//            }

            StartupCacheManager.getInstance().saveInitializedComponent(
                    startup.getClass(), new Result(result));
        }
        return this;
    }


    public static class Builder {
        private List<AndroidStartup<?>> startupList = new ArrayList<>();

        public Builder addStartup(AndroidStartup<?> startup) {
            startupList.add(startup);
            return this;
        }

        public Builder addAllStartup(List<AndroidStartup<?>> startupList) {
            startupList.addAll(startupList);
            return this;
        }


        public StartupManager build(Context context) {
            return new StartupManager(context, startupList);
        }
    }

}
