package com.csuft.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

	
	public static String sha256(String password){
		String encrypt = "";
		try {
			byte[] encryptByte = MessageDigest.getInstance("SHA-256").digest(password.getBytes());
			encrypt = new BigInteger(1, encryptByte).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encrypt;
	}
	
//	public static void main(String[] args) {
//		String str = "abc";
//		str = SHA256.sha256(str);
//		System.out.println(str);
//	}
}
