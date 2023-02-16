package com.guagua.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author guagua
 * @date 2023/2/17 00:28
 * @describe
 */
public class FileDemo {

    public static void main(String[] args) {
        deleteRecursive(new File("data"));
//        File file = new File("data/1/2");
//        file.delete();
    }

    /**
     * 递归删除目录
     */
    public static void deleteRecursive(File file) {
        if (null ==  file) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (file.isDirectory()) {
                    deleteRecursive(f);
                }
            }
        }
        boolean delete = file.delete();
        if (delete) {
            System.out.println("删除文件成功：" + file.getAbsoluteFile());
        }
    }

    /**
     * 文件属性
     */
    @Test
    public void attr() throws IOException {
        File file = new File("data");
        System.out.println(file.getAbsolutePath()); // /Users/guagua/code/my/java-basic/data
        System.out.println(file.getAbsoluteFile()); // /Users/guagua/code/my/java-basic/data

        System.out.println("--------");
        System.out.println(file.getCanonicalPath()); // /Users/guagua/code/my/java-basic/data
        System.out.println(file.getCanonicalFile()); // /Users/guagua/code/my/java-basic/data

        System.out.println("--------");
        System.out.println(file.getParent()); // null
        System.out.println(file.getPath()); // data
        System.out.println(file.getName()); // data

        System.out.println("--------");
        System.out.println(file.getParentFile()); // null
    }

    /**
     * 文件属性
     */
    @Test
    public void attrFile() throws IOException {
        File file = new File("data/a.txt");
        System.out.println(file.getAbsolutePath()); // /Users/guagua/code/my/java-basic/data/a.txt
        System.out.println(file.getAbsoluteFile()); // /Users/guagua/code/my/java-basic/data/a.txt

        System.out.println("--------");
        System.out.println(file.getCanonicalPath()); // /Users/guagua/code/my/java-basic/data/a.txt
        System.out.println(file.getCanonicalFile()); // /Users/guagua/code/my/java-basic/data/a.txt

        System.out.println("--------");
        System.out.println(file.getParent()); // data
        System.out.println(file.getPath()); // data/a.txt
        System.out.println(file.getName()); // a.txt

        System.out.println("--------");
        System.out.println(file.getParentFile()); // data

    }

    /**
     * 创建文件
     */
    @Test
    public void createFile() {
        File parent = new File("data");
        if (!parent.exists()) {
            if (parent.mkdir()) {
                try {
                    File file = new File(parent.getPath() + "/a.txt");
                    if (file.createNewFile()) {
                        System.out.println("创建文件成功！");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
