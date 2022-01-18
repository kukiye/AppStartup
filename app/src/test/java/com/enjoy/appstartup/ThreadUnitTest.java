package com.enjoy.appstartup;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    //执行第一步
                    System.out.println("第一步执行完成！");
                    //执行第二步
                    System.out.println("第二步执行完成！");
                    lock.notify();
                    //执行第三步
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
                }
                System.out.println("执行第二个线程任务！");
            }
        };
        t2.start();
        t1.start();

        t1.join();
        t2.join();
    }

    @Test
    public void testNotify2() throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                //执行第一步
                System.out.println("t1:第一步执行完成！");
                //执行第二步
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1:第二步执行完成！");
                synchronized (lock1) {
                    lock1.notify();
                }
                //执行第三步
                System.out.println("t1:第三步执行完成！");
            }
        };


        Thread t2 = new Thread() {
            @Override
            public void run() {
                //执行第一步
                System.out.println("t2:第一步执行完成！");
                //执行第二步
                System.out.println("t2:第二步执行完成！");
                synchronized (lock2) {
                    lock2.notify();
                }
                //执行第三步
                System.out.println("t2:第三步执行完成！");
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                synchronized (lock1) {
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (lock2) {
                    try {
                        lock2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("执行第三个线程任务！");
            }
        };
        t3.start();
        t2.start();
        t1.start();

        t1.join();
        t2.join();
        t3.join();
    }

    @Test
    public void testCountDown() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                //执行第一步
                System.out.println("t1:第一步执行完成！");
                //执行第二步
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1:第二步执行完成！");
                countDownLatch.countDown();
                //执行第三步
                System.out.println("t1:第三步执行完成！");
            }
        };


        Thread t2 = new Thread() {
            @Override
            public void run() {
                //执行第一步
                System.out.println("t2:第一步执行完成！");
                //执行第二步
                System.out.println("t2:第二步执行完成！");
                countDownLatch.countDown();
                //执行第三步
                System.out.println("t2:第三步执行完成！");
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行第三个线程任务！");
            }
        };
        t3.start();
        t2.start();
        t1.start();

        t1.join();
        t2.join();
        t3.join();
    }
}