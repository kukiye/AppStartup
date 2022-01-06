package com.enjoy.appstartup.startup;

import android.content.Context;

import java.util.List;

public interface Startup<T> {

    T create(Context context);

    /**
     * 本任务依赖哪些任务
     *
     * @return
     */
    List<Class<? extends Startup<?>>> dependencies();


    int getDependenciesCount();
}
