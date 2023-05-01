package com.fan.collect.base.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlackToast {


    public static View createView(Context context,String s) {
        TextView textView = new TextView(context);
        textView.setId(android.R.id.message);
        textView.setGravity(getTextGravity(context));
        textView.setTextColor(getTextColor(context));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getTextSize(context));
        textView.setText(s);
        int horizontalPadding = getHorizontalPadding(context);
        int verticalPadding = getVerticalPadding(context);
        verticalPadding = verticalPadding /2;
        // 适配布局反方向特性
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setPaddingRelative(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        } else {
            textView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        }

        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Drawable backgroundDrawable = getBackgroundDrawable(context);
        // 设置背景
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(backgroundDrawable);
        } else {
            textView.setBackgroundDrawable(backgroundDrawable);
        }
        // 设置 Z 轴阴影
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textView.setZ(getTranslationZ(context));
        }

        return textView;
    }

    protected static float getTranslationZ(Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, context.getResources().getDisplayMetrics());
    }
    protected static int getTextGravity(Context context) {
        return Gravity.CENTER;
    }

    protected static int getTextColor(Context context) {
        return 0XEEFFFFFF;
    }

    protected static float getTextSize(Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, context.getResources().getDisplayMetrics());
    }

    protected static int getHorizontalPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, context.getResources().getDisplayMetrics());
    }

    protected static int getVerticalPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics());
    }


    protected static Drawable getBackgroundDrawable(Context context) {
        GradientDrawable drawable = new GradientDrawable();
        // 设置颜色
//        drawable.setColor(0XB3000000);
        drawable.setColor(Color.parseColor("#77000000"));
        // 设置圆角
        drawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics()));
        return drawable;
    }
}
