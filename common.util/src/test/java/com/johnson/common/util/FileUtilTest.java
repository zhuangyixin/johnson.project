package com.johnson.common.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileUtilTest {
	private static final String PATH = getPath();

	@Test
	public void test_Read() {
		String str = FileUtil.read(PATH, "TestFile.txt");
		assertNotNull(str);
		assertEquals(str, "Hello World");
	}

	private static String getPath() {
		String path = FileUtilTest.class.getResource("/").getPath();
		path = path.substring(0, path.length() - 1);
		return path;
	}
}
