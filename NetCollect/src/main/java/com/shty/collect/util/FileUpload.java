package com.shty.collect.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class FileUpload {

	public static void main(String[] args) {

		String remoteUrl = "smb://192.168.191.111/share";
		String localFilePath = "C:/Users/ljr/Desktop/pyOut/qq.jpg";
		smbPut(remoteUrl, localFilePath);
	}

	/**
	 * Description: 从本地上传文件到共享目录
	 * 
	 * @param remoteUrl
	 *            共享文件目录
	 * @param localFilePath
	 *            本地文件绝对路径
	 */
	public static boolean smbPut(String remoteUrl, String localFilePath) {
		InputStream in = null;
		OutputStream out = null;
		try {
			File localFile = new File(localFilePath);
			String fileName = localFile.getName();
			SmbFile remoteFile = new SmbFile(remoteUrl + "/" + fileName);
			in = new BufferedInputStream(new FileInputStream(localFile));
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
			byte[] buffer = new byte[1024];
			while (in.read(buffer) != -1) {
				out.write(buffer);
				buffer = new byte[1024];
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Description: 从二进制上传文件到共享目录
	 * 
	 * @param remoteUrl
	 *            共享文件目录
	 * @param localFilePath
	 *            本地文件绝对路径
	 */
	public static void smbPut(String remoteUrl, byte[] img, String path, String fullName) {

		OutputStream out = null;

		try {
			SmbFile remoteFileDir = new SmbFile(remoteUrl + path);

			if (!remoteFileDir.exists())
				remoteFileDir.mkdirs();

			SmbFile remoteFile = new SmbFile(remoteUrl + fullName);
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile, true));
			out.write(img);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
