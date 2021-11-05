package com.mxw.doraemon.utils.secret.paillier;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Kevin Zhang
 * @time: 2020/11/25 3:30 下午
 */
public interface KPaillier {
    /**
     * 密钥生成
     * @return 密钥对
     */
    public KeyPairs KeyGeneration();

    /**
     *  数据加密
     * @param data  需要加密数据
     * @param publicKey 公钥
     * @return 密文
     */
    public String encryption(BigDecimal data, String publicKey);

    /**
     * 数据解密
     * @param encryptedValue 密文
     * @param publicKey  公钥
     * @param privateKey  私钥
     * @return  明文
     */
    public String decryption(String encryptedValue, String publicKey, String privateKey);

    /**
     * 同态加法
     * @param encryptedValues 数值
     * @return 密文结果
     */
    public String add(String... encryptedValues);
}
