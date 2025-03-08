package com.code.common.utils.misc;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;

import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

/**
 * @author fan
 * @version 1.0
 * @time 2017年8月10日 下午10:22:37
 */
public class PhoneInfo {

	private TelephonyManager mTelephonyManager;
	private static volatile PhoneInfo instance;
	private static Context context;

	/** 手机品牌 **/
	public static String brand = Build.BRAND;
	/** 手机型号 **/
	public static String model = Build.MODEL;

	/** 安卓api level 25 **/
	public int androidLevel = Build.VERSION.SDK_INT;
	/** 安卓版本 7.1 **/
	public String androidVersion = Build.VERSION.RELEASE;

	/** 手机识别码IMEI */
	public String IMEI = "000000000000000";
	/** 移动用户识别码 */
	public String IMSI = "ffffffffffffffff";
	/** 运营商 */
	public String operatCodeStr = "1";

	public String android_Id = "";
	/** 分辨率 */
	public String resolution = "";
	/**序列号*/
	public String serial = "";


	public String mac = "";
	// 计算一个唯一标识(相同手机可能重复)
	public String device_UUID = "";
	// 用于本地保存一个唯一标识(卸载后失效)
	public String uuidstr;
	// 语言
	public String lang;

	public static PhoneInfo getInstance(Context ctx) {

		if (context == null) {
			context = ctx.getApplicationContext();
		}




		if (instance == null) {
			synchronized (PhoneInfo.class) {
				if (instance == null) {
					instance = new PhoneInfo(ctx);
				}
			}
		}
		return instance;
	}

	private PhoneInfo(Context ctx) {
		context = ctx.getApplicationContext();
		try {
			mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		} catch (Exception e) {
			FLogger.Ex(FLogger.UTIL_TAG, e);
		}
		collectMachineInfomation();
	}

	/**
	 * 收集手机信息
	 */
	private void collectMachineInfomation() {

		try {
			uuidstr = generateUUID();
			IMEI = getIMEI();
			IMSI = getIMSI();
			android_Id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
			if (TextUtils.isEmpty(android_Id)) {
				android_Id = "default";
			}
		} catch (Exception e) {
			FLogger.Ex(FLogger.UTIL_TAG, e);
		}
		device_UUID = getDeviceUUID();
		resolution = getResolution();
		operatCodeStr = getOperator() + "";
		lang = getLanguage();
		serial = getSerial();
		mac = NetWorkUtils.getMacAddr(context);

	}

	@SuppressLint("MissingPermission")
	@TargetApi(26)
	private String getSerial() {
		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				return Build.getSerial();
			} else {
				return Build.SERIAL;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "serial";
	}

	public String getLanguage() {
		try {
			String locale = Locale.getDefault().getLanguage();
			return locale;
		} catch (Exception e) {
			FLogger.e(FLogger.UTIL_TAG, e.getMessage());
		}
		return "zh_CN";
	}

	@SuppressLint("NewApi")
	private String getDeviceUUID() {
		String serial = null;
		String m_szDevIDShort = "35";
		try {
			m_szDevIDShort = "" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

					Build.DEVICE.length() % 10 +

					Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

					Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

					Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

					Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

					Build.USER.length() % 10;
			if (Build.VERSION.SDK_INT > 21) {
				int x = Arrays.toString(Build.SUPPORTED_ABIS).length() % 10;
				m_szDevIDShort = m_szDevIDShort + x;
			} else {
				m_szDevIDShort = m_szDevIDShort + Build.CPU_ABI.length() % 10;
			}

			serial = getSerial();
			return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
		} catch (Exception e) {
			FLogger.e(FLogger.UTIL_TAG, e.getMessage());
			serial = "serial";
		}
		return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
	}

	// 获取不到就用UUID 代替.
	@SuppressLint("MissingPermission")
	@TargetApi(26)
	private String getIMEI() {

		// 获取imei越来严格
		String res = "";
		try {
			if (null != mTelephonyManager) {
				if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
					res = mTelephonyManager.getImei();
				else
					res = mTelephonyManager.getDeviceId();
			}
			
		} catch (Exception e) {
			FLogger.e(FLogger.UTIL_TAG, e.getMessage());
			res = uuidstr;
		}

		if (TextUtils.isEmpty(res)) {
			res = uuidstr;
		}
		// 小于15位 不认为是IMEI
		if (res.length() < 15) {
			res = uuidstr;
		}
		return res;
	}

	@SuppressLint("MissingPermission")
	private String getIMSI() {
		String res = "ffffffffffffffff";
		try {
			if (null != mTelephonyManager) {
				res = mTelephonyManager.getSubscriberId();
			}
		} catch (Exception e) {
			FLogger.e(FLogger.UTIL_TAG, e.getMessage());
		}
		return res;
	}

	/**
	 * 获取屏幕分辨率
	 * 
	 * @return
	 */
	private String getResolution() {
		try {
			WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			Point point = new Point();
			wm.getDefaultDisplay().getSize(point);
			int screenWidth = point.x;
			int screenHeight = point.y;
			if (screenHeight < screenWidth) {
				return screenWidth + "x" + screenHeight;
			} else {
				return screenHeight + "x" + screenWidth;
			}
		} catch (Exception e) {
			FLogger.e(FLogger.UTIL_TAG, e.getMessage());
		}
		return "0x0";
	}


	/**
	 * 获取当前的运营商
	 * 
	 * @return 运营商名字
	 */
	private int getOperator() {
		Oper pro = Oper.OTHER;
		if (!TextUtils.isEmpty(IMSI)) {
			if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007")
					|| IMSI.startsWith("46020")) {
				pro = Oper.CHINA_MOBILE;
			} else if (IMSI.startsWith("46001") || IMSI.startsWith("46006") || IMSI.startsWith("46009")) {
				pro = Oper.CHINA_UNI;
			} else if (IMSI.startsWith("46003") || IMSI.startsWith("46005") || IMSI.startsWith("46011")) {
				pro = Oper.CHCHINATELE;
			}
		}
		return pro.getCode();
	}

	// 1、移动；2、联通；3、电信；4、其他
	enum Oper {
		CHINA_MOBILE(1), CHINA_UNI(2), CHCHINATELE(3), OTHER(4);
		int code;

		Oper(int code) {
			this.code = code;
		}

		protected int getCode() {
			return code;
		}
	}

	private String generateUUID() {
		String uuid_sp = SharePreferencesUtil.getString(context, SharePreferencesUtil.UUID);
		if (TextUtils.isEmpty(uuid_sp)) {
			String uuid = UUID.randomUUID().toString().replace("-", "");
			FLogger.e(FLogger.UTIL_TAG, "本地没有保存UUID,重新生成保存：" + uuid);
			SharePreferencesUtil.setString(context, SharePreferencesUtil.UUID, uuid);
			return uuid;
		} else {
			FLogger.e(FLogger.UTIL_TAG, "本地已保存有UUID:" + uuid_sp);
			return uuid_sp;
		}
	}

}
