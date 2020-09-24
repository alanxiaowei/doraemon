package com.mxw.doraemon.key;

import ft.otp.verify.OTPVerify;

import java.util.Map;
import java.util.Scanner;

/********************************************************************

 RFC4226 JAVA SAMPLE RESULT:
 Secret = 0x3132333435363738393031323334353637383930

 Count    HOTP
 0        755224
 1        287082
 2        359152
 3        969429
 4        338314
 5        254676
 6        287922
 7        162583
 8        399871
 9        520489

 /////////////////////////////////////////////////

 TOTP JAVA SAMPLE RESULT:
 Secret = 0x3132333435363738393031323334353637383930
 T0 = UTC EPOCH
 X = 30

 +------------+-----------------------+------------------+--------+
 | Unix Time  |       UTC Time        |    Value of T    |  OTP   |
 +------------+-----------------------+------------------+--------+
 | 1000000000 |  2001-09-09 01:46:40  | 0000000001FCA055 | 864010 |
 +------------+-----------------------+------------------+--------+
 | 1111111111 |  2005-03-18 01:58:31  | 00000000023523ED | 050471 |
 +------------+-----------------------+------------------+--------+
 | 1234567890 |  2009-02-13 23:31:30  | 000000000273EF07 | 005924 |
 +------------+-----------------------+------------------+--------+
 | 2000000000 |  2033-05-18 03:33:20  | 0000000003F940AA | 279037 |
 +------------+-----------------------+------------------+--------+



 #define OTP_SUCCESS					(0x00000000L) //操作成功

 #define OTP_ERR_INVALID_PARAMETER	(0x00000001L) //参数无效

 #define OTP_ERR_CHECK_PWD			(0x00000002L) //认证失败

 #define OTP_ERR_SYN_PWD				(0x00000003L) //同步失败

 #define OTP_ERR_REPLAY				(0x000000004) //同一个动态口令被多次重试
 *******************************************************************/

public class Demo {

	private Demo() {
	}

	//手机令牌验证接口调用
	private static void testCheckPwdC200() {
		String seed = "560AEB58419E246D0F6581EA023FC167E3B886B2";
		String sOTP = "";
		Map hashMap;
		Scanner sc = new Scanner(System.in);
		int iDrift = 0;
		long lSucc = 0;


		System.out.println("demo ITSecurity_CheckPwdC200,input:\"quit\" to end");

		System.out.print("please input otp:");
		while (!((sOTP = sc.next()).equals("quit"))) {
			//System.out.println(sOTP);


			hashMap = OTPVerify.ITSecurity_CheckPwdC200(seed,                                    //令牌密钥
					System.currentTimeMillis() / 1000,        //调用本接口计算机的当前时间
					0,                                        //给0
					60,                                        //给60，因为每60秒变更新的动态口令
					iDrift,                                //漂移值，用于调整硬件与服务器的时间偏差，见手册说明
					20,                                        //认证窗口，见手册说明
					lSucc,                                    //成功值，用于调整硬件与服务器的时间偏差，见手册说明
					sOTP);                                    //要认证的动态口令OTP

			Long nReturn = (Long) hashMap.get("returnCode");
			System.out.println("returnCode = " + nReturn);

			if (nReturn == OTPVerify.OTP_SUCCESS) {
				System.out.println("check success");
				System.out.println("currentSucc = " + hashMap.get("currentUTCEpoch"));
				System.out.println("currentDrift = " + hashMap.get("currentDrift"));

				iDrift = ((Long) hashMap.get("currentDrift")).intValue();
				lSucc = ((Long) hashMap.get("currentUTCEpoch")).longValue();

			} else {
				System.out.println("check fail");
			}

			System.out.print("please input otp:");
		}


	}

	//手机令牌同步接口调用
	private static void testSyncPwdC200() {
		String seed = "560AEB58419E246D0F6581EA023FC167E3B886B2";
		String sOTP1 = "";
		String sOTP2 = "";
		Map hashMap;
		Scanner sc = new Scanner(System.in);
		int iDrift = 0;
		long lSucc = 0;
		Long nReturn;

		System.out.println("demo ET_Syncz201,input:\"quit\" to end");


		System.out.print("please input otp1:");
		while (!((sOTP1 = sc.next()).equals("quit"))) {
			System.out.print("please input otp2:");
			sOTP2 = sc.next();


			hashMap = OTPVerify.ITSecurity_PSW_SYNC200(seed, System.currentTimeMillis() / 1000, 0, 60, iDrift, 40, lSucc, sOTP1, sOTP2);

			nReturn = (Long) hashMap.get("returnCode");
			System.out.println("returnCode = " + nReturn);

			if (nReturn == OTPVerify.OTP_SUCCESS) {
				System.out.println("syn success");
				System.out.println("currentSucc = " + hashMap.get("currentUTCEpoch"));
				System.out.println("currentDrift = " + hashMap.get("currentDrift"));

				iDrift = ((Long) hashMap.get("currentDrift")).intValue();
				lSucc = ((Long) hashMap.get("currentUTCEpoch")).longValue();

			} else {
				System.out.println("syn fail");
			}

			System.out.print("please input otp1:");
		}

	}


	//ET z201验证接口调用

	/**
	 * returnCode = 0
	 * check success
	 * currentSucc = 1586498662
	 * currentDrift = 0
	 */
	private static void testCheckPwdz201() {
		//2100000445572 ll4FA6E5F659EA220CBB199EC56F7212C7BA9374A6AF07FAEA
		// 2100000445573 ll97A67284183147AEF171E83727608680B569446C7E8F883B
		String seed = "ll4FA6E5F659EA220CBB199EC56F7212C7BA9374A6AF07FAEA";
		String sOTP = "";
		Map hashMap;
		Scanner sc = new Scanner(System.in);
		int iDrift = 0;
		long lSucc = 0;
		Long nReturn;

		System.out.println("demo ET_CheckPwdz201,input:\"quit\" to end");


		System.out.print("please input otp:");
		while (!((sOTP = sc.next()).equals("quit"))) {
			//System.out.println(sOTP);


			hashMap = OTPVerify.ET_CheckPwdz201(seed,                                    //令牌密钥
					System.currentTimeMillis() / 1000,        //调用本接口计算机的当前时间
					0,                                        //给0
					60,                                        //给60，因为每60秒变更新的动态口令
					iDrift,                                //漂移值，用于调整硬件与服务器的时间偏差，见手册说明
					120,                                        //认证窗口，见手册说明
					lSucc,                                    //成功值，用于调整硬件与服务器的时间偏差，见手册说明
					sOTP);                                    //要认证的动态口令OTP

			nReturn = (Long) hashMap.get("returnCode");
			System.out.println("returnCode = " + nReturn);

			if (nReturn == OTPVerify.OTP_SUCCESS) {
				System.out.println("check success");
				System.out.println("currentSucc = " + hashMap.get("currentUTCEpoch"));
				System.out.println("currentDrift = " + hashMap.get("currentDrift"));

				iDrift = ((Long) hashMap.get("currentDrift")).intValue();
				lSucc = ((Long) hashMap.get("currentUTCEpoch")).longValue();

			} else {
				System.out.println("check fail");
			}

			System.out.print("please input otp:");
		}

	}

	//ET z201同步接口调用
	private static void testSyncPwdz201() {
		String seed = "ll276DFE997728E6E083A2A5162671527348B97092246C9D1D";
		String sOTP1 = "";
		String sOTP2 = "";
		Map hashMap;
		Scanner sc = new Scanner(System.in);
		int iDrift = 0;
		long lSucc = 0;
		Long nReturn;

		System.out.println("demo ET_Syncz201,input:\"quit\" to end");


		System.out.print("please input otp1:");
		while (!((sOTP1 = sc.next()).equals("quit"))) {
			System.out.print("please input otp2:");
			sOTP2 = sc.next();


			hashMap = OTPVerify.ET_Syncz201(seed, System.currentTimeMillis() / 1000, 0, 60, iDrift, 40, lSucc, sOTP1, sOTP2);

			nReturn = (Long) hashMap.get("returnCode");
			System.out.println("returnCode = " + nReturn);

			if (nReturn == OTPVerify.OTP_SUCCESS) {
				System.out.println("syn success");
				System.out.println("currentSucc = " + hashMap.get("currentUTCEpoch"));
				System.out.println("currentDrift = " + hashMap.get("currentDrift"));

				iDrift = ((Long) hashMap.get("currentDrift")).intValue();
				lSucc = ((Long) hashMap.get("currentUTCEpoch")).longValue();

			} else {
				System.out.println("syn fail");
			}

			System.out.print("please input otp1:");
		}

	}

	public static void main(String[] args) {
		//手机令牌
		//System.out.println();
		//testCheckPwdC200();

		//System.out.println();
		//testSyncPwdC200();

		//ET z201
		//System.out.println();
		testCheckPwdz201();

		//System.out.println();
		// testSyncPwdz201();


	}
}

