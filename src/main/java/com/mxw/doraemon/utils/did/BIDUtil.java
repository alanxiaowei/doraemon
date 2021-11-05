package com.mxw.doraemon.utils.did;

import com.mxw.doraemon.utils.hash.SHA256Util;
import com.mxw.doraemon.utils.secret.ecc.ecdsa.PrivateKey;
import com.mxw.doraemon.utils.secret.ecc.ecdsa.utils.Base58;

/**
 * @program: doraemon
 * @description: BID生成工具类
 * @author: AlanMa
 * @create: 2020-12-11 11:04
 */
public class BIDUtil {

	public static String generateBID() {
		//生成原始公私钥对Secp256k1
		PrivateKey privateKey = new PrivateKey();
		byte[] pubKey=privateKey.publicKey().toByteString().getBytes();
		//计算原始公钥的哈希SHA-256
		String pubKeyHash=SHA256Util.getSHA256(pubKey);
		//截取前22位进行进行Base58编码
		String subHash=pubKeyHash.substring(0, 22);
		byte[] hashBase58=Base58.encodeByte(subHash.getBytes());
		// 添加编码类型
		byte[] encodeByte = new byte[hashBase58.length + 1];
		byte[] encodeType = {'f'};
		System.arraycopy(encodeType, 0, encodeByte, 0, encodeType.length);
		System.arraycopy(hashBase58, 0, encodeByte, encodeType.length, hashBase58.length);
		//添加加密算法类型前缀
		byte[] secretByte = new byte[encodeByte.length + 1];
		byte[] secretType = {'s'};
		System.arraycopy(secretType, 0, secretByte, 0, secretType.length);
		System.arraycopy(encodeByte, 0, secretByte, secretType.length, encodeByte.length);
		String subBID=new String(secretByte);
		//添加 ChainCode
		String subBIDChainCode = "byo1:" + subBID;
		//添加前缀
		String bid = "did:bid:" + subBIDChainCode;
		return bid;
	}

	public static void main(String[] args) {
		System.out.println(BIDUtil.generateBID());
		System.out.println(Base58.encode("1234567890123456789012".getBytes()));

	}
}
