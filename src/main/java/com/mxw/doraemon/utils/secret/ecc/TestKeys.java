package com.mxw.doraemon.utils.secret.ecc;

import com.mxw.doraemon.utils.secret.ecc.ecdsa.PrivateKey;
import com.mxw.doraemon.utils.secret.ecc.ecdsa.PublicKey;

/**
 * @program: doraemon
 * @description: GenerateKeys
 * @author: AlanMa
 * @create: 2020-10-12 11:38
 */
public class TestKeys {

	private static String priKey = "MHcCAQEEIJw7junIWA0Cj1WfASB6yGmBe1+4mYtTxPTl0XcO8K8poAoGCCqGSM49AwEHoUQDQgAEzMW736pFydtDkU+3KIk8wAgQR8i7drXznimBdWt8mSZqdE4L0WamsSgbc0DN7e491ZnR2Fm67S7JpSrRJAmsrQ==";
	private static String pubKey = "BMzFu9+qRcnbQ5FPtyiJPMAIEEfIu3a1854pgXVrfJkmanROC9FmprEoG3NAze3uPdWZ0dhZuu0uyaUq0SQJrK0=";

	public static void main(String[] args) {
		// PrivateKey privateKey = null;
		// try {
		// 	privateKey = PrivateKey.fromString(new ByteString(Base64.decode(priKey.toString())));
		// 	PublicKey publicKey = PublicKey.fromString(new ByteString(Base64.decode(pubKey.toString())));
		// 	// PublicKey publicKey = privateKey.publicKey();
		// 	// System.out.println(publicKey.toPem());
		// 	String message = "Hello World 麻欣伟~";
		//
		// 	Signature signature = Ecdsa.sign(message, privateKey);
		//
		// 	boolean verified = Ecdsa.verify(message, signature, publicKey);
		//
		// 	System.out.println("Verified: " + verified);
		// } catch (IOException e) {
		// 	e.printStackTrace();
		// }


		TestKeys.testOrig();

	}


	private static void testOrig() {
		// Generate Keys
		PrivateKey privateKey = new PrivateKey();
		PublicKey publicKey = privateKey.publicKey();
		// System.out.println("priKey:" + privateKey.toDer());
		System.out.println("pubKey:" + publicKey.toPubString());
		System.out.println("test~~~~~~~~~~");
		// System.out.println("pubKey:" + publicKey.toDer().toString());

		// String message = "Testing message";
		// // Generate Signature
		// Signature signature = Ecdsa.sign(message, privateKey);
		//
		// // Verify if signature is valid
		// boolean verified = Ecdsa.verify(message, signature, publicKey);
		//
		// // Return the signature verification status
		// System.out.println("Verified: " + verified);
	}
}
