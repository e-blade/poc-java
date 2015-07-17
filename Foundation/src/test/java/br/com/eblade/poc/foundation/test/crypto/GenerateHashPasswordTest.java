package br.com.eblade.poc.foundation.test.crypto;

import static org.junit.Assert.*;
import org.junit.Test;

import br.com.eblade.poc.foundation.crypto.StringEncoder;

public class GenerateHashPasswordTest {

	@Test
	public void testHashWasGenerateWithSuccess() {
		String password = "1234";
		assertNotNull(StringEncoder.encode(password));
	}
	
	@Test
	public void testValidIfPasswordIsValidAfterHashed() {
		String pass = "brazil";
		String hash = StringEncoder.encode(pass);
		boolean valid = StringEncoder.isValid(pass, hash);
		assertTrue(valid);
	}
	
	@Test
	public void testPassIsNotEqualsToHashcodeGenerated() {
		String password = "XPto";
        String passwordhash = StringEncoder.encode(password);
        String passwordTyped = "xPto";
        boolean valid = StringEncoder.isValid(passwordTyped, passwordhash);
        assertFalse(valid);
	}

}
