package com.johnson.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

public class DateUtilTest {
	@Ignore
	@Test
	public void test_removeChinaDst() throws ParseException {
		SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = formatter.parse("1986-05-04 00:00:00");
		System.out.println(outputFormatter.format(date));

		date = DateUtil.removeChineseDst(date);
		System.out.println(outputFormatter.format(date));

		date = formatter.parse("1986-05-05 00:00:00");

		date = DateUtil.removeChineseDst(date);
		System.out.println(outputFormatter.format(date));
	}

	@Test
	public void test_removeChinaDsts() throws ParseException {
		SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 1; i < 31; i++) {
			Date date = formatter.parse("1987-09-0" + i + " 00:00:00");
			System.out.println(outputFormatter.format(date));
		}
//
//		date = DateUtil.removeChineseDst(date);
//		System.out.println(outputFormatter.format(date));
//
//		date = formatter.parse("1986-05-05 00:00:00");
//
//		date = DateUtil.removeChineseDst(date);
//		System.out.println(outputFormatter.format(date));
	}
}
