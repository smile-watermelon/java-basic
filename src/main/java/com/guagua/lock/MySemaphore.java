package com.guagua.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author guagua
 * @date 2022/11/9 17:48
 * @describe
 *
 */
public class MySemaphore {

    public static void main(String[] args) {
        /**
         * permits 许可证的数量，可以为负数
         */
        Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 找车位中...");
                    // 获取许可证
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 停车成功...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println(Thread.currentThread().getName() + " 出库...");
                    // 释放许可证
                    semaphore.release();
                }
            }, "thread" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
