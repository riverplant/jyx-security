package com.river.browser.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author user3
 *
 */
public class KeyStore {
	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";
	/**
	 * 
	 * HmacMD5 
	 * HmacSHA1 
	 * HmacSHA256 
	 * HmacSHA384 
	 * HmacSHA512
	 * 
	 */
	public static final String KEY_MAC = "HmacMD5";
	/**
	 * decrypteBASE64
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static byte[] decrypteBASE64(String key) throws IOException{
		return (new BASE64Decoder()).decodeBuffer(key);
	}
	/**
	 * encrypBASE64
	 * @param key
	 * @return
	 */
	public static String encrypBASE64(byte[]key){
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	/**encryptMD5
	 * 
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptMD5(byte[] data) throws NoSuchAlgorithmException{
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
	
		md5.update(data);
		byte[] byteArray = md5.digest();  
		  
        StringBuilder md5StrBuff = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++)  {
        	if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
        		md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
        	}else{
        		md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i])); 
        	}
        }
		return md5StrBuff.toString();
	}
	/**
	 * initMacKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String initMacKey() throws NoSuchAlgorithmException{
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
		SecretKey secretKey = keyGenerator.generateKey();
		return encrypBASE64(secretKey.getEncoded());
	}
	/**
	 * encryHMAC
	 * @param data
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static byte[] encryHMAC(byte[] data,String key) throws IOException, NoSuchAlgorithmException, InvalidKeyException{
		SecretKey secretKey = new SecretKeySpec(decrypteBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}
}
