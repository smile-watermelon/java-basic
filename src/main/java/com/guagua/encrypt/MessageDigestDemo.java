package com.guagua.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author guagua
 * @date 2023/2/3 14:58
 * @describe
 */
public class MessageDigestDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String original = "瓜瓜";
        // 24位
        // 149147234486752222651662212262481511807055
        // 9593ea304334de41a6dde2f897b44637
//        String algorithm = "MD5";
//        String algorithm = "sha";
        // d3f0a9256a7b3a92555b88b5826082fbe07b65d665a5f12cea6ee5c8fe0dc706
//        String algorithm = "sha-256";
        // 88位
        String algorithm = "sha-512";
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

        byte[] digest = messageDigest.digest(original.getBytes());
//        System.out.println(new String(digest));
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : digest) {
            String s = Integer.toHexString(b & 0xff);
            s = s.length() == 1 ? "0" + s : s;
            stringBuilder.append(s);
        }
        System.out.println(stringBuilder);
//        byte[] encode = Base64.getEncoder().encode(digest);
//        String encrypt = new String(encode);
//        System.out.println(encrypt);
//        System.out.println(encrypt.length());
    }
}
