package com.code.common.utils.misc;



import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

public class ManifestUtil {

	static Bundle metaData;
	/**
	 * 注意如果配置的参数如果大于int最大值，则会返回错误的结果
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static int getMetaInt(Context context, String key) {
		if (metaData != null) {
			return metaData.getInt(key);
		}
		PackageManager pm = context.getPackageManager();
		ApplicationInfo appinfo;
		try {
			appinfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			metaData = appinfo.metaData;
			return metaData.getInt(key);
		} catch (Exception e) {
			FLogger.Ex(FLogger.UTIL_TAG, e);
		}
		return 0;
	}
	public static long getMetaILong(Context context, String key) {
		if (metaData != null) {
			return metaData.getInt(key);
		}
		PackageManager pm = context.getPackageManager();
		ApplicationInfo appinfo;
		try {
			appinfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			metaData = appinfo.metaData;
			return metaData.getLong(key);
		} catch (Exception e) {
			FLogger.Ex(FLogger.UTIL_TAG, e);
		}
		return 0;
	}
	
	/**
	 * 
	 * @param context
	 * @param key
	 * @return 默认false
	 */
	public static boolean getMetaBoolean(Context context, String key, boolean defValue) {
		if (metaData != null) {
			return metaData.getBoolean(key, defValue);
		}
		PackageManager pm = context.getPackageManager();
		ApplicationInfo appinfo;
		try {
			appinfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			metaData = appinfo.metaData;
			return metaData.getBoolean(key, defValue);
		} catch (Exception e) {
			FLogger.Ex(FLogger.UTIL_TAG, e);
		}
		return false;
	}
	
	public static String getMetaString(Context context, String key) {
		if (metaData != null) {
			return metaData.getString(key);
		}
		PackageManager pm = context.getPackageManager();
		ApplicationInfo appinfo;
		try {
			appinfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			metaData = appinfo.metaData;
			String t = metaData.getString(key);
			if(TextUtils.isEmpty(t)) {
				t = "";
			}
			return t;
		} catch (Exception e) {
			FLogger.Ex(FLogger.UTIL_TAG, e);
		}
		return "";
	}
	
	
	public static String getMetaMsg(Context context, String key) {
		if (metaData != null) {
			Object object = metaData.get(key);
			if(object == null) {
				return "";
			}
			return object+"";
		}
		PackageManager pm = context.getPackageManager();
		ApplicationInfo appinfo;
		try {
			appinfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			metaData = appinfo.metaData;
			return metaData.get(key)+"";
		} catch (Exception e) {
			FLogger.Ex(FLogger.UTIL_TAG, e);
		}
		return "";
	}
	
	
	public static int getVersionCode(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			int version = info.versionCode;
			return version;
		} catch (Exception e) {
			FLogger.Ex(FLogger.UTIL_TAG, e);
		}
		return 1;
	}
	
	public static String getVersionName(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			FLogger.Ex(FLogger.UTIL_TAG, e);
		}
		return "1.0";
	}
	
	public static String getAppName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			ApplicationInfo applicationInfo = context.getApplicationInfo();
			return (String)applicationInfo.loadLabel(packageManager);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
