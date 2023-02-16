package com.guagua.encrypt;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author guagua
 * @date 2023/2/3 15:42
 * @describe 非对称加密
 * rsa
 */
public class RsaDemo {

    public RsaDemo() {

    }

    @Test
    public void test() throws Exception {

        String original = "瓜瓜";
        String algorithm = "RSA";

        String privateKeyPath = "key.pri";
        String publicKeyPath = "key.pub";

        KeyPair keyPair = getKeyPair(algorithm);
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        byte[] privateKeyEncoded = privateKey.getEncoded();
        byte[] publicKeyEncoded = publicKey.getEncoded();

        String privateKeyEncodeBase64 = Base64.encode(privateKeyEncoded);
        String publicKeyEncodeBase64 = Base64.encode(publicKeyEncoded);

        // 保存公钥，私钥
//        saveKeyToFile(privateKeyPath, privateKeyEncodeBase64, publicKeyPath, publicKeyEncodeBase64);
//        String privateKeyStr = getKeyByPath(privateKeyPath);

//        Cipher cipher = Cipher.getInstance(algorithm);

//        String encrypt = encrypt(original, cipher, privateKey);
//        System.out.println(encrypt);

//        String decrypt = decrypt(encrypt, cipher, publicKey);
//        System.out.println(decrypt);


    }

    @Test
    public void getPrivateKeyTest() throws Exception {
        String path = "key.pri";
        String publicKeyPath = "key.pub";
        String algorithm = "RSA";
        PrivateKey privateKey = getPrivateKey(path, algorithm);
        byte[] privateKeyEncoded = privateKey.getEncoded();
        System.out.println(Base64.encode(privateKeyEncoded));

        PublicKey publicKey = getPublicKey(publicKeyPath, algorithm);
        byte[] publicKeyEncoded = publicKey.getEncoded();
        System.out.println(Base64.encode(publicKeyEncoded));
    }

    @Test
    public void getSignatureTest() throws Exception {
        String signatureAlgorithm = "sha256withrsa";
        String encryptAlgorithm = "RSA";
        String privateKeyPath = "key.pri";
        String publicKeyPath = "key.pub";


        String original = "瓜瓜";
        PrivateKey privateKey = getPrivateKey(privateKeyPath, encryptAlgorithm);
        PublicKey publicKey = getPublicKey(publicKeyPath, encryptAlgorithm);

        // 用私钥进行签名
        String signatureData = getSignature(signatureAlgorithm, privateKey, original);

        System.out.println(signatureData);
        // 用公钥进行签名的验证
        boolean b = verifySignature(signatureAlgorithm, publicKey, original, signatureData);
        System.out.println(b);
    }

    private String encrypt(String original, Cipher cipher, PrivateKey privateKey) throws Exception {

        /**
         * 使用私钥加密
         */
//        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        byte[] encrypt = cipher.doFinal(original.getBytes(StandardCharsets.UTF_8));
        return Base64.encode(encrypt);
    }

    private String decrypt(String encrypt, Cipher cipher, PublicKey publicKey) throws Exception {
        /**
         * 公钥解密
         */
        byte[] decode = Base64.decode(encrypt);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        byte[] decrypt = cipher.doFinal(decode);
        return new String(decrypt);
    }

    /**
     * 保存私钥，公钥到文件
     *
     * @param privateKeyPath
     * @param privateKey
     * @param publicKeyPath
     * @param publicKey
     * @throws IOException
     */
    public static void saveKeyToFile(String privateKeyPath, String privateKey, String publicKeyPath, String publicKey) throws IOException {
        FileUtils.writeStringToFile(new File(privateKeyPath), privateKey);
        FileUtils.writeStringToFile(new File(publicKeyPath), publicKey);
    }

    /**
     * 获取私钥
     *
     * @param path
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String path, String algorithm) throws Exception {
        String key = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8.toString());
        // 获取公钥/私钥的工厂
        KeyFactory factory = KeyFactory.getInstance(algorithm);
        byte[] decode = Base64.decode(key);
        // 构建私钥/公钥的编码规则
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode);
        // 生产私钥
        return factory.generatePrivate(pkcs8EncodedKeySpec);
    }

    public static PublicKey getPublicKey(String path, String algorithm) throws Exception {
        String key = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8.toString());
        KeyFactory factory = KeyFactory.getInstance(algorithm);
        byte[] decode = Base64.decode(key);
//        RSAPublicKeySpec pkcs8EncodedKeySpec = new RSAPublicKeySpec();
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decode);
        return factory.generatePublic(x509EncodedKeySpec);
    }

    /**
     * 对原文进行签名
     *
     * @param algorithm 签名算法
     * @param privateKey 私钥
     * @param original 原文
     * @return
     * @throws Exception
     */
    public static String getSignature(String algorithm, PrivateKey privateKey, String original) throws Exception {
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateKey);
        signature.update(original.getBytes());
        byte[] sign = signature.sign();

        return Base64.encode(sign);
    }

    /**
     * 校验签名
     *
     * @param algorithm 签名算法
     * @param publicKey 公钥验证签名
     * @param original 原文
     * @param signatureData 原文签名后的数据
     * @return
     * @throws Exception
     */
    public static boolean verifySignature(String algorithm, PublicKey publicKey, String original, String signatureData) throws Exception {
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(publicKey);
        signature.update(original.getBytes());
        return signature.verify(Base64.decode(signatureData));
    }

    /**
     * 从文件读取公钥，私钥
     *
     * @param path
     * @return
     */
    private static String getKeyByPath(String path) {
        FileReader fileReader = null;
        try {
            File file = new File(path);
            fileReader = new FileReader(file);
            char[] buffer = new char[1024];
            int len;
            StringBuilder builder = new StringBuilder();
            while ((len = fileReader.read(buffer)) != -1) {
                builder.append(buffer, 0, len);
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取公钥，私钥对
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair getKeyPair(String algorithm) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        return keyPairGenerator.genKeyPair();
    }


}
