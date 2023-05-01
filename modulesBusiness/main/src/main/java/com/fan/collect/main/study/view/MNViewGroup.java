package com.fan.collect.main.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MNViewGroup extends ViewGroup {

    private int mWidth;
    private int mHeight;

    public MNViewGroup(Context context) {
        this(context,null);
    }

    public MNViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MNViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        getWidth();//onlayout之后
        getMeasuredWidth();//onMeasured之后
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        if(childCount == 0){
            //  如果viewgroup中没有任何控件
            int width = measureWH(widthMeasureSpec);
            int height = measureWH(heightMeasureSpec);
            setMeasuredDimension(width,height);
        }else{
            //  有控件的情况 ,当时wrap的时候宽度是子view最大的宽度，高度是所有子view相加和margin
            int childViewsWidth = 0;
            int childViewsHeight = 0;
            int childViewsMarginLeft = 0;
            int childViewsMarginRight = 0;
            int childViewsMarginTop = 0;
            int childViewsMarginBottom = 0;
            for(int i=0;i<childCount;i++){
                View childView = getChildAt(i);
                // 根据父view属性和子view测量子view
                measureChild(childView,widthMeasureSpec,heightMeasureSpec);
                // 得到其中最大的子view的宽度
                childViewsWidth = Math.max(childViewsWidth,childView.getMeasuredWidth());
                // 所有子view的高度的和
                childViewsHeight = childViewsHeight +childView.getMeasuredHeight();
                // 重写generateLayoutParams
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                childViewsMarginTop += lp.topMargin;
                childViewsMarginBottom += lp.bottomMargin;
                childViewsMarginLeft= Math.max(childViewsMarginLeft,lp.leftMargin);
                childViewsMarginRight= Math.max(childViewsMarginRight,lp.rightMargin);

            }
            mWidth = childViewsWidth +childViewsMarginLeft +childViewsMarginRight;
            mHeight = childViewsHeight+childViewsMarginTop+childViewsMarginBottom;
            setMeasuredDimension(measureWH(widthMeasureSpec,mWidth),measureWH(heightMeasureSpec, mHeight));
        }
    }

    private int measureWH(int measureSpec, int mWidthOrHeight) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = mWidthOrHeight;
        }
        return result;
    }

    private int measureWH(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 布局子控件
        int left,top,right,bottom;
        int childCount = getChildCount();
        int countTop = 0;
        for(int i=0;i<childCount;i++){
            View childView = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            left = lp.leftMargin;
            right = left+childView.getMeasuredWidth();
            top  = countTop+lp.topMargin;
            bottom = top +childView.getMeasuredHeight();
            childView.layout(left,top,right,bottom);
            countTop +=(bottom-top)+ lp.bottomMargin +lp.topMargin;
        }

    }
}
