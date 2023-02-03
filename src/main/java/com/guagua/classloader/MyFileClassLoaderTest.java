package com.guagua.classloader;


/**
 * @author guagua
 * @date 2022/12/15 23:22
 * @describe
 */
public class MyFileClassLoaderTest extends ClassLoader {

    public static void main(String[] args) throws Exception {
        String dir = "/Users/guagua/code/my/java-basic/target/classes/";
        MyFileClassLoader classLoader = new MyFileClassLoader(dir);
        Class<?> aClass = classLoader.findClass("com.guagua.classloader.Test");
        Object o = aClass.newInstance();
    }
}
