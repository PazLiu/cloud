package com.shty.collect.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

public class MyStringUtils extends StringUtils {

	private final static String ascSpace = Jsoup.parse("&nbsp").text();

	/**
	 * Test to see if a string is an empty string
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}

		return false;
	}

	/**
	 * trims white spaces from a String. Use it instead of String.trim() to deal
	 * with null scenarios.
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (str == null) {
			return null;
		}

		return str.trim();
	}

	/**
	 * trims sql statements where it ends with , or AND.
	 * 
	 * @param sql
	 * @return
	 */
	public static String trimSQLExtra(String sql) {
		if (!isNullOrEmpty(sql) && sql.length() > 1) {
			if (sql.endsWith(",")) {
				return sql.substring(0, sql.length() - 1);
			} else if (sql.endsWith("AND")) {
				return sql.substring(0, sql.length() - 3);
			}
		}

		return sql;
	}

	/**
	 * Convert a stack trace into a string
	 * 
	 * @param t
	 * @return
	 */
	public static String stackTraceToString(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);

		return sw.toString();
	}

	public static String trimHtmlExtra(String echo) {
		String result = StringUtils.replace(echo, ascSpace, " ");
		return StringUtils.trimToEmpty(result);
	}

	private static int ld(String str1, String str2) {
		// Distance
		int[][] d;
		int n = str1.length();
		int m = str2.length();
		int i; // iterate str1
		int j; // iterate str2
		char ch1; // str1
		char ch2; // str2
		int temp;
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) {
			d[i][0] = i;
		}
		for (j = 0; j <= m; j++) {
			d[0][j] = j;
		}
		for (i = 1; i <= n; i++) {
			ch1 = str1.charAt(i - 1);
			// match str2
			for (j = 1; j <= m; j++) {
				ch2 = str2.charAt(j - 1);
				if (ch1 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}

				d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
			}
		}
		return d[n][m];
	}

	private static int min(int one, int two, int three) {
		int min = one;
		if (two < min) {
			min = two;
		}
		if (three < min) {
			min = three;
		}
		return min;
	}

	public static double sim(String str1, String str2) {
		int ld = ld(str1, str2);
		return 1 - (double) ld / Math.max(str1.length(), str2.length());
	}

	/*
	 * Normally used for phone# formating, return all numbers contained in the
	 * given string
	 */
	public static String extractAllDigit(String str) {
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();

	}

	/*
	 * Normally used to extract profile_id number for URL, return the first
	 * matcher only
	 */
	public static String getContinuousNumbers(String str) {
		String target = null;
		String regex = "\\d+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if (m.find()) {
			target = m.group(0);
		}
		return target;
	}

	// JSON浼犲浘鐗囨祴璇�
	public static byte[] inputStreamToByteArray(InputStream input) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		;
		byte[] data = null;
		try {
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = input.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}

			data = baos.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return data;
	}

	/**
	 * 瀵筨yte[]杩涜鍘嬬缉
	 * 
	 * @param 瑕佸帇缂╃殑鏁版嵁
	 * @return 鍘嬬缉鍚庣殑鏁版嵁
	 */
	public static byte[] compress(byte[] data) {
		GZIPOutputStream gzip = null;
		ByteArrayOutputStream baos = null;
		byte[] newData = null;

		try {
			baos = new ByteArrayOutputStream();
			gzip = new GZIPOutputStream(baos);

			gzip.write(data);
			gzip.finish();
			gzip.flush();

			newData = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				gzip.close();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return newData;
	}

	public static InputStream decompressBig(byte[] byteArray) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
		GZIPInputStream gunzip = new GZIPInputStream(in);
		// byte[] buffer = new byte[256];
		// int n;
		// while ((n = gunzip.read(buffer))>= 0) {
		// out.write(buffer, 0, n);
		// }
		// toString()浣跨敤骞冲彴榛樿缂栫爜锛屼篃鍙互鏄惧紡鐨勬寚瀹氬toString(&quot;GBK&quot;)
		return gunzip;
	}

	public static InputStream decompress(byte[] byteArray) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
		// GZIPInputStream gunzip = new GZIPInputStream(in);
		// byte[] buffer = new byte[256];
		// int n;
		// while ((n = gunzip.read(buffer))>= 0) {
		// out.write(buffer, 0, n);
		// }
		// toString()浣跨敤骞冲彴榛樿缂栫爜锛屼篃鍙互鏄惧紡鐨勬寚瀹氬toString(&quot;GBK&quot;)
		return in;
	}

	public static void testImgDecode(String ss) {
		try {
			byte[] decodeByteArray = Base64.decodeBase64(ss);

			String fileName = "/Users/david/Sony/D_Disk/MyWorkSheet/fbstandalone/debug/imgcode_James";

			FileUtils.copyInputStreamToFile(MyStringUtils.decompress(decodeByteArray), new File(fileName));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String ss = "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";
		testImgDecode(ss);
	}
}
