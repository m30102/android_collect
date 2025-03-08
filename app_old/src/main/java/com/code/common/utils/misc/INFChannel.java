package com.code.common.utils.misc;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 签名后META-INF下存放的文件信息
 * 
 * @author Administrator
 */
public class INFChannel {
	private static final String CHANNEL_KEY = "hfchannel";
	private static final String CHANNEL_VERSION_KEY = "hfchannel_version";
	private static String mChannel;
	public static String defaultChannel = "10000000";
	public static String defaultOtherInfo = "0";//0：无属性（空字符串）1：联运（联运）2：11游戏（11游戏）3：EUI（EUI）
	private static String mOtherInfo;

	/**
	 * 返回市场。 如果获取失败返回""
	 *
	 * @param context
	 * @return
	 */
	public static String getChannel(Context context) {
		return getChannel(context, defaultChannel);
	}

	/**
	 * 返回市场。 如果获取失败返回defaultChannel
	 *
	 * @param context
	 * @param defaultChannel
	 * @return
	 */
	public static String getChannel(Context context, String defaultChannel) {
		// 内存中获取
		if (!TextUtils.isEmpty(mChannel)) {
			return mChannel;
		}
		// 从apk中获取
		mChannel = getChannelFromApk(context, CHANNEL_KEY);
		if (!TextUtils.isEmpty(mChannel)) {
			// 保存sp中备用 ？？？
			saveChannelBySharedPreferences(context, mChannel);
			return mChannel;
		}
		// 全部获取失败
		return defaultChannel;
	}

	/**
	 * 从apk中获取版本信息
	 *
	 * @param context
	 * @param channelKey
	 * @return
	 */
	private static String getChannelFromApk(Context context, String channelKey) {
		// 从apk包中获取
		ApplicationInfo appinfo = context.getApplicationInfo();
		String sourceDir = appinfo.sourceDir;
		// 默认放在meta-inf/里， 所以需要再拼接一下
		String key = "META-INF/" + channelKey;
		String ret = "";
		ZipFile zipfile = null;
		try {
			zipfile = new ZipFile(sourceDir);
			Enumeration<?> entries = zipfile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = ((ZipEntry) entries.nextElement());
				String entryName = entry.getName();
				if (entryName.contains("../")) {
					break;
				}
				if (entryName.startsWith(key)) {
					ret = entryName;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (zipfile != null) {
				try {
					zipfile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		String[] split = ret.split("_");
		String channel = "";
		if (split != null) {
			if (split.length == 2) {
				channel = ret.substring(split[0].length() + 1);// ???
			} else if (split.length >= 2) {
				channel = split[1];
			}
		}
		return channel;
	}

	/**
	 * 本地保存channel & 对应版本号
	 *
	 * @param context
	 * @param channel
	 */
	private static void saveChannelBySharedPreferences(Context context, String channel) {
		try {
			SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
			Editor editor = sp.edit();
			editor.putString(CHANNEL_KEY, channel);
			editor.putInt(CHANNEL_VERSION_KEY, getVersionCode(context));
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从包信息中获取版本号
	 *
	 * @param context
	 * @return
	 */
	private static int getVersionCode(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 获取下发地址
	 *
	 * @param context
	 * @return
	 */
	public static String getOtherInfo(Context context) {
		if (!TextUtils.isEmpty(mOtherInfo)) {
			return mOtherInfo;
		}
		mOtherInfo = getOtherInfoFromApk(context, CHANNEL_KEY);
		if (!TextUtils.isEmpty(mOtherInfo)) {
			return mOtherInfo;
		}
		return defaultOtherInfo;
	}

	private static String getOtherInfoFromApk(Context context, String channelKey) {
		String otherInfo = "";
		try {
			ApplicationInfo appInfo = context.getApplicationInfo();
			String sourceDir = appInfo.sourceDir;
			// 默认放在meta-inf/里， 所以需要再拼接一下
			String key = "META-INF/" + channelKey;
			String ret = "";
			ZipFile zipfile = null;
			try {
				zipfile = new ZipFile(sourceDir);
				Enumeration<?> entries = zipfile.entries();
				while (entries.hasMoreElements()) {
					ZipEntry entry = ((ZipEntry) entries.nextElement());
					String entryName = entry.getName();
					if (entryName.startsWith(key)) {
						ret = entryName;
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (zipfile != null) {
					try {
						zipfile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			String[] split = ret.split("_");
			if (split != null && split.length >= 3) {
				otherInfo = split[2];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otherInfo;
	}
}
