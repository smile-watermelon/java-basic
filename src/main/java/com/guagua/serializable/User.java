package com.guagua.serializable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author guagua
 * @date 2023/3/17 20:48
 * @describe
 */
public class User implements Serializable {
    private static final long serialVersionUID = 2602228657096243551L;

    private int id;
    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(id);
        out.writeUTF(name);
    }

    public void readObject(ObjectInputStream in) throws IOException {
        id = in.readInt();
        name = in.readUTF();
    }

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
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
