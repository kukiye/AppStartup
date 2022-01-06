package com.enjoy.appstartup.startup;


import android.os.Process;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public abstract class AndroidStartup<T> implements Startup<T> {

    @Override
    public List<Class<? extends Startup<?>>> dependencies() {
        return null;
    }

    @Override
    public int getDependenciesCount() {
        List<Class<? extends Startup<?>>> dependencies = dependencies();
        return dependencies == null ? 0 : dependencies.size();
    }

}
