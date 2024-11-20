package com.fan.collect.study.view;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.fan.collect.R;
import com.fan.collect.base.utils.Logger;

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


        //onMeasure width:1073741824_1080_MeasureSpec: EXACTLY 1080
        //onMeasure height:1073741824_300_MeasureSpec: EXACTLY 300
        //onMeasure width:-2147483648_1920_MeasureSpec: AT_MOST 1920
        Logger.d(TAG,"onMeasure width:"+widthSpecMode+"_"+widthSpecWidth+"_"+MeasureSpec.toString(widthMeasureSpec));
        Logger.d(TAG,"onMeasure height:"+heightSpecMode+"_"+heightSpecWidth+"_"+MeasureSpec.toString(heightMeasureSpec));
        if(widthSpecMode == MeasureSpec.EXACTLY){
            //match_parent 或者 具体数值
            mWidth = widthSpecWidth;
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            // wrap_content:AT_MOST.  如果这里不指定，那么父控件会传父控件的宽度给到子控件 .onMeasure width:-2147483648_1920_MeasureSpec: AT_MOST 1920
            mWidth = mTextBounds.width()+getPaddingLeft()+getPaddingRight();
//            mWidth = widthSpecWidth;//at most = 父控件
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
