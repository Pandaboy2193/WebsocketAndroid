package com.example.jasonwu.slicenet_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    static final String TAG = "io.crossbar.autobahn.echo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cameraInit = (Button) findViewById(R.id.start_button);
        cameraInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),OpenCVCamera.class);
                startActivity(i);
            }
        });
    }
    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        if (mConnection.isConnected()) {
            mConnection.sendClose();
        }
    }*/


}

