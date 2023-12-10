package com.server.server.passwordSecurityTest;

import static org.junit.jupiter.api.Assertions.*;

import com.server.server.passwordEncoding.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
class ServerApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	public void testPasswordLengthLessThan10() {
		String shortPassword = "7symbol";
		assertThrows(IllegalArgumentException.class,
				() -> passwordEncoder.passwordLengthIsCorrect(shortPassword));

	}

	@Test
	public void testPasswordLengthMoreThan30() {
		String longPassword = "waytooManySymbols12345678999987875ddas!@#!@$!@@@ssaaaasymbol";
		assertThrows(IllegalArgumentException.class,
				() -> passwordEncoder.passwordLengthIsCorrect(longPassword));

	}

	@Test
	public void testPasswordWithNoSpecialSymbols() {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		String password = "Passwordls123456aasymbol";
		assertThrows(IllegalArgumentException.class,
				() -> passwordEncoder.passwordContainsSpecialCharacter(password));
	}

	@Test
	public void testPasswordWithASpecialSymbol() {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		String password = "Passwordls1234/5\6aasymbol/";
		assertDoesNotThrow(() -> passwordEncoder.passwordContainsSpecialCharacter(password));
	}

	@Test
	public void testPasswordWithNoNumbers() {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		String passwordNoNumbers = "VerySafePasswordššįčę!???#/saa";
		assertThrows(IllegalArgumentException.class,
				() -> passwordEncoder.passwordContainsANumber(passwordNoNumbers));
	}

	@Test
	public void testPasswordWithSpecialSymbols() {
		PasswordEncoder passwordEncoder = new PasswordEncoder();
		String passwordSymbols = "A1~!@#$%^&*()_+{}:|<>?%ė%//!";
		assertDoesNotThrow(() -> passwordEncoder.passwordContainsSpecialCharacter(passwordSymbols));
	}

	@Test
	public void testPasswordHashingSecurePassword() throws NoSuchAlgorithmException {

		String password = "TikraiSaugusSlaptazodis123!???";
		String expectedHash = "9fe0fa0d45c9b4c3abf83cb733993fa1f5c180043c189de3bc1207550cdfe235";
		String actualHashedPassword = passwordEncoder.hashPassword(password);
		assertEquals(expectedHash, actualHashedPassword);

	}

	@Test
	public void testPasswordHashingShorterThan8Symbols() throws NoSuchAlgorithmException {

		String password = "Short1!";
		assertThrows(IllegalArgumentException.class,
				() -> passwordEncoder.hashPassword(password));

	}

}
