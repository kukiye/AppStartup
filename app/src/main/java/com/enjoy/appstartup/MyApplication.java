package com.enjoy.appstartup;

import android.app.Application;

import com.enjoy.appstartup.startup.manage.StartupManager;
import com.enjoy.appstartup.tasks.Task1;
import com.enjoy.appstartup.tasks.Task2;
import com.enjoy.appstartup.tasks.Task3;
import com.enjoy.appstartup.tasks.Task4;
import com.enjoy.appstartup.tasks.Task5;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        new StartupManager.Builder()
//                .addStartup(new Task5())
//                .addStartup(new Task4())
//                .addStartup(new Task3())
//                .addStartup(new Task2())
//                .addStartup(new Task1())
//                .build(this)
//                .start().await();
    }
}
