package com.fan.collect.module.androidversion.fileprovider;

import android.Manifest;
import android.Manifest.permission;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.hjq.permissions.XXPermissions;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileProviderActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        Button button = new Button(this);
        button.setText("点击拍照");
        linearLayout.addView(button);
        imageView = new ImageView(this);
        linearLayout.addView(imageView);
        setContentView(linearLayout);
        button.setOnClickListener(v -> {
            takePhotoNoCompress2();
        });
    }

    private static final int REQUEST_CODE_TAKE_PHOTO = 0x110;// 272

    private String mCurrentPhotoPath;

    public void takePhotoNoCompress() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                    .format(new Date()) + ".png";
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            mCurrentPhotoPath = file.getAbsolutePath();

            Log.d("FileProviderActivity","mCurrentPhotoPath:"+mCurrentPhotoPath);
            // android.os.FileUriExposedException: file:///storage/emulated/0/20220216-152452.png exposed beyond app through ClipData.Item.getUri()
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);

        }
    }
    public void takePhotoNoCompress2() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                    .format(new Date()) + ".png";
            // 若没有 cameraimg  报错 Failed to find configured root that contains
            File file = new File(getExternalFilesDir("cameraimg"), filename);
//            File file = new File(Environment.getExternalStorageDirectory(), filename);
            mCurrentPhotoPath = file.getAbsolutePath();

            Log.w("FileProviderActivity","mCurrentPhotoPath:"+mCurrentPhotoPath);// /storage/emulated/0/Android/data/com.fan.collect.module.androidversion/files/cameraimg/20220216-151911.png
            // param1:manifest中配置的authorities
            Uri uriForFile = FileProvider.getUriForFile(this, getPackageName() + ".fileProvider", file);
            Log.w("FileProviderActivity","uriForFile:"+uriForFile);// content://com.fan.collect.module.androidversion.fileProvider/testname/20220216-151911.png

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
            startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
            /*
             getExternalFilesDir("cameraimg")  <external-files-path name="testname" path="cameraimg" />
             mCurrentPhotoPath:/storage/emulated/0/Android/data/com.fan.collect.module.androidversion/files/cameraimg/20220216-164529.png
             uriForFile:content://com.fan.collect.module.androidversion.fileProvider/testname/20220216-164529.png 这里没有cameraimg
             requestCode:272 resultCode:-1 // 成功
             */
            /*
             * getExternalFilesDir("cameraimg") <external-files-path name="testname" path="" />
             * mCurrentPhotoPath:/storage/emulated/0/Android/data/com.fan.collect.module.androidversion/files/cameraimg/20220216-165326.png
             * uriForFile:content://com.fan.collect.module.androidversion.fileProvider/testname/cameraimg/20220216-165326.png
             *            content://com.fan.collect.module.androidversion.fileProvider/testname/Android/data/com.fan.collect.module.androidversion/files/cameraimg/20220216-180119.png
             * requestCode:272 resultCode:-1 // 成功
             */
            /*
             * getExternalFilesDir("")  <external-files-path name="testname" path="cameraimg" />
             * Failed to find configured root that contains /storage/emulated/0/Android/data/com.fan.collect.module.androidversion/files/20220216-165145.png
             */
            /*
             Environment.getExternalStorageDirectory()  <external-path name="testname" path="" />
             mCurrentPhotoPath: /storage/emulated/0/20220216-152642.png
             uriForFile: content://com.fan.collect.module.androidversion.fileProvider/testname/20220216-152642.png
             requestCode:272 resultCode:0 // 能拍照成功但返回失败
             */
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("FileProviderActivity","requestCode:"+requestCode+" resultCode:"+resultCode);//若是sdcard
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_PHOTO) {
            Log.e("FileProviderActivity","onActivityResult mCurrentPhotoPath:"+mCurrentPhotoPath);
            imageView.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
        }
    }
}
