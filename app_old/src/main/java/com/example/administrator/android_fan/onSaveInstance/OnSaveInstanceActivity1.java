package com.example.administrator.android_fan.onSaveInstance;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
// https://blog.csdn.net/fenggering/article/details/53907654
public class OnSaveInstanceActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState !=null){
            Log.e("OnSaveInstanceActivity","onCreate savedInstanceState:"+savedInstanceState.getString("save"));
        }
        Button button = new Button(this);
        button.setText("跳转");
        button.setOnClickListener(v -> {
            // 调用本app的activity
//            Intent intent = new Intent();
//            intent.setClass(OnSaveInstanceActivity1.this,OnSaveInstanceActivity2.class);
//            startActivityForResult(intent,2);

            // 调用第三方app的activity
            Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
            pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            startActivityForResult(pickContactIntent,2);
//            finish();// 主动finish 不会有onActivityResult
        });
        setContentView(button);
    }


    // 没有setResult也有回调 resultCode0
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String datas = "";
        if(data != null){
             datas = data.getStringExtra("data");
        }
        Log.e("OnSaveInstanceActivity","requestCode:"+requestCode+" resultCode:"+resultCode+" datas:"+datas);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("OnSaveInstanceActivity","onSaveInstanceState");
        outState.putString("save","qqwwee");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("OnSaveInstanceActivity","onRestoreInstanceState:"+savedInstanceState.getString("save"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("OnSaveInstanceActivity","onDestroy");
//        finish(); 不能调用finish,主动finish的不会重建。
    }
}
