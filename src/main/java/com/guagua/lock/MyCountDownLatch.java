package com.guagua.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author guagua
 * @date 2022/11/9 17:33
 * @describe
 */
public class MyCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " count = " + countDownLatch.getCount());
                countDownLatch.countDown();
            }, "thread" + i).start();
        }

        countDownLatch.await();

    }
}
