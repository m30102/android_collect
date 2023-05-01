package com.fan.collect.module.ui.dialog;


import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.fan.collect.base.utils.LogHelper;
import com.fan.collect.module.widget.R;

public class ProgressLoadingDialog extends DialogFragment implements DialogInterface.OnKeyListener {

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().setOnKeyListener(this);
        }
}

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (Exception e) {
            Log.e("ProgressLoadingDialog", "Fragment show error");
            e.printStackTrace();
        }
    }
    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            LogHelper.e("ProgressLoadingDialog", "Fragment dismiss error");
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progressdialog_loading, container,false);
        setCancelable(true);
        try {
            Window window = getDialog().getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0));
            // 去掉标题
            window.requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().setCancelable(false);
            getDialog().setCanceledOnTouchOutside(false);}
        catch (Exception e) {
            // 如果把DialogFragment 当做普通Fragment使用 那么这里会有异常
//			e.printStackTrace();
            Log.e("ProgressLoadingDialog", e == null ? ".":e.getMessage()+"");
        }
        return view;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // true:禁止返回键
            return true;
        } else {
            return false;
        }
    }
}
