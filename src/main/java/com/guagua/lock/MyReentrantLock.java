package com.guagua.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author guagua
 * @date 2022/11/9 18:08
 * @describe
 */
public class MyReentrantLock {

    private int count = 0;

    public static final String a  = "a";

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock reentrantLock = new ReentrantLock();

        System.out.println(a);

//        AtomicInteger count = new AtomicInteger();
        Counter counter = new Counter();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
//                count.getAndIncrement();
                reentrantLock.lock();
                try {
                    for (int j = 0; j < 100; j++) {
                        counter.increment();
                        System.out.println(counter.getCount());
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }).start();
        }

//        TimeUnit.SECONDS.sleep(3);
        System.out.println(counter.getCount());
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                reentrantLock.lock();
                try {
                    for (int j = 0; j < 100; j++) {
                        counter.decrement();
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }).start();
//        System.out.println(count.get());
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println(counter.getCount());
    }

}

class Counter {
    private int count;

    public void increment() {
        count++;
    }


    public void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
}