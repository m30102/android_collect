package com.fan.collect.main.study.image;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class ImageUtil {

    public static boolean fileSaveToPublic(Context mContext, Bitmap bitmap) {
        String imgPath =
                + new Date().getTime() + ".jpg";
        //Android 10及以上版本
        //设置路径 Pictures/
//        String folder = Environment.DIRECTORY_PICTURES;
//        String folder = Environment.DIRECTORY_DCIM;
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
        Uri uri = mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        OutputStream os = null;
        try {
            if (uri != null) {
                //若生成了uri，则表示该文件添加成功
                //使用流将内容写入该uri中即可
                os = mContext.getContentResolver().openOutputStream(uri);
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
        mContext.sendBroadcast(intent);
        return true;
    }
}
