package com.code.common.utils.encode;



import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;


public class Md5Provider {

	public static String md5string(String s) {
		try {
			byte[] e = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(e);
			byte[] md = mdTemp.digest();
			return bytesToHexString(md);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 获取一个"随机的"md5字符串
	 * 
	 * @return
	 */
	public static String getRandomMd5string() {
		String time = System.currentTimeMillis() + "";
		return Md5Provider.md5string(Md5Provider.md5string(time).hashCode() + time);
	}

	public static String md5File(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return "文件不存在:"+path;
		}
		if (!file.isFile()) {
			return "非文件:"+path;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			return "";
		}
		return bytesToHexString(digest.digest());
		
	}
	
	private static String bytesToHexString(byte[] src) {
	    StringBuilder stringBuilder = new StringBuilder("");
	    if (src == null || src.length <= 0) {
	        return null;
	    }
	    for (int i = 0; i < src.length; i++) {
	        int v = src[i] & 0xFF;
	        String hv = Integer.toHexString(v);
	        if (hv.length() < 2) {
	            stringBuilder.append(0);
	        }
	        stringBuilder.append(hv);
	    }
	    return stringBuilder.toString();
	}
	

}
