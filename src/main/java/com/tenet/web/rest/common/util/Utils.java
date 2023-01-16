package com.tenet.web.rest.common.util;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.tenet.web.rest.common.entity.MassTime;

public class Utils {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int EXPIRE_TOKEN_AFTER_MINUTES = 5;
	private static final String SFX = "SFX";
	private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("ddMMMyy");
	private static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hhmm");
	private static final String BK = "BK";

	public static String generateOTP(int lenth) {
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		while (lenth-- != 0) {
			int randomIndex = random.nextInt(ALPHA_NUMERIC_STRING.length());
			sb.append(ALPHA_NUMERIC_STRING.charAt(randomIndex));
		}

		return sb.toString();
	}

	public static String generateToken() {
		StringBuilder token = new StringBuilder();
		return token.append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString()).toString();
	}

	public static boolean isTokenExpired(LocalDateTime tokenCreationTime) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationTime, now);
		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}

	public static String generateMassBookingNo(MassTime massTime, long sequenceNo) {
		StringBuilder sb = new StringBuilder(SFX);
		LocalDate date = massTime.getDate();
		sb.append(date.format(DATE_FORMATTER).toUpperCase());
		LocalTime time = massTime.getTime();
		sb.append(time.format(TIME_FORMATTER));
		sb.append(BK);
		sb.append(sequenceNo);
		return sb.toString();
	}

}
