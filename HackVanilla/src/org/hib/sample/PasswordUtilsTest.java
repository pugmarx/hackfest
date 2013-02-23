package org.hib.sample;

import org.junit.Test;

/**
 * 
 * @author syed_rizvi
 * 
 */
public class PasswordUtilsTest {

	@Test
	public void testDecryption() {
		String toDecrypt = "rbV665WJHzRBmbtgDopxFdyBckGFeTCG";
		System.out.println(PasswordUtils.decrypt(toDecrypt));
	}
	
	@Test
	public void testEncryption() {
		String toEncrypt = "test";
		System.out.println(PasswordUtils.encrypt(toEncrypt));
	}
}
