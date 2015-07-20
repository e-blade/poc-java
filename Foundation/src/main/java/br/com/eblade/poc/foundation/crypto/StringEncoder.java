package br.com.eblade.poc.foundation.crypto;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class StringEncoder {

	// salt is used with null value
	private static Object salt;
	private static String algorithm = "MD5";
	
	public static String encode(String pass) {		
		MessageDigestPasswordEncoder encoder = getInstance();		
		String encodedPass = encoder.encodePassword(pass, salt);		
		return encodedPass;		
	}
	
	public static boolean isValid(String pass, String encodedPass) {		
		MessageDigestPasswordEncoder encoder = getInstance();
		return encoder.isPasswordValid(encodedPass, pass, salt);
	}
	
	// Factory
	private static MessageDigestPasswordEncoder getInstance() {		
		MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder(algorithm);		
		return encoder;
	}	
		
}
