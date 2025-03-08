/**
 * @Description:TODO
 * @author:fan
 * @time:2017年9月4日 下午5:33:52
 */
package com.code.common.utils.misc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

//此类 23以上再使用
@SuppressLint("NewApi")
public class PermissionUtils {
	
	
	/**
	 * 使用的时候注意要用Build.VERSION.SDK_INT 做版本判断，此类23及以上才能使用
	 * @param activity
	 * @param permissions
	 * @return code  是否正在申请
	 */
	public static boolean requestPermissions(Activity activity, String[] permissions, int code)
	{	 
		
		 List<String> permissionList = new ArrayList<>();
		 for (int i =0 ; i< permissions.length;i++){
			 	int checkSelfPermission = activity.checkSelfPermission(permissions[i]);
	            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED){
	                permissionList.add(permissions[i]);
	            }
	      }
		 String[] s = new String[permissionList.size()];
//		 FLogger.d("hf_utils", "需要申请的个数:"+s.length);
		 if(permissionList.size() >0) {
			 activity.requestPermissions(permissionList.toArray(s),code);
			 UIUtil.toastOnMain(activity, "请允许权限,拒绝可能导致无法正常使用~");
			 return true;
		 }else {
			 return false;
		 }
	}
	public static boolean requestPermissions(Fragment fragment, String[] permissions, int code)
	{	 
		
		 List<String> permissionList = new ArrayList<>();
		 for (int i =0 ; i< permissions.length;i++){
			 	int checkSelfPermission = fragment.getActivity().checkSelfPermission(permissions[i]);
	            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED){
	                permissionList.add(permissions[i]);
	            }
	      }
		 String[] s = new String[permissionList.size()];
		 FLogger.d("hf_utils", "需要申请的个数:"+s.length);
		 if(permissionList.size() >0) {
			 fragment.requestPermissions(permissionList.toArray(s),code);
			 UIUtil.toastOnMain(fragment.getActivity(), "请允许权限,拒绝可能导致无法正常使用~");
			 return true;
		 }else {
			 return false;
		 }
	}

	
	

}

