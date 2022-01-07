package com.enjoy.appstartup;

import android.os.SystemClock;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.util.LogUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class CountDownLatchTest {
    private static final String TAG = "CountDownLatchTest";

    @Test
    public void testCountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread() {
            @Override
            public void run() {
                Log.e(TAG, "任务3等待执行");
                try {
                    countDownLatch.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "任务3执行完成");
            }
        }.start();
        SystemClock.sleep(1_000);
        Log.e(TAG, "任务1执行完成");
        countDownLatch.countDown();
        SystemClock.sleep(1_000);
        Log.e(TAG, "任务2执行完成");
        countDownLatch.countDown();
    }
}
