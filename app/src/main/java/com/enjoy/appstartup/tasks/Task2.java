package com.enjoy.appstartup.tasks;

import android.content.Context;
import android.os.SystemClock;


import androidx.annotation.Nullable;

import com.enjoy.appstartup.LogUtils;
import com.enjoy.appstartup.startup.AndroidStartup;
import com.enjoy.appstartup.startup.Startup;

import java.util.ArrayList;
import java.util.List;

public class Task2 extends AndroidStartup<Void> {
    static List<Class<? extends Startup<?>>> depends;

    static {
        depends = new ArrayList<>();
        depends.add(Task1.class);
    }

    @Nullable
    @Override
    public Void create(Context context) {
        LogUtils.log("学习Socket");
        SystemClock.sleep(800);
        LogUtils.log("掌握Socket");
        return null;
    }


    @Override
    public List<Class<? extends Startup<?>>> dependencies() {
        return depends;
    }
}
