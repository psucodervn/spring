package vn.uts.facebookgetfeed.facebook;

import java.util.Date;

/**
 * @author Hung
 *
 */
public class Converter {

	/**
	 * @param date
	 * @return time stamp
	 */
	public static Long dateToTimeStamp(Date date) {
		return date.getTime() / 1000;
	}
}
