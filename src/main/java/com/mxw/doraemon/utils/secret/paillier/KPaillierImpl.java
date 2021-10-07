package com.mxw.doraemon.utils.secret.paillier;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

/**
 * @description:
 * @author: Kevin Zhang
 * @time: 2020/11/25 4:58 下午
 * @Version  1.0 released on 2020.11.26
 */
public class KPaillierImpl implements KPaillier {

    private static int bitLength = 1024;
    private static int certainty = 64;
    private static int decimalsLength = 10;

    @Override
    public KeyPairs KeyGeneration() {
        BigInteger p = new BigInteger(bitLength / 2, certainty, new Random());
        BigInteger q = new BigInteger(bitLength / 2, certainty, new Random());
        BigInteger n = p.multiply(q);
        BigInteger nsquare = n.multiply(n);

        BigInteger g = n.add(BigInteger.ONE);

        BigInteger lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)).divide(
                p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));

        KeyPairs keyPair = new KeyPairs(n.toString(),lambda.toString());
        return keyPair;
    }

    @Override
    public String encryption(BigDecimal data, String publicKey){
        BigInteger r = new BigInteger(bitLength, new Random());
        BigInteger n = new BigInteger(publicKey);
        BigInteger nsquare = n.multiply(n);
        BigInteger g = n.add(BigInteger.ONE);
        BigDecimal d = data.setScale(decimalsLength, RoundingMode.HALF_UP);
        BigInteger m = d.multiply(new BigDecimal(10).pow(decimalsLength)).toBigInteger();
        String ciphertext = g.modPow(m, nsquare).multiply(r.modPow(n, nsquare)).mod(nsquare).toString();
        return ciphertext;
    }

    @Override
    public String decryption(String encryptedValue, String publicKey, String privateKey){
        BigInteger c = new BigInteger(encryptedValue);
        BigInteger n = new BigInteger(publicKey);
        BigInteger g = n.add(BigInteger.ONE);
        BigInteger lambda = new BigInteger(privateKey);
        BigInteger nsquare = n.multiply(n);
        BigInteger u = g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).modInverse(n);
        BigInteger ret = c.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
        BigInteger maxValue = BigInteger.ONE.shiftLeft(n.bitLength() / 2);
        if (ret.compareTo(maxValue) > 0) {
            ret = ret.subtract(n);
        }
        BigDecimal r = new BigDecimal(ret);
        String str = r.divide(new BigDecimal(10).pow(decimalsLength)).toString();
        return str;
    }

    @Override
    public String add(String... encryptedValues) {
        BigInteger sum = BigInteger.ONE;
        for(String encryptedValue:encryptedValues){
            BigInteger em1 = new BigInteger(encryptedValue);
            sum = em1.multiply(sum);
        }
//        sum = sum.mod(nsquare);
        return sum.toString();
    }

}
