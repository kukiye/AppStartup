package com.enjoy.appstartup.tasks;

import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;


import com.enjoy.appstartup.LogUtils;
import com.enjoy.appstartup.startup.AndroidStartup;
import com.enjoy.appstartup.startup.Startup;

import java.util.ArrayList;
import java.util.List;

public class Task4 extends AndroidStartup<Void> {

    static List<Class<? extends Startup<?>>> depends;

    static {
        depends = new ArrayList<>();
        depends.add(Task2.class);
    }

    @Override
    public Void create(Context context) {
        String t = Looper.myLooper() == Looper.getMainLooper()
                ? "主线程: " : "子线程: ";
        LogUtils.log(t + "学习Http");
        SystemClock.sleep(1_000);
        LogUtils.log(t + "掌握Http");
        return null;
    }


    @Override
    public List<Class<? extends Startup<?>>> dependencies() {
        return depends;
    }
}
