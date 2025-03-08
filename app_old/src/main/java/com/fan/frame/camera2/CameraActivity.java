package com.fan.frame.camera2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity {
   /* private TextureView mTextureView;
    private CameraCaptureSession mCameraCaptureSession;
    private CameraDevice mCameraDevice;
    private Surface mPreviewSurface;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        findViewById(R.id.startcamera2).setOnClickListener(v -> {
            open2(manager);
        });
        //预览用的surface
        mTextureView = (TextureView) this.findViewById(R.id.textureview);
//        mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void open2(CameraManager manager) {
        mTextureView.setSurfaceTextureListener(new SurfaceTextureListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture arg0, int arg1, int arg2) {
                // TODO 自动生成的方法存根
                mPreviewSurface = new Surface(arg0);
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture arg0) {
                // TODO 自动生成的方法存根
                return false;
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture arg0, int arg1, int arg2) {
                // TODO 自动生成的方法存根

            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture arg0) {
                // TODO 自动生成的方法存根

            }

        });
        try {
            if (ActivityCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            manager.openCamera("1", new CameraDevice.StateCallback() {

                @Override
                public void onOpened(CameraDevice arg0) {
                    // TODO 自动生成的方法存根s
                    mCameraDevice = arg0;
                    configPreview(mTextureView,640,480);
                    try {
                        mCameraDevice.createCaptureSession(Arrays.asList(mPreviewSurface), new CameraCaptureSession.StateCallback() {

                            @Override
                            public void onConfigured(CameraCaptureSession arg0) {
                                // TODO 自动生成的方法存根
                                mCameraCaptureSession = arg0;
                                try {
                                    CaptureRequest.Builder builder;
                                    builder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                                    builder.addTarget(mPreviewSurface);
                                    mCameraCaptureSession.setRepeatingRequest(builder.build(), null, null);
                                } catch (CameraAccessException e1) {
                                    // TODO 自动生成的 catch 块
                                    e1.printStackTrace();
                                }


                            }

                            @Override
                            public void onConfigureFailed(CameraCaptureSession arg0) {
                                // TODO 自动生成的方法存根

                            }
                        }, null);
                    } catch (CameraAccessException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(CameraDevice arg0, int arg1) {
                    // TODO 自动生成的方法存根

                }

                @Override
                public void onDisconnected(CameraDevice arg0) {
                    // TODO 自动生成的方法存根

                }
            }, null);
        } catch (CameraAccessException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    //  https://www.cnblogs.com/lengyanyue39/p/4033805.html
    public void configPreview(TextureView textureView, int targetWidth, int targetHeight) {
        // 手机横屏1(逆90°)手机竖屏0  满客宝 横屏0  竖屏录脸0
         int rotation = getWindowManager().getDefaultDisplay().getRotation();
        // 手机竖屏90 横屏0   满客宝 横屏0
//        int cameraDisplayOrientation = getCameraDisplayOrientation(CameraActivity.this, 1, camera);

        FLogger.d("rotation:"+rotation);
        Matrix matrix = new Matrix();
        matrix.postScale(-1,1,targetHeight/2,0);
        RectF viewRect = new RectF(0, 0, targetWidth, targetHeight);
        RectF bufferRect = new RectF(0, 0,targetWidth, targetHeight);
        float centerX = viewRect.centerX();
        float centerY = viewRect.centerY();
        if (Surface.ROTATION_90 == rotation || Surface.ROTATION_270 == rotation) {
            bufferRect.offset(centerX - bufferRect.centerX(), centerY - bufferRect.centerY());
            matrix.setRectToRect(viewRect, bufferRect, Matrix.ScaleToFit.FILL);
            float scale = Math.max((float) targetHeight / targetWidth, (float) targetWidth / targetHeight);
            matrix.postScale(scale, scale, centerX, centerY);
            matrix.postRotate(90 * (rotation - 2), centerX, centerY);
        }
        ToastUtils.showShort("rotation:"+rotation);
        textureView.setTransform(matrix);
    }

    public void sss(){

    }*/
}
