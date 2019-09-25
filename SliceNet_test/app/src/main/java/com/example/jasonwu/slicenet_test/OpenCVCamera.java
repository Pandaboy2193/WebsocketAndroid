package com.example.jasonwu.slicenet_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;



public class OpenCVCamera extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{

    private static final String TAG = "OpenCVCamera";
    private CameraBridgeViewBase cameraBridgeViewBase;
    private BaseLoaderCallback baseLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    cameraBridgeViewBase.enableView();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cvcamera);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_open_cvcamera);
        cameraBridgeViewBase = (CameraBridgeViewBase) findViewById(R.id.camera_view);
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        cameraBridgeViewBase.setCvCameraViewListener(this);
        Intent i = new Intent(getApplicationContext(),WebIntentService.class);
        startService(i);
        System.out.println("onCreate-------------(1)");

    }
    @Override
    public void onResume(){
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, baseLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    public void onPause()
    {
        super.onPause();
        if (cameraBridgeViewBase != null)
            cameraBridgeViewBase.disableView();
    }

    public void onDestroy() {
        super.onDestroy();
        if (cameraBridgeViewBase != null)
            cameraBridgeViewBase.disableView();
    }

    public void onCameraViewStarted(int width, int height) {
        System.out.println("onCameraViewStarted-------------(2)");
    }


    public void onCameraViewStopped() {
        System.out.println("onCameraViewStopped-------------(4)");
    }


    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        System.out.println("onCameraFrame-------------(3)");
//        Intent web = new Intent(getApplicationContext(),WebIntentService.class);
//        Bundle sendimage = web.getExtras();
        Mat image = inputFrame.rgba();
//        long addr = image.getNativeObjAddr();
//        web.putExtra("image", addr);
//        startService(web);

        return image;

    }
}
