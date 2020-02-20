package com.orderSystem.util;

public class MStr {

	public static String noNull(String s) {
		return s == null ? "" : s;
	}

	public static String noNull(Object s) {
		String s1 = "";
		try {
			s1 = s.toString();
		} catch (Exception e) {
			s1 = "";
		}
		return s1;
	}

	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	public static boolean isNull(String s) {
		return s == null;
	}

	public static String trim(String s) {
		if (s == null || s.length() == 0) {
			return s;
		} else {
			return s.trim();
		}
	}

	public static String trimNoNull(String s) {
		if (s == null || s.length() == 0) {
			return "";
		} else {
			return s.trim();
		}
	}

	public static String toString(int i) {
		return String.valueOf(i);
	}

	public static String toString(double d) {
		return String.valueOf(d);
	}

	public static String toString(String d) {
		return d;
	}

	public static boolean isNick(String str) {
		if (str == null)
			return false;
		return str.matches("^[\\u4e00-\\u9fa5|\\w]+$");
	}

	public static boolean isEmail(String str) {
		if (str == null)
			return false;
		return str.matches("^[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+$");
	}

	public static boolean isNumber(String str) {
		return str.matches("^[0-9\\.]+$");
	}

	public static boolean isInteger(String str) {
		if (str == null)
			return false;
		return str.matches("^\\d+$");
	}

	/**
	 * @param len
	 *            需要显示的长度(注意：长度是以byte为单位的，一个汉字是2个byte)
	 * @param symbol
	 *            用于表示省略的信息的字符，如“...”,“>>>”等。
	 * @return 返回处理后的字符串
	 */
	public static String byteCutLen(String str, int len, String symbol) {
		int c = 0;
		String s = null;
		if (symbol == null)
			symbol = "";
		try {
			byte[] b = str.getBytes("GBK");
			if (b.length <= len)
				return str;
			if (symbol.length() < b.length) {
				len = len - symbol.length();
			} else {
				symbol = "";
			}
			for (int i = 0; i < len; i++) {
				if (b[i] < 0)
					c++;
			}
			if (c % 2 == 0) {
				s = new String(b, 0, len, "GBK") + symbol;
			} else {
				s = new String(b, 0, len - 1, "GBK") + symbol;
			}
		} catch (Exception e) {
			s = str;
			e.printStackTrace();
		}
		return s == null ? "" : s;
	}

	public static String charCutLen(String str, int len, String symbol) {
		if (symbol == null)
			symbol = "";
		if (str == null) {
			return "";
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(0, len) + symbol;

	}

	public static int getByteLen(String str) {
		if (str == null)
			return 0;
		int length = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) > 255) {
				length += 2;
			} else {
				length++;
			}
		}
		return length;
	}
}
