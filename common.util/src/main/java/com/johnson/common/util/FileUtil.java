package com.johnson.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class FileUtil {

	private static final String DEFAULT_ENCODE = "UTF-8";

	public static String read(String path, String fileName) {
		return read(path, fileName, DEFAULT_ENCODE);
	}

	public static String read(String path, String fileName, String encode) {
		File file = new File(path + File.separator + fileName);
		if (!file.exists()) {
			return null;
		}
		StringBuilder strBuilder = new StringBuilder();
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			int character = 0;
			while ((character = reader.read()) != -1) {
				strBuilder.append((char) character);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strBuilder.toString();
	}

	public static boolean write(String path, String fileName, byte[] content) {
		File file = new File(path + File.pathSeparator + fileName);
		if (file.getParent() == null) {
			new File(path).mkdirs();
		}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
