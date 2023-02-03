package com.guagua.encrypt;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

/**
 * @author guagua
 * @date 2023/2/3 12:17
 * @describe base58 一般用于比特币，编码
 * base58 没有数字0，没有字母o,没有大写字母I，小写字母i,也没有+号，斜杠 /
 * <p>
 * base64 3个字节一组，24位，把三个字节转换为4组，每组6位
 * 一个字节8位，6位的话缺少两位，在高位进行补0
 * 好处取后面6位，111111 = 32 + 16 + 8 + 4 + 2 + 1 = 63，0 ~ 63 是64个数
 * base64 输出等号=，不够三个字节需要用等号补齐
 * <p>
 * 加密模式
 * ECB (electronnic code book 电子密码本)  将原文分成多个数据块，使用同一个秘钥，对每个块进行加密
 * 缺点是：多个块的原文一样，加密后的密文也一样，安全度低
 * 优点：可以进行并行加密处理，提升加密速度
 * <p>
 * CBC （cipher block chaining 密码块连接）每个明文先与前一个密文块进行异或计算后再进行加密，每个密文块都依赖于它前面的所有文明块
 * 会有一个加密初始iv向量，对第一块明文进行加密
 * 优点：同样的明文生成的密文不一样
 * 缺点是：串行处理，速度慢
 * <p>
 * 填充模式
 * 不填充
 * DES加密算法下，要求原文长度必须是8字节的整数倍
 * AES加密算法下，要求原文长度必须是16字节的整数倍
 * PKCS5padding：数据块的大小为8位，不够进行补足
 * 默认情况下，加密模式为，ECB/PKCS5padding
 *
 */
public class CipherDemo {

    public static void main(String[] args) throws Exception {

        String a = "瓜瓜";
        System.out.println(new String(Base64.getEncoder().encode(a.getBytes())));
        String original = "瓜瓜";
        // 使用DES 加密秘钥长度必须是8个字节
        // 使用AES加码，秘钥长度必须是16个字节
//        String key = "1234567812345678";
        // AES iv 必须是16字节, ECB 模式下，不能加 iv 向量
//        String iv = "1234567812345678";
        // 加密算法
//        String algorithm = "AES";
//        String transformation = "AES/ECB/PKCS5Padding";
//        String transformation = "AES/CBC/PKCS5Padding";

        /**
         * DES 加密算法，key 和 iv 的字节长度必须是8字节
         */
        String key = "12345678";
        String iv = "12345678";
        String algorithm = "DES";
        // 加密模式
        String transformation = "DES/ECB/PKCS5Padding";
//        String transformation = "DES/CBC/PKCS5Padding";

        String encrypt = encrypt(original, key, transformation, algorithm, null);
//        String encrypt = encrypt(original, key, transformation, algorithm, iv);
        System.out.println(encrypt);

        String decrypt = decrypt(encrypt, key, transformation, algorithm, null);
//        String decrypt = decrypt(encrypt, key, transformation, algorithm, iv);
        System.out.println(decrypt);
    }

    /**
     * 解密
     *
     * @param encrypt
     * @param key
     * @param transformation
     * @param algorithm
     * @return
     * @throws Exception
     */
    private static String decrypt(String encrypt, String key, String transformation, String algorithm, String iv) throws Exception {

        Cipher cipher = Cipher.getInstance(transformation);

        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);

        if (!Objects.isNull(iv)) {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        }

        byte[] decode = Base64.getDecoder().decode(encrypt.getBytes());

        byte[] bytes = cipher.doFinal(decode);

        return new String(bytes);

    }

    /**
     * 加密
     *
     * @param original       原文
     * @param key            加密的key
     * @param transformation 加密模式
     * @param algorithm      加密算法
     * @return x5cLX71/cHc= 加密后是8个字节，base64 是三个字节一组，差一个字节，用等号补齐
     * @throws Exception
     */
    private static String encrypt(String original, String key, String transformation, String algorithm, String iv) throws Exception {
        // 获取加密的实力
        Cipher cipher = Cipher.getInstance(transformation);

        // 创建秘钥规则
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
        // 初始化秘钥
//        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 在 CBC 加密模式下，需要使用iv 向量
        if (!Objects.isNull(iv)) {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        }

        // 加密
        byte[] bytes = cipher.doFinal(original.getBytes(StandardCharsets.UTF_8));
//        for (byte aByte : bytes) {
//            System.out.println(aByte);
//        }
        // 不在asscii 码表的字符会出现乱码，使用base64 进行转码，
        byte[] encode = Base64.getEncoder().encode(bytes);
        return new String(encode);
    }
}
