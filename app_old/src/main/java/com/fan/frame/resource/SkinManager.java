package com.fan.frame.resource;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import java.lang.reflect.Method;

public class SkinManager {

    private static SkinManager skinManager = new SkinManager();
    public static SkinManager getInstance(){
        return skinManager;
    }

    private Context context;

    private Resources resources;
    // 获取资源包的包名
    private String packageName;

    public void setContext(Context context){
        this.context = context;
    }
    public void loadSkinApk(String path){
        // 加载皮肤资源包
        PackageManager packageManager = context.getPackageManager();
        PackageInfo archiveInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        packageName = archiveInfo.packageName;

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager,path);
            resources = new Resources(assetManager,context.getResources().getDisplayMetrics(),context.getResources().getConfiguration());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // 用来去资源包中的资源对象中匹配

    /**
     * @param resId 当前app中的资源id
     * @return
     */
    public int getColor(int resId){
        if(resources == null) return context.getResources().getColor(resId);
        // 匹配,得到原资源名字和类型
        // 获取到属性的值的名字 colorAccent colorPrimary
        String resourceEntryName = context.getResources().getResourceEntryName(resId);
        // color drawable
        String resourceTypeName = context.getResources().getResourceTypeName(resId);
        // 去匹配皮肤资源包的id ctx.getResources().getIdentifier(name, "color", ctx.getPackageName());
        int identifier = resources.getIdentifier(resourceEntryName, resourceTypeName, packageName);
        // 没有匹配到
        if(identifier == 0){
            return resId;
        }
        return resources.getColor(identifier);
    }


    public int getDrawableId(int resId){
        if(resources == null){
            return resId;
        }
        // 匹配,得到原资源名字和类型
        // 获取到属性的值的名字 colorAccent colorPrimary
        String resourceEntryName = context.getResources().getResourceEntryName(resId);
        // color drawable
        String resourceTypeName = context.getResources().getResourceTypeName(resId);
        // 去匹配皮肤资源包的id ctx.getResources().getIdentifier(name, "color", ctx.getPackageName());
        int identifier = resources.getIdentifier(resourceEntryName, resourceTypeName, packageName);
        // 没有匹配到
        if(identifier == 0){
            return resId;
        }
        return identifier;
    }

    public Drawable getDrawable(int resId){
        if(resources == null)return ContextCompat.getDrawable(context,resId);
        // 匹配,得到原资源名字和类型
        // 获取到属性的值的名字 colorAccent colorPrimary
        String resourceEntryName = context.getResources().getResourceEntryName(resId);
        // color drawable
        String resourceTypeName = context.getResources().getResourceTypeName(resId);
        // 去匹配皮肤资源包的id ctx.getResources().getIdentifier(name, "color", ctx.getPackageName());
        int identifier = resources.getIdentifier(resourceEntryName, resourceTypeName, packageName);
        // 没有匹配到
        if(identifier == 0){
            return ContextCompat.getDrawable(context,resId);
        }
        return resources.getDrawable(identifier);
    }
}
