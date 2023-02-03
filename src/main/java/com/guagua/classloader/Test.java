package com.guagua.classloader;

/**
 * @author guagua
 * @date 2022/12/15 23:13
 * @describe
 */
public class Test {

    public static void main(String[] args) {
        // 线程上下文类加载器，实现SPI 接口的类 无法被 bootstrap 类加载器加载，
        // bootstrap 类加载器也无法委托给系统类加载器，因为 SPI 类是被引导类加载器所加载
        // 所以需要线程上下文类加载器加载
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 默认使用系统类加载器
        System.out.println(classLoader); // sun.misc.Launcher$AppClassLoader@18b4aac2

    }

    public Test() {
        System.out.println("constructor...");
    }
}
