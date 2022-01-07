package com.enjoy.appstartup.startup;

import android.content.Context;
import android.os.Looper;

import com.enjoy.appstartup.startup.run.StartupRunnable;
import com.enjoy.appstartup.startup.sort.TopologySort;

import java.util.ArrayList;
import java.util.List;

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
            StartupRunnable startupRunnable = new StartupRunnable(context, startup, this);
            if (startup.callCreateOnMainThread()) {
                startupRunnable.run();
            } else {

            }
        }
        return this;
    }

    public void notifyChildren(Startup<?> startup, Object result) {
        //获得已经完成的当前任务的所有子任务
        if (startupSortStore
                .startupChildrenMap.containsKey(startup.getClass())) {
            List<Class<? extends Startup>> childStartupCls = startupSortStore
                    .startupChildrenMap.get(startup.getClass());
            for (Class<? extends Startup> cls : childStartupCls) {
                //通知子任务 startup父任务已完成
                Startup<?> childStartup = startupSortStore.startupMap.get(cls);
                childStartup.toNotify();
            }
        }
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
