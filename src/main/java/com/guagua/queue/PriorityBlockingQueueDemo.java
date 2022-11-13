package com.guagua.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author guagua
 * @date 2022/11/13 22:37
 * @describe
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(3);

        queue.add(1);
        queue.add(1);
        queue.add(1);
        queue.add(1);
    }
}
