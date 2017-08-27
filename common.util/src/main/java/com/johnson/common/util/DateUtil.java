package com.johnson.common.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * China used to implemented daylight saving time(DST). Therefore, if you parse
	 * a character string(for example, 1986-5-4 00:00:00) or read time from
	 * database. There may be dst offset. The method can remove this offset.
	 */
	public static Date removeChineseDst(Date originDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(originDate);
		int dstOffset = calendar.get(Calendar.DST_OFFSET);
		int month = calendar.get(Calendar.MONTH);

		if (dstOffset > 0 && 3 <= month && month <= 4) {
			calendar.add(Calendar.HOUR_OF_DAY, -1);
			if (calendar.get(Calendar.DST_OFFSET) == 0) {
				return originDate;
			}
			calendar.add(Calendar.HOUR_OF_DAY, 1);
		}
		calendar.add(Calendar.MILLISECOND, dstOffset);
		return calendar.getTime();
	}
}
