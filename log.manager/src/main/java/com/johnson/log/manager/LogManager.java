package com.johnson.log.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogManager {
	private static final Logger LOGGER = LoggerFactory.getLogger("LogManager");

	public static void debug(String format, Object... args) {
		LOGGER.debug(format, args);
	}

	public static void info(String format, Object... args) {
		LOGGER.info(format, args);
	}

	public static void warn(String format, Object... args) {
		LOGGER.warn(format, args);
	}

	public static void error(String format, Object... args) {
		LOGGER.error(format, args);
	}
}
