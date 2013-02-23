package org.hib.sample;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * 
 * @author syed_rizvi
 * 
 */
public class PasswordUtils {
	private static final String DEFAULT_PASSWORD = "solid";

	public static String encrypt(String plainText) {
		return encrypt(plainText, DEFAULT_PASSWORD);
	}

	public static String decrypt(String encryptedText) {
		return decrypt(encryptedText, DEFAULT_PASSWORD);
	}

	public static String encrypt(String plainText, String encryptingPassword) {
		String encrypted = "";
		if (plainText == null || plainText.trim().length() == 0) {
			return encrypted;
		}
		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
		strongEncryptor.setPassword(encryptingPassword);
		encrypted = strongEncryptor.encrypt(plainText);
		return encrypted;
	}

	public static String decrypt(String encryptedText, String decryptingPassword) {
		String decrypted = "";
		if (encryptedText == null || encryptedText.trim().length() == 0) {
			return decrypted;
		}
		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
		strongEncryptor.setPassword(decryptingPassword);
		decrypted = strongEncryptor.decrypt(encryptedText);
		return decrypted;
	}

}
