package com.code.common.utils.misc;


/**
 * @Description:TODO
 * @author:fan
 * @time:2017年8月22日 下午1:57:47
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class UIUtil {
	
	private static Toast toast;

	public static void runUI(Activity activity, Runnable runnable) {
		activity.runOnUiThread(runnable);
	}

	public static void toastShortOnMain(final Activity activity, final String msg) {
		toastOnMain(activity, msg);
		/*
		 * 华为机上可能有问题
		 * runUI(activity, new Runnable() {
			@Override
			public void run() {
				if (toast == null) {
					toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
				} else {
					toast.setText(msg);
				}
				toast.show();
			}
		});
*/
	}

	public static void toastOnMain(final Activity activity, final String msg) {

		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
			}
		});

	}

	public static WindowManager getWindowManager(Context context) {
		return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	}

	public static int getScreenHeight(Context context) {
		return getPoint(getWindowManager(context)).y;
	}

	public static int getScreenWidth(Context context) {
		return getPoint(getWindowManager(context)).x;
	}

	private static Point getPoint(WindowManager windowManager) {
		Display display = windowManager.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return size;
	}

	public static boolean isOritationVertical(Context context) {
		int orientation = context.getResources().getConfiguration().orientation;
		if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
			return false;
		} else {
			return true;
		}
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
	
	public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (spValue * fontScale + 0.5f);  
    }  
	
}
