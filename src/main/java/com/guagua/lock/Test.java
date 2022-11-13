package com.guagua.lock;

/**
 * @author guagua
 * @date 2022/11/11 12:24
 * @describe
 */
public class Test {

    private int num = 50;

    public static void main(String[] args) {
        Test test = new Test();
        test.num = 30;
        int x = 500;
        int y = 100;
        int a = x / y;
        int b = 50;
        System.out.println(a + b);
    }
}
