package com.river.browser.security;

import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
	
	public static void main(String[] args) {
		MyPasswordEncoder mm = new MyPasswordEncoder();
		System.out.println(mm.encode("123456"));
	}

	@Override
	public String encode(CharSequence rawPassword) {
		String result = null;
		// TODO Auto-generated method stub
		try {
			result = KeyStore.encryptMD5(rawPassword.toString().getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		boolean flag = false;
		try {
			System.out.println(KeyStore.encryptMD5(rawPassword.toString().getBytes()));
			System.out.println(encodedPassword);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if (KeyStore.encryptMD5(rawPassword.toString().getBytes()).equalsIgnoreCase(encodedPassword))
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

}
