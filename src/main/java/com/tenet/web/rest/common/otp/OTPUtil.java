package com.tenet.web.rest.common.otp;

import java.security.SecureRandom;

public class OTPUtil {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String generateOTP(int lenth) {
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		while (lenth-- != 0) {
			int randomIndex = random.nextInt(ALPHA_NUMERIC_STRING.length());
			sb.append(ALPHA_NUMERIC_STRING.charAt(randomIndex));
		}

		return sb.toString();
	}

}
