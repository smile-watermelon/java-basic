package com.guagua.threaddemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guagua
 * @date 2022/12/26 18:13
 * @describe
 */
public class RunnableDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);


    }
}


class Account implements Runnable {

    private int salary = 100;

    public void add(int mount) {
        this.salary += mount;
    }

    public void decrement(int money) {
        this.salary -= money;
    }

    @Override
    public void run() {

    }
}