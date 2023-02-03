package com.guagua.classloader;

import java.io.*;
import java.net.URL;

/**
 * @author guagua
 * @date 2022/12/15 23:22
 * @describe
 */
public class MyUrlClassLoader extends ClassLoader {

    // 被加载所在类的目录
    private String url;

    public MyUrlClassLoader(String url) {
        this.url= url;
    }


    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String path = url + "/"+ name.replace(".", File.separator) + ".class";
            URL url1 = new URL(path);
            // 构建输入流
            InputStream inputStream = url1.openStream();

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
