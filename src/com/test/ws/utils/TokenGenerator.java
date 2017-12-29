package com.test.ws.utils;

import java.security.SecureRandom;
import java.util.UUID;

public class TokenGenerator {

	protected static SecureRandom random = new SecureRandom();

	public synchronized static String generateToken(String username) {
		long longToken = Math.abs(random.nextLong());
		String random = Long.toString(longToken, 16);
		return (username + ":" + random);
	}

	public static String uniqueUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
