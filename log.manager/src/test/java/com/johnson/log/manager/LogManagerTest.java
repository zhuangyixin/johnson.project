package com.johnson.log.manager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnson.log.manager.LogManager;

public class LogManagerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogManagerTest.class);

	@Test
	public void testInfo() {
		LogManager.debug("This is {}.", "LogDemo test");
		LogManager.info("This is {}.", "LogDemo test");
		LogManager.warn("This is {}.", "LogDemo test");
		LogManager.error("This is {}.", "LogDemo test");

		LOGGER.debug("This is {}.", "LogDemo test");
		LOGGER.info("This is {}.", "LogDemo test");
		LOGGER.warn("This is {}.", "LogDemo test");
		LOGGER.error("This is {}.", "LogDemo test");
	}
}
