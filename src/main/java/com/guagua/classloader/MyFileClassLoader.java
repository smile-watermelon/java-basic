package com.guagua.classloader;

import java.io.*;

/**
 * @author guagua
 * @date 2022/12/15 23:22
 * @describe
 */
public class MyFileClassLoader extends ClassLoader {

    // 被加载所在类的目录
    private String directory;

    public MyFileClassLoader(String directory) {
        this.directory = directory;
    }

    public MyFileClassLoader(String directory, ClassLoader parent) {
        super(parent);
        this.directory = directory;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String file = directory + File.separator + name.replace(".", File.separator) + ".class";
            // 构建输入流
            InputStream inputStream = new FileInputStream(file);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            byte[] bytes = outputStream.toByteArray();
            inputStream.close();
            outputStream.close();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
