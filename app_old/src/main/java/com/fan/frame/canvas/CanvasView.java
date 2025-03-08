package com.fan.frame.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasView extends View {

    public CanvasView(Context context) {
        super(context);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();

    }

    Paint mPaint;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        draw2(canvas);
    }
    private void draw2(Canvas canvas){
        canvas.drawColor(Color.BLUE);
        mPaint.setColor(Color.RED);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);

        canvas.save();
        canvas.scale(0.5f, 0.5f);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);
        canvas.restore();

        canvas.save();
        canvas.scale(0.5f, 0.5f, 200, 200);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);
        canvas.restore();

        mPaint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, 300, 300), mPaint);
//        canvas.rotate(45);
        canvas.rotate(45,200,200);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(new Rect(0, 0, 300, 300), mPaint);
    }

    private void draw1(Canvas canvas) {
        // 整个view为蓝色
        canvas.drawColor(Color.BLUE);
        // 画笔为红色
        mPaint.setColor(Color.RED);
        // view的左上角为基准点，画红色矩形
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);

        mPaint.setColor(Color.GRAY);
        // view的左上角为基准点，100 100为左上角顶点画灰色矩形
        canvas.drawRect(new Rect(100, 100, 400, 400), mPaint);
        mPaint.setColor(Color.GREEN);
        // view的左上角为基准点 话绿色小方块
        canvas.drawRect(new Rect(0, 0, 100, 100), mPaint);

        canvas.save();
        // xy缩放0.5相当于画Rect(0, 0, 200, 200).黄色
        canvas.scale(0.5f, 0.5f);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);
        // canvas上面缩放过，会影响下面.得还原. 如果不还原，下面黑色小方块在黄色小方块中间，长宽为一半
        canvas.restore();

        canvas.save();
        // 以 200 200为中心向四周缩放，相当于canvas.drawRect(new Rect(100, 100, 300, 300), mPaint);
        canvas.scale(0.5f, 0.5f, 200, 200);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);
        canvas.restore();

        /***canvas.drawRect(new Rect(0, 0, 400, 400), mPaint); 相当于下面3步操作
         * translate(px, py);
         * scale(sx, sy); 即先将画布平移px,py，然后scale，scale结束之后再将画布平移回原基准点(最后的平移是scale后的距离)；
         * translate(-px, -py);
         * 以px py这个坐标 为中心向四方缩放.
         */
        canvas.translate(200,200);
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);

        canvas.scale(0.5f,0.5f);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);

        canvas.translate(-200,-200);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);

    }
}
