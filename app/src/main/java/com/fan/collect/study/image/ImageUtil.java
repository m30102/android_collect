package com.fan.collect.study.image;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class ImageUtil {

    public static boolean saveToGallery(Context context, Bitmap bitmap){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
            return saveToGalleryBeforeQ(context,bitmap);
        }else{
            return saveToGalleryAfterQ(context,bitmap);
        }
    }
    public static boolean saveToGalleryBeforeQ(Context context, Bitmap bitmap){
        File directory_pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(directory_pictures,"tmp.png");
        Log.e("ImageUtil","imageFile:" + imageFile.getAbsolutePath());
        try {
            String uri = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, null, null);
            Log.e("ImageUtil","uri:" +uri);
            if(!TextUtils.isEmpty(uri)){
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,Uri.parse(uri)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public static boolean saveToGalleryAfterQ(Context context, Bitmap bitmap) {
        String imgPath = new Date().getTime() + ".jpg";
        //Android 10及以上版本
        //设置路径 Pictures/
        String folder = Environment.DIRECTORY_PICTURES;
        //设置保存参数到ContentValues中
        ContentValues values = new ContentValues();
        //设置图片名称
        values.put(MediaStore.Images.Media.DISPLAY_NAME, imgPath);
        //设置图片格式
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        //设置图片路径
        values.put(MediaStore.Images.Media.RELATIVE_PATH, folder);
        //执行insert操作，向系统文件夹中添加文件
        //EXTERNAL_CONTENT_URI代表外部存储器，该值不变
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        OutputStream os = null;
        try {
            if (uri != null) {
                //若生成了uri，则表示该文件添加成功
                //使用流将内容写入该uri中即可
                os = context.getContentResolver().openOutputStream(uri);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        context.sendBroadcast(intent);
        return true;
    }

    public static void saveToShareDir(Context context, Bitmap bitmap) {
        File share = new File(context.getExternalFilesDir(""), "tmp.png");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(share);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void share(Context context) {
        File shareImg = new File(context.getExternalFilesDir(""), "tmp.png");
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".FileProvider", shareImg);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
        } else {
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(shareImg));
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setType("image/png");
        context.startActivity(Intent.createChooser(intent, "分享到..."));
    }
}
