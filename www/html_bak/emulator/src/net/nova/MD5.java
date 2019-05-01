package net.nova;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String encode(String word) {
		String wordEncrypt = "";
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] wordDigest = md5.digest(word.getBytes());
			StringBuffer wordConstruct = new StringBuffer();
			int wordLength = wordDigest.length;
			
			for(int i = 0; i < wordLength; i++)
				wordConstruct.append(Integer.toHexString((wordDigest[i] & 0xFF) | 0x100).substring(1, 3));
			
			wordEncrypt = wordConstruct.toString();
		} catch (NoSuchAlgorithmException e) {
		}
		
		return wordEncrypt;
	}
}
