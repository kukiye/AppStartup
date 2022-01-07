package com.enjoy.appstartup;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.enjoy.appstartup.startup.Startup;
import com.enjoy.appstartup.startup.StartupSortStore;
import com.enjoy.appstartup.startup.provider.StartupInitializer;
import com.enjoy.appstartup.startup.provider.StartupProvider;
import com.enjoy.appstartup.startup.sort.TopologySort;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class ProviderTest {
    private static final String TAG = "ProviderTest";

    @Test
    public void testProvider() throws Exception {
        Context appContext =
                InstrumentationRegistry.getInstrumentation().getTargetContext();
        List<Startup<?>> startups = StartupInitializer
                .discoverAndInitializ(appContext, StartupProvider.class.getName());
        Log.e(TAG, "解析：");
        for (Startup<?> startup : startups) {
            Log.e(TAG, startup.getClass().getName());
        }
        Log.e(TAG, "拓扑：");
        StartupSortStore sortStore = TopologySort.sort(startups);
        for (Startup<?> startup : sortStore.getResult()) {
            Log.e(TAG, startup.getClass().getName());
        }
    }
}
