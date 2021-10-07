package com.mxw.doraemon.utils.secret.ecc.ecdsap256;

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.ECPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/**
 * @program: doraemon
 * @description:
 * @author: AlanMa
 * @create: 2020-10-12 20:46
 */
public class ECDSA {

	public static PublicKey bytesToPublicKey(byte[] x509key) throws GeneralSecurityException {
		X509EncodedKeySpec spec = new X509EncodedKeySpec(x509key);
		KeyFactory factory = KeyFactory.getInstance("EC");
		ECPublicKey publicKey = (ECPublicKey) factory.generatePublic(spec);
		//We should be able to use these X and Y in Go to build the public key
		BigInteger x = publicKey.getW().getAffineX();
		BigInteger y = publicKey.getW().getAffineY();
		System.out.println(publicKey.toString());
		return publicKey;
	}

	//we can either use the Java standard signature ANS1 format output, or just take the R and S parameters from it, and pass those to Go
	//https://stackoverflow.com/questions/48783809/ecdsa-sign-with-bouncycastle-and-verify-with-crypto
	public static BigInteger extractR(byte[] signature) throws Exception {
		int startR = (signature[1] & 0x80) != 0 ? 3 : 2;
		int lengthR = signature[startR + 1];
		return new BigInteger(Arrays.copyOfRange(signature, startR + 2, startR + 2 + lengthR));
	}

	public static BigInteger extractS(byte[] signature) throws Exception {
		int startR = (signature[1] & 0x80) != 0 ? 3 : 2;
		int lengthR = signature[startR + 1];
		int startS = startR + 2 + lengthR;
		int lengthS = signature[startS + 1];
		return new BigInteger(Arrays.copyOfRange(signature, startS + 2, startS + 2 + lengthS));
	}

	public static byte[] signMsg(String msg, PrivateKey priv) throws Exception {
		Signature ecdsa = Signature.getInstance("SHA1withECDSA");

		ecdsa.initSign(priv);

		byte[] strByte = msg.getBytes("UTF-8");
		ecdsa.update(strByte);

		byte[] realSig = ecdsa.sign();

		//this is the R and S we could also pass as the signature
		System.out.println("R: "+extractR(realSig));
		System.out.println("S: "+extractS(realSig));

		return realSig;
	}
}
