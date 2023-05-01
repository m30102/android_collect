package com.fan.collect.share;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tmf.demo.R;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private static final double ONLY_IMAGE_MAX_SIZE = 10 * 1024 * 1024;  // 10MB
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.example_image4);
        ImageView iv = findViewById(R.id.iv_share);
        iv.setImageBitmap(bitmap);


        IWXAPI wxapi = WXAPIFactory.createWXAPI(getApplicationContext(), "wx8071141a542f9dad");
        findViewById(R.id.share).setOnClickListener(v -> {

            byte[] maxWeixinThumbData = getMaxWeixinThumbData(bitmap, ONLY_IMAGE_MAX_SIZE);
//            WXImageObject imageObject = new WXImageObject(maxWeixinThumbData);
            WXImageObject imageObject = new WXImageObject(bitmap);
            WXMediaMessage  mediaMessage = new WXMediaMessage(imageObject);

            SendMessageToWX.Req localReq = new SendMessageToWX.Req();
            localReq.message = mediaMessage;
            localReq.scene = SendMessageToWX.Req.WXSceneTimeline;
            boolean b = wxapi.sendReq(localReq);
            Log.e("wxactivity","result:"+b);
        });


    }

    private static byte[] bitmap2PngBytes(Bitmap bm) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        } catch (Throwable throwable) {

        }
        return new byte[0];
    }
    private static byte[] getMaxWeixinThumbData(Bitmap bm, double maxSize) {
        if (bm == null || bm.isRecycled()) {
            return new byte[0];
        }
        double maxHeight = 180;
        // 将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
        byte[] bs = bitmap2PngBytes(bm);
        Log.i("wxactivity", "bitmapSize: " + bs.length + " maxSize: " + maxSize);
        if (bs.length < maxSize) {
            return bs;
        } else {
            int height = bm.getWidth();
            int width = bm.getHeight();
            int temp = height > width ? height : width;
            double i = temp / maxHeight;
            double suggestHeight = height / i;
            double suggestWidth = width / i;

            Bitmap bitmap = bm;
            byte[] bs2 = null;
            while (true) {
                Bitmap newbitmap = zoomImage(bitmap, suggestWidth, suggestHeight);
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
                bitmap = newbitmap;
                if (bitmap != null) {
                    bs2 = bitmap2PngBytes(bitmap);
                    if (bs2.length < maxSize) {
                        break;
                    } else {
                        suggestHeight = suggestHeight * 0.9;
                        suggestWidth = suggestWidth * 0.9;
                    }
                }
            }
            return bs2;
        }
    }

    private static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }

}