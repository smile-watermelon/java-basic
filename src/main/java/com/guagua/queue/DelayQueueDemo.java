package com.guagua.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * @author guagua
 * @date 2022/11/13 22:34
 * @describe
 */
public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Delayed> delayQueue = new DelayQueue<>();
        delayQueue.add(new UserDelayed("guagua", 5));


        System.out.println(delayQueue.take());
    }
}
