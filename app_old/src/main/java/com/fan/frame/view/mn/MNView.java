package com.fan.frame.view.mn;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.administrator.android_fan.R;
import com.fan.frame.utils.FLogger;

//https://www.bilibili.com/video/BV1Hi4y1G7Dz?t=76
//https://blog.csdn.net/chen_white/article/details/79607909
public class MNView extends View {


    String text;
    private Paint mPaint;
    // 计算字体需要的范围
    Rect mTextBounds;
    public MNView(Context context) {
        this(context,null);
    }

    public MNView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MNView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MNView);
        text = ta.getString(R.styleable.MNView_mn_text);
        ta.recycle();

        mPaint = new Paint();
        mTextBounds = new Rect();
        mPaint.setTextSize(50);
        mPaint.getTextBounds(text,0,text.length(),mTextBounds);
    }

    String TAG =  "MNView";

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecWidth = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecWidth = MeasureSpec.getSize(heightMeasureSpec);
        FLogger.d("onMeasure width:"+widthSpecMode+"_"+widthSpecWidth+"_"+MeasureSpec.toString(widthMeasureSpec));
        FLogger.d("onMeasure height:"+heightSpecMode+"_"+heightSpecWidth+"_"+MeasureSpec.toString(heightMeasureSpec));
        if(widthSpecMode == MeasureSpec.EXACTLY){
            //match_parent 或者 具体数值
            mWidth = widthSpecWidth;
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            // wrap_content:AT_MOST.
            mWidth = mTextBounds.width()+getPaddingLeft()+getPaddingRight();
        }

        if(heightSpecMode == MeasureSpec.EXACTLY){
            mHeight = heightSpecWidth;
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            mHeight = mTextBounds.height()+getPaddingTop()+getPaddingBottom();
        }
//        FLogger.i("setMeasuredDimension:"+mWidth+"_"+mHeight);
        setMeasuredDimension(mWidth,mHeight);

    }
    int mHeight;
    int mWidth;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawText(text,0,mTextBounds.height(),mPaint);
        canvas.drawText(text,getPaddingLeft(),getPaddingTop()+mTextBounds.height(),mPaint);

    }
}
