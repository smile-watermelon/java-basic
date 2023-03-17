package com.guagua.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author guagua
 * @date 2023/3/17 21:07
 * @describe
 */
public class UserTest {

    public static void main(String[] args) throws IOException {
        // 继承Serializable 的基本使用
        basic();
        // todo 加密序列化对象


    }

    private static void basic() throws IOException {
        Path path = Paths.get("user.bin");
        ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path));

        User user = new User(1, "guagua");
        user.writeObject(outputStream);
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path));
        User user1 = new User();
        user1.readObject(inputStream);
        inputStream.close();

        System.out.println(user1);
    }
}
