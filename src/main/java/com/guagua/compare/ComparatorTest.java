package com.guagua.compare;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author guagua
 * @date 2022/12/20 18:21
 * @describe
 */
public class ComparatorTest {


    public static void main(String[] args) {
        People[] peoples = new People[]{
          new People("gaugua", 19),
          new People("dudu", 18)
        };

        Arrays.sort(peoples, new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                if (o1.getAge() - o2.getAge() > 0) {
                    return 1;
                } else if (o1.getAge() - o2.getAge() < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for (int i = 0; i < peoples.length; i++) {
            System.out.println(peoples[i]);
        }
    }
}

class People implements Comparator {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public People(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compare(Object o1, Object o2) {
        People people = (People) o1;
        People people1 = (People) o2;

        return people.age - people1.age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
