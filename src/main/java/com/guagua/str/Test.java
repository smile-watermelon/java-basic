package com.guagua.str;

/**
 * @author guagua
 * @date 2022/12/27 12:36
 * @describe
 */
public class Test {

    public static void main(String[] args) {
        String a= "_xx";
        String[] s = a.split("_");
        for (String s1 : s) {
            System.out.println(s1);
        }
        System.out.println(s.length);

        User user = new User();
        user.setId(1);
        user.setName("瓜瓜");

        System.out.println(user);
    }
}
