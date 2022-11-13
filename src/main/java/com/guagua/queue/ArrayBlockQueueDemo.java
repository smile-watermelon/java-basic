package com.guagua.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author guagua
 * @date 2022/11/13 20:43
 * @describe
 */
public class ArrayBlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        queue.add(1);
//        queue.offer(2);
//        queue.put(3);
        // 队列已满，返回false
//        queue.offer(2);
//        System.out.println(queue.offer(3));
        // 队列已满，抛出异常 Exception in thread "main" java.lang.IllegalStateException: Queue full
//        queue.add(4);
        // 队列已满，阻塞住线程，知道队列有空闲
//        queue.put(5);

        // 队列没有元素抛出异常       Exception in thread "main" java.util.NoSuchElementException
//        queue.remove();
        // 队列没有元素返回null
//        queue.poll();
        // 没有元素阻塞线程
//        queue.take();

        // 检查队列头有没有元素，没有元素抛出异常
//        System.out.println(queue.element());
        // 没有元素返回 0
        System.out.println(queue.peek());

        System.out.println(queue.size());
    }
}
