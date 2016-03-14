package com.law.commons.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.commons.lang.StringEscapeUtils;


public class StringUtil {

	/**
	 * 将字符串解析为long型
	 * 
	 * @param source
	 *            要解析的字符串
	 * @return 返回字符串所表示的long，如果解析错误返回long所能表示的最小值。
	 */
	public static long convt2long(String source) {
		long result = Long.MIN_VALUE;
		if (source != null && source.length() > 0) {
			try {
				result = Long.parseLong(source);
			} catch (NumberFormatException e) {
				result = Long.MIN_VALUE;
			}
		}
		return result;
	}

	/**
	 * 将字符串解析为long型
	 * 
	 * @param source
	 *            要解析的字符串
	 * @return 返回字符串所表示的long，如果解析错误返回0。
	 */
	public static long string2long(String source) {
		long result = 0l;
		if (source != null && source.length() > 0) {
			try {
				result = Long.parseLong(source.trim());
			} catch (NumberFormatException e) {
				result = 0l;
			}
		}
		return result;
	}
	

	/**
	 * 将字符串解析为long型
	 * 
	 * @param source
	 *            要解析的字符串
	 * @return 返回字符串所表示的long，如果解析错误返回0。
	 */
	public static int string2int(String source) {
		int result = 0;
		if (source != null && source.length() > 0) {
			try {
				result = Integer.parseInt(source.trim());
			} catch (NumberFormatException e) {
				result = 0;
			}
		}
		return result;
	}

	/**
	 * 将字符串解析为long型
	 * 
	 * @param source
	 *            要解析的字符串
	 * @return 返回字符串所表示的double，如果解析错误返回0。
	 */
	public static double string2double(String source) {
		double result = 0l;
		if (source != null && source.length() > 0) {
			try {
				result = Double.parseDouble(source);
			} catch (NumberFormatException e) {
				result = 0l;
			}
		}
		return result;
	}

	
	/**
	 * 将字符串解析为long型
	 * 
	 * @param source
	 *            要解析的字符串
	 * @return 返回字符串所表示的float，如果解析错误返回0。
	 */
	public static float string2float(String source) {
		float result = 0f;
		if (source != null && source.length() > 0) {
			try {
				result = Float.parseFloat(source);
			} catch (NumberFormatException e) {
				result = 0f;
			}
		}
		return result;
	}

	/**
	 * 将字符串解析为int型
	 * 
	 * @param source
	 *            要解析的字符串
	 * @return 返回字符串所表示的int，如果解析错误返回int所能表示的最小值。
	 */
	public static int convt2int(String source) {
		int result = Integer.MIN_VALUE;
		if (source != null && source.length() > 0) {
			try {
				result = Integer.parseInt(source);
			} catch (NumberFormatException e) {
				result = Integer.MIN_VALUE;
			}
		}
		return result;
	}

	/**
	 * 将汉字转换成16进制编码
	 * 
	 * @param source
	 *            要转换的汉字
	 * @param charsetName
	 *            字符集名称
	 * @return 转换后的编码
	 */
	public static String toUnicode(String source, String charsetName) {
		try {
			StringBuilder builder = new StringBuilder();
			source = new String(source.getBytes(), charsetName);
			for (int i = 0; i < source.length(); i++) {
				char c = source.charAt(i);
				int intAsc = (int) c;
				if (intAsc > 128) {
					builder.append("&#x");
					builder.append(Integer.toHexString(intAsc));
					builder.append(";");
				} else {
					builder.append(c);
				}
			}
			return builder.toString();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return source;
	}

	/**
	 * 将字符串进行base64编码
	 * 
	 * @param source
	 *            要编码的字符串
	 * @return 编码后的字符串
	 */
	public static String base64Encode(String source) {
		if (source == null) {
			source = "";
		}
		char[] t = Base64.encode(source.getBytes());
		return new String(t);
	}

	/**
	 * 将字符串进行base64解码
	 * 
	 * @param source
	 *            要解码的字符串
	 * @return 解码后的字符串
	 */
	public static String base64Decode(String source) {
		if (source == null) {
			source = "";
		}
		byte[] t = Base64.decode(source.toCharArray());
		return new String(t);
	}

	/**
	 * 随机生成length长度的字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomStr(int length) {
		char sigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		Random random = new Random();
		if (length <= 0) {
			return "";
		}
		char randomStr[] = new char[length];
		for (int i = 0; i < length; i++) {
			randomStr[i] = sigits[random.nextInt(sigits.length)];
		}
		return new String(randomStr);
	}


 

	public static void main(String[] args) throws IOException {

		System.out.println(string2long("+86186118787300000000"));
		System.out.println(StringUtil
				.toUnicode("test汉**字100!@#$%^&*()", "UTF8"));
		System.out.println(StringEscapeUtils.escapeXml("good&amp;&测试<Omg"));
		String x = StringUtil.base64Encode("汉字");
		System.out.println(StringUtil.base64Decode(x));
	}
}
