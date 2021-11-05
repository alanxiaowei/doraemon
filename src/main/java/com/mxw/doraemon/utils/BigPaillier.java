package com.mxw.doraemon.utils;

import java.math.BigInteger;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BigPaillier {
	/**
	 * p and q are two large primes.
	 * lambda = lcm(p-1, q-1) = (p-1)*(q-1)/gcd(p-1, q-1).
	 */
	private BigInteger p, q, lambda;
	/**
	 * n = p*q, where p and q are two large primes.
	 */
	public BigInteger n;
	/**
	 * nsquare = n*n
	 */
	public BigInteger nsquare;
	/**
	 * a random integer in Z*_{n^2} where gcd (L(g^lambda mod n^2), n) = 1.
	 */
	private BigInteger g = new BigInteger("65537");
	;
	/**
	 * number of bits of modulus
	 */
	private int bitLength;

	private String split = "0123456";

	/**
	 * Constructs an instance of the Paillier cryptosystem.
	 *
	 * @param bitLengthVal number of bits of modulus
	 * @param certainty    The probability that the new BigInteger represents a prime number will exceed (1 - 2^(-certainty)). The execution time of this constructor is proportional to the value of this parameter.
	 */
	public BigPaillier(int bitLengthVal, int certainty) {
		KeyGeneration(bitLengthVal, certainty);
	}

	/**
	 * Constructs an instance of the Paillier cryptosystem with 512 bits of modulus and at least 1-2^(-64) certainty of primes generation.
	 */
	public BigPaillier() {
		//        generateKeys();
	}

	public Map<String, String> generateKeys(int bitLengthVal, int certainty) {
		return KeyGeneration(bitLengthVal, certainty);
	}

	public Map<String, String> generateKeys() {
		return KeyGeneration(512, 64);
	}

	/**
	 * Sets up the public key and private key.
	 *
	 * @param bitLengthVal number of bits of modulus.
	 * @param certainty    The probability that the new BigInteger represents a prime number will exceed (1 - 2^(-certainty)). The execution time of this constructor is proportional to the value of this parameter.
	 */
	public Map<String, String> KeyGeneration(int bitLengthVal, int certainty) {
		bitLength = bitLengthVal;
		/*Constructs two randomly generated positive BigIntegers that are probably prime, with the specified bitLength and certainty.*/
		p = new BigInteger(bitLength / 2, certainty, new Random());
		q = new BigInteger(bitLength / 2, certainty, new Random());
		n = p.multiply(q);
		nsquare = n.multiply(n);

		g = new BigInteger("65537");
		//        g = getRandom(2, 10);
		lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)).divide(p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));
		/* check whether g is good.*/
		if (g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1) {
			System.out.println("g is not good. Choose g again.");
			System.exit(1);
		}
		Map<String, String> keyMap = new HashMap<String, String>();
		String pk = n + "";
		String sk = lambda + split + n;
		String publicKeyString = Base64.getEncoder().encodeToString(pk.getBytes());
		keyMap.put("pk", publicKeyString);// 0表示公钥
		String privateKeyString = Base64.getEncoder().encodeToString(sk.getBytes());
		keyMap.put("sk", privateKeyString);// 1表示私钥
		return keyMap;
	}

	public String Encryption(BigInteger m, String publicKey) {
		//        BigInteger m = new BigInteger(String.valueOf(value));
		BigInteger r = new BigInteger(bitLength, new Random());
		//        r = n.add(getRandom(100, 200));
		String nstr = new String(Base64.getDecoder().decode(publicKey));
		BigInteger n = new BigInteger(nstr);
		BigInteger nsquare = n.multiply(n);
		return g.modPow(m, nsquare).multiply(r.modPow(n, nsquare)).mod(nsquare).toString();
	}

	public BigInteger Decryption(String encryptedValue, String privateKey) {
		BigInteger c = new BigInteger(encryptedValue);
		String keys = new String(Base64.getDecoder().decode(privateKey));
		String[] keylist = keys.split(split);
		String lambda1, n1;
		if (keylist.length > 1) {
			lambda1 = keylist[0];
			n1 = keylist[1];
		} else {
			lambda1 = keylist[0];
			n1 = lambda1;
		}
		BigInteger lambda = new BigInteger(lambda1);
		BigInteger n = new BigInteger(n1);
		BigInteger nsquare = n.multiply(n);
		BigInteger u = g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).modInverse(n);
		BigInteger ret = c.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
		BigInteger maxValue = BigInteger.ONE.shiftLeft(n.bitLength() / 2);
		if (ret.compareTo(maxValue) > 0) {
			ret = ret.subtract(n);
		}
		return ret;
	}

	/**
	 * Encrypts plaintext m. ciphertext c = g^m * r^n mod n^2. This function explicitly requires random input r to help with encryption.
	 *
	 * @param m plaintext as a BigInteger
	 * @param r random plaintext to help with encryption
	 * @return ciphertext as a BigInteger
	 */
	public BigInteger Encryption(BigInteger m, BigInteger r) {
		return g.modPow(m, nsquare).multiply(r.modPow(n, nsquare)).mod(nsquare);
	}

	/**
	 * Encrypts plaintext m. ciphertext c = g^m * r^n mod n^2. This function automatically generates random input r (to help with encryption).
	 *
	 * @param m plaintext as a BigInteger
	 * @return ciphertext as a BigInteger
	 */
	public BigInteger Encryption(BigInteger m) {
		BigInteger r = new BigInteger(bitLength, new Random());
		return g.modPow(m, nsquare).multiply(r.modPow(n, nsquare)).mod(nsquare);

	}

	/**
	 * Decrypts ciphertext c. plaintext m = L(c^lambda mod n^2) * u mod n, where u = (L(g^lambda mod n^2))^(-1) mod n.
	 *
	 * @param c ciphertext as a BigInteger
	 * @return plaintext as a BigInteger
	 */
	public BigInteger Decryption(BigInteger c) {
		BigInteger u = g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).modInverse(n);
		return c.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
	}

	public BigInteger getRandom(int start, int end) {
		//        int num=(int) (Math.random()*(end-start+1)+start);
		Random random = new Random();
		int num = random.nextInt(end - start + 1) + start;
		num = 65537;
		BigInteger bit = new BigInteger(String.valueOf(num));
		return bit;
	}

	public String add(String encryptedValue, String encryptedValue2) {
		BigInteger em1 = new BigInteger(encryptedValue);
		BigInteger em2 = new BigInteger(encryptedValue2);
		BigInteger sum = em1.multiply(em2).mod(nsquare);
		return sum.toString();
	}


	public String multiply(String encryptedValue, BigInteger m2) {
		BigInteger em1 = new BigInteger(encryptedValue);
		//        BigInteger m2 = new BigInteger(String.valueOf(originallValue));
		BigInteger product = em1.modPow(m2, nsquare);
		return product.toString();
	}


	public static void main(String[] args) {
		BigPaillier bigPaillier = new BigPaillier();
		Map<String, String> keysMap = bigPaillier.generateKeys();
		System.out.println(keysMap);
		String sk = keysMap.get("sk");
		String pk = keysMap.get("pk");
        System.out.println("使用方王玮私钥:" + sk);
        System.out.println("使用方王玮公钥:" + pk);

        BigPaillier bigPaillier2 = new BigPaillier();
        Map<String, String> keysMap2 = bigPaillier.generateKeys();
        System.out.println(keysMap);
        String sk2 = keysMap.get("sk");
        String pk2 = keysMap.get("pk");
        System.out.println("使用方王玮2私钥:" + sk);
        System.out.println("使用方王玮2公钥:" + pk);

		String encrypt1 = bigPaillier.Encryption(new BigInteger("2"), pk);
		System.out.println("数据提供方1:"+encrypt1);

        String encrypt2 = bigPaillier.Encryption(new BigInteger("3"), pk);
        System.out.println("数据提供方2:"+encrypt1);

        String result=bigPaillier.add(encrypt1, encrypt2);
        System.out.println("使用方加法后密文:"+result);

        BigInteger resultBI = bigPaillier.Decryption(result, sk);
        System.out.println("明文:"+resultBI.toString());

        if (sk.equals(sk2)) {
            System.out.println("~~~~~~~~~一样一样滴~~~~~~~~~~");
        }
        if (pk.equals(pk2)) {
            System.out.println("~~~~~~~~~一样一样滴~~~~~~~~~~");
        }
	}

}