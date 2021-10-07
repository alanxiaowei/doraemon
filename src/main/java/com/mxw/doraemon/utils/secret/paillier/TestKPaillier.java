package com.mxw.doraemon.utils.secret.paillier;

import com.mxw.doraemon.utils.secret.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @description:
 * @author: Kevin Zhang
 * @time: 2020/11/25 4:59 下午
 */
public class TestKPaillier {
	private static Logger LOGGER = LoggerFactory.getLogger(TestKPaillier.class);

	public static void main(String[] args) {
		KPaillier kPaillier = new KPaillierImpl();
		KeyPairs keyPair = kPaillier.KeyGeneration();

		System.out.println("公钥===>" + keyPair.getPublic());
		System.out.println("私钥===>" + keyPair.getPrivate());

		System.out.println(Base64.encode(keyPair.getPublic().getBytes()));

		BigDecimal bd1 = new BigDecimal("100");
		String em1 = kPaillier.encryption(bd1, keyPair.getPublic());
		System.out.println("密文===>" + em1);
		String m1 = kPaillier.decryption(em1, keyPair.getPublic(), keyPair.getPrivate());
		System.out.println("解密===>" + m1);

		BigDecimal bd2 = new BigDecimal("132.329");
		String em2 = kPaillier.encryption(bd2, keyPair.getPublic());

		BigDecimal bd3 = new BigDecimal("439");
		String em3 = kPaillier.encryption(bd3, keyPair.getPublic());
		String esum = kPaillier.add(em1, em2, em3);
		System.out.println("加法和密文===>" + esum);
		String sum = kPaillier.decryption(esum, keyPair.getPublic(), keyPair.getPrivate());
		System.out.println("加法和明文===>" + sum);
	}
}
