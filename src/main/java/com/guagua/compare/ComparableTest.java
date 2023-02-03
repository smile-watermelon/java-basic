package com.guagua.compare;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Arrays;
import java.util.List;

/**
 * @author guagua
 * @date 2022/12/20 18:19
 * @describe
 */
public class ComparableTest {
    /**
     * 自然排序
     *
     * 当前this 大于 形参 obj 返回正数，
     * 当前this 小于 形参 obj 返回负数
     * 当前this 等于 形参 obj 返回0
     * @param args
     */
    public static void main(String[] args) {
        User[] users = new User[]{new User("guagua", 19),
                new User("dudu", 18)
        };
        Arrays.sort(users);
        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i]);
        }
    }


}

class User implements Comparable {

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            return this.age - user.age;
        }
        throw new RuntimeException("输入的参数异常");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}