package cn.sf.ss.util;

public class StringUtil {
	public static String nullString(String s) {
		return s;
	}
	
	public static String nullStringTrim(String s) {
		if (null == s) {
			return null;
		} else {
			return s.trim();
		}
	}
}
