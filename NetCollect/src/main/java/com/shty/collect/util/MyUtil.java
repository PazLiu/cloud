package com.shty.collect.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class MyUtil {

	public static Map<Integer, Long> THREAD_END_TIME_MAP = Collections.synchronizedMap(new HashMap<Integer, Long>());

	public static Long THREAD_START_TIME;

	public static int THREAD_NUMBER = 10;

	// public static String BASE_SAVE_URL = "E:/CollectImg/linkedin_img";
	public static String BASE_SAVE_URL = ResourceBundle.getBundle("systemconfig").getString("imgPath") + "linkedin_img";

	public static String BASE_SAVE_T_URL = "E:/CollectImg/twitter_img";

	public static String BASE_SAVE_F_URL = "E:/CollectImg/facebook_img";

	// public static String BASE_IMG_URL = "linkedin_img";
	public static String BASE_IMG_URL = "";

	public static int processcount = 0; // 需要处理的数据条数

	public static String byteToString(byte[] param) {

		String result = "";

		byte[] conbyte = param;
		for (int i = 0; i < conbyte.length; i++) {
			if ((conbyte[i] & 0xF8) == 0xF0) {
				for (int j = 0; j < 4; j++) {
					conbyte[i + j] = 0x30;
				}
				i += 3;
			}
		}
		try {
			result = new String(conbyte, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.replace("0000", "");
		conbyte = null;

		return result;

	}

	// 解析facebook大头像
	public static String fbImgDecodeAndSave(String imgcode, String uuid) {
		byte[] decodeByteArray = null;
		String imgName = "/images/facebookimg/" + uuid + "/" + uuid + "_main.jpg";
		// String imgName = "/home/linkedinimg/"+ lnkid + "/" + lnkid +
		// "_main.jpg";
		try {
			InputStream input = MyStringUtils.decompress(imgcode.getBytes("iso-8859-1"));
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
			decodeByteArray = Base64.decodeBase64(output.toByteArray());

			FileUtils.writeByteArrayToFile(new File(BASE_SAVE_F_URL + imgName), decodeByteArray);
			// FileUtils.copyInputStreamToFile(MyStringUtils.decompressBig(decodeByteArray),new
			// File(BASE_SAVE_URL+imgName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
		return imgName;

	}

	// 解析facebook小头像
	public static String fbImgDecodeAndSave(String imgcode, String uuid, int num, String type) {
		byte[] decodeByteArray = null;
		String imgName = "/images/facebookimg/" + uuid + "/" + type + "/" + uuid + "_" + type + num + ".jpg";
		try {
			InputStream input = MyStringUtils.decompress(imgcode.getBytes("iso-8859-1"));
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
			decodeByteArray = Base64.decodeBase64(output.toByteArray());

			FileUtils.writeByteArrayToFile(new File(BASE_SAVE_F_URL + imgName), decodeByteArray);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
		return imgName;

	}

	// 解析大头像
	public static String imgDecodeAndSave(String imgcode, String lnkid, int version) {
		byte[] decodeByteArray = null;
		String path = "/images/linkedinimg/" + lnkid + "_" + version + "/";
		String fullName = path + lnkid + "_main.jpg";

		try {
			InputStream input = MyStringUtils.decompress(imgcode.getBytes("iso-8859-1"));
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
			decodeByteArray = Base64.decodeBase64(output.toByteArray());

			// FileUtils.writeByteArrayToFile(new File(BASE_SAVE_URL + imgName),
			// decodeByteArray);

			FileUpload.smbPut(BASE_SAVE_URL, decodeByteArray, path, fullName);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return fullName;

	}

	// 解析小头像
	public static String imgDecodeAndSave(String imgcode, String lnkid, int num, String type, int version) {
		byte[] decodeByteArray = null;
		String path = "/images/linkedinimg/" + lnkid + "_" + version + "/" + type + "/";
		String fullName = path + lnkid + "_" + type + num + ".jpg";
		try {
			InputStream input = MyStringUtils.decompress(imgcode.getBytes("iso-8859-1"));

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
			decodeByteArray = Base64.decodeBase64(output.toByteArray());

			// FileUtils.writeByteArrayToFile(new File(BASE_SAVE_URL + imgName),
			// decodeByteArray);

			FileUpload.smbPut(BASE_SAVE_URL, decodeByteArray, path, fullName);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return fullName;
	}
}
