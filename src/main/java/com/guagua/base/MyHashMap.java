package com.guagua.base;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLClassLoader;
import java.util.HashMap;

/**
 * @author guagua
 * @date 2022/11/21 15:53
 * @describe
 */
public class MyHashMap {


    public static void main(String[] args) throws URISyntaxException {


//        System.out.println(VM.current().details());
//        System.out.println(VM.current().objectAlignment());
//        Object obj = new Object();
//        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
//        System.out.println("--------------");
//        Customer customer = new Customer();
//        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
//        System.out.println(obj.hashCode());
//        HashMap<String, String> map = new HashMap<>();
        // put 的操作流程
        /**
         * 1、
         */
//        map.put("name", "guagua");
    }
}

class Customer{
    private int id;
    private boolean flag;
}