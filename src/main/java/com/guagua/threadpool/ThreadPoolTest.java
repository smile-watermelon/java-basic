package com.guagua.threadpool;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guagua
 * @date 2022/12/21 15:37
 * @describe
 */
public class ThreadPoolTest {

    /**
     * 1、批量提交任务量小于，等于核心线程数，线程池都会创建和任务数量相等的核心线程数
     * 2、如果批量提交的任务数量大于核心线程数量，小于/等于队列长度，使用核心线程数处理任务
     * 3、如果批量提交的任务数超过了队列设置的容量，核心数可以处理的过来，继续使用核心线程数处理任务（核心线程数处理任务的速度大于任务的提交提速，会使用核心线程数处理，不会创建非核心线程）
     *      如果核心线程数处理不过来，会创建非核心线程数处理任务
     * 4、如果提交的任务数量超过了，队列的容量，并且达到线程池线程的数量达到最大值后，任务提交速度大于线程处理速度，会执行拒绝策略
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        // 抛出异常
//        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        // 默默丢弃，不会抛出异常
//        ThreadPoolExecutor.DiscardPolicy rejectPolicy = new ThreadPoolExecutor.DiscardPolicy();
        // 丢弃最先提交没有处理的任务
//        ThreadPoolExecutor.DiscardOldestPolicy rejectPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        // 由main 线程直接执行 Runnable 的 run 方法
        ThreadPoolExecutor.CallerRunsPolicy rejectPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), rejectPolicy);

        for (int i = 0; i < 5; i++) {
            executor.execute(new MyRunnable());
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println(executor.getPoolSize());
    }
}

class MyRunnable implements Runnable{

    AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getId() +" count=" + count.get());
        count.getAndIncrement();
    }
}
