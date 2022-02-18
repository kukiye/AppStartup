package com.enjoy.appstartup.startup.run;

import android.content.Context;
import android.os.Process;

import com.enjoy.appstartup.startup.Result;
import com.enjoy.appstartup.startup.Startup;
import com.enjoy.appstartup.startup.manage.StartupCacheManager;
import com.enjoy.appstartup.startup.manage.StartupManager;

/**
 * 将每个任务放到线程池的任务中执行
 */
public class StartupRunnable implements Runnable {
    private StartupManager startupManager;
    private Startup<?> startup;
    private Context context;

    public StartupRunnable(Context context, Startup<?> startup,
                           StartupManager startupManager) {
        this.context = context;
        this.startup = startup;
        this.startupManager = startupManager;
    }

    @Override
    public void run() {
        Process.setThreadPriority(startup.getThreadPriority());
        startup.toWait();
        Object result = startup.create(context);
        StartupCacheManager.getInstance().saveInitializedComponent(startup.getClass(),
                new Result(result));

        startupManager.notifyChildren(startup);

    }
}
