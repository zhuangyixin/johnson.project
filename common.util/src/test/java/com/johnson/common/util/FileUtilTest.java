package com.johnson.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Ignore;
import org.junit.Test;

public class FileUtilTest {
	private static final String PATH = getPath();

	@Test
	@Ignore
	public void test_Read() throws FileNotFoundException {
		String str = FileUtil.read(PATH, "TestFile.txt");
		assertNotNull(str);
		assertEquals(str, "Hello World");
	}

	@Test
	public void test_InputStreamAvailable() throws IOException {
		InputStream inputStream = new FileInputStream(new File(getPath() + File.separator + "test.html"));
		assertNotNull(inputStream);
		System.out.println(inputStream.available() + "");
	}

	private static String getPath() {
		String path = FileUtilTest.class.getResource("/").getPath();
		path = path.substring(0, path.length() - 1);
		return path;
	}
}
