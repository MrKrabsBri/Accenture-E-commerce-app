package com.server.server;

import static org.junit.jupiter.api.Assertions.*;

import com.server.server.passwordEncoding.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
class ServerApplicationTests {

	@Autowired
	private PasswordEncoder passwordHashing;

	@Test
	void contextLoads() {
	}

	@Test
	public void testPasswordLengthLessThan8(){

	}

	@Test
	public void testPasswordLengthMoreThan30(){

	}

	@Test
	public void testPasswordWithNoSpecialSymbols(){

	}

	@Test
	public void testPasswordWithNoNumbers(){

	}

	@Test
	public void testPasswordHashingSecurePassword() throws NoSuchAlgorithmException {

		String password = "TikraiSaugusSlaptazodis123!???";
		String expectedHash = "9fe0fa0d45c9b4c3abf83cb733993fa1f5c180043c189de3bc1207550cdfe235";
		String actualHashedPassword = passwordHashing.hashPassword(password);

		assertEquals(expectedHash, actualHashedPassword);

	}

	@Test
	public void testPasswordHashingShorterThan8Symbols() throws NoSuchAlgorithmException {


	}


}
