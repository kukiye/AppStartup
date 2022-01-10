package com.enjoy.appstartup;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ThreadUnitTest {
    @Test
    public void testJoin() throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                //执行第一步
                System.out.println("第一步执行完成！");
                //执行第二步
                System.out.println("第二步执行完成！");
                //执行第三步
                System.out.println("第三步执行完成！");
            }
        };
        t1.start();
        t1.join();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("执行第二个线程任务！");
            }
        };
        t2.start();
        t2.join();
    }

    @Test
    public void testNotify() throws InterruptedException {
        Object lock = new Object();
        List<String> data = new ArrayList<>();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    //执行第一步
                    data.add("1");
                    System.out.println("第一步执行完成！");
                    //执行第二步
                    data.add("2");
                    System.out.println("第二步执行完成！");
                    lock.notify();
                    //执行第三步
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    data.add("3");
                    System.out.println("第三步执行完成！");
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    data.add("4");
                    System.out.println("执行第二个线程任务！");
                }

            }
        };
        t2.start();
        t1.start();

        t1.join();
        t2.join();
    }
}