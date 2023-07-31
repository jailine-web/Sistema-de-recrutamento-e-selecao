package com.example.app.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

	//Converte a senha do usuário
	public static  String md5(String senha) throws NoSuchAlgorithmException{
		MessageDigest dis = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, dis.digest(senha.getBytes()));
		return hash.toString(16);
	}
	
	
}
