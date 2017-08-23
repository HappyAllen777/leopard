package com.geelun.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

public class StringUtil {

	public static boolean contains(final CharSequence source, final CharSequence keyword) {
		if (source == null || keyword == null) {
			return false;
		}
		return source.toString().indexOf(keyword.toString(), 0) > 0;
	}

	public static boolean equals(CharSequence str1, CharSequence str2) {
		if (str1 == str2) {
			return true;
		}
		if (str1 == null || str2 == null) {
			return false;
		}
		if (str1.length() != str2.length()) {
			return false;
		}
		return str1.equals(str2);
	}

	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static boolean isNotEmpty(final CharSequence cs) {
		return !isEmpty(cs);
	}

	public static String replaceAll(String source, String target, String replacement) {
		if (source == null || source == null || replacement == null) {
			return source;
		}
		return source.replaceAll(source, replacement);
	}

	public static boolean isBlank(final CharSequence cs) {
		return StringUtils.isBlank(cs);
	}

	public static boolean isNotBlank(final CharSequence cs) {
		return !isBlank(cs);
	}

	public static StringBuffer deleteLastChar(StringBuffer stringBuffer) {
		return stringBuffer.deleteCharAt(stringBuffer.length() - 1);
	}

	public static StringBuffer replaceLastChar(StringBuffer stringBuffer, String replacement) {
		return deleteLastChar(stringBuffer).append(replacement);
	}

	public static int getRealLength(String string) {
		if (StringUtil.isEmpty(string)) {
			return 0;
		}
		return string.length();
	}

	public static boolean isDate(String value) {
		// Todo:
		return false;
	}

	public static boolean isInt(String value) {
		if (isEmpty(value)) {
			return false;
		}
		final char[] chars = value.toCharArray();
		int sz = chars.length;
		boolean hasExp = false;
		boolean hasDecPoint = false;
		boolean allowSigns = false;
		boolean foundDigit = false;
		// deal with any possible sign up front
		final int start = chars[0] == '-' || chars[0] == '+' ? 1 : 0;
		final boolean hasLeadingPlusSign = start == 1 && chars[0] == '+';
		if (sz > start + 1 && chars[start] == '0') { // leading 0
			if (chars[start + 1] == 'x' || chars[start + 1] == 'X') { // leading
																		// 0x/0X
				int i = start + 2;
				if (i == sz) {
					return false; // str == "0x"
				}
				// checking hex (it can't be anything else)
				for (; i < chars.length; i++) {
					if ((chars[i] < '0' || chars[i] > '9') && (chars[i] < 'a' || chars[i] > 'f') && (chars[i] < 'A' || chars[i] > 'F')) {
						return false;
					}
				}
				return true;
			} else if (Character.isDigit(chars[start + 1])) {
				// leading 0, but not hex, must be octal
				int i = start + 1;
				for (; i < chars.length; i++) {
					if (chars[i] < '0' || chars[i] > '7') {
						return false;
					}
				}
				return true;
			}
		}
		sz--; // don't want to loop to the last char, check it afterwords
				// for type qualifiers
		int i = start;
		// loop to the next to last char or to the last char if we need another
		// digit to
		// make a valid number (e.g. chars[0..5] = "1234E")
		while (i < sz || i < sz + 1 && allowSigns && !foundDigit) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				foundDigit = true;
				allowSigns = false;

			} else if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					// two decimal points or dec in exponent
					return false;
				}
				hasDecPoint = true;
			} else if (chars[i] == 'e' || chars[i] == 'E') {
				// we've already taken care of hex.
				if (hasExp) {
					// two E's
					return false;
				}
				if (!foundDigit) {
					return false;
				}
				hasExp = true;
				allowSigns = true;
			} else if (chars[i] == '+' || chars[i] == '-') {
				if (!allowSigns) {
					return false;
				}
				allowSigns = false;
				foundDigit = false; // we need a digit after the E
			} else {
				return false;
			}
			i++;
		}
		if (i < chars.length) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				if (SystemUtils.IS_JAVA_1_6 && hasLeadingPlusSign && !hasDecPoint) {
					return false;
				}
				// no type qualifier, OK
				return true;
			}
			if (chars[i] == 'e' || chars[i] == 'E') {
				// can't have an E at the last byte
				return false;
			}
			if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					// two decimal points or dec in exponent
					return false;
				}
				// single trailing decimal point after non-exponent is ok
				return foundDigit;
			}
			if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F')) {
				return foundDigit;
			}
			if (chars[i] == 'l' || chars[i] == 'L') {
				// not allowing L with an exponent or decimal point
				return foundDigit && !hasExp && !hasDecPoint;
			}
			// last character is illegal
			return false;
		}
		// allowSigns is true iff the val ends in 'E'
		// found digit it to make sure weird stuff like '.' and '1E-' doesn't
		// pass
		return !allowSigns && foundDigit;
	}

}
