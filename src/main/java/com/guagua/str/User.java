package com.guagua.str;

import java.util.Objects;

/**
 * 重写 equals 方法目的是判断两个实例对象是不是相等，这里的 相等 是基于对象的 字段 是不是相等确定的
 *
 * @author guagua
 * @date 2023/2/16 21:55
 * @describe
 */
public class User {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
