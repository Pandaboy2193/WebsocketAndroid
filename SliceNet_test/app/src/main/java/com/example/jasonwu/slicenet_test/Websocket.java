package com.example.jasonwu.slicenet_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import io.crossbar.autobahn.websocket.WebSocketConnection;
import io.crossbar.autobahn.websocket.WebSocketConnectionHandler;
import io.crossbar.autobahn.websocket.exceptions.WebSocketException;
import io.crossbar.autobahn.websocket.interfaces.IWebSocket;
import io.crossbar.autobahn.websocket.types.WebSocketOptions;

public class Websocket extends AppCompatActivity {

    private static final String TAG = "Websocket";
    private final IWebSocket mConnection = new WebSocketConnection();

    private void start() {

        WebSocketConnection connection = new WebSocketConnection();
        WebSocketOptions connectOptions = new WebSocketOptions();
        connectOptions.setReconnectInterval(5000);
        try {
            mConnection.connect("ws://192.168.113.128:8888", new WebSocketConnectionHandler() {


                public void onOpen() {
                    System.out.println("Connection Opened");
                    connection.sendMessage("Echo with Autobahn");
                }


                public void onClose(int code, String reason) {
                    System.out.println("Connection closed");
                    Toast.makeText(getApplicationContext(),"Connection closed", Toast.LENGTH_LONG).show();
                }


                public void onMessage(String payload) {
                    System.out.println("Received message: " + payload);
                    connection.sendMessage(payload);
                    Toast.makeText(getApplicationContext(),"Received message", Toast.LENGTH_LONG).show();
                }
            }, connectOptions);
        } catch (WebSocketException e) {
            Log.d(TAG, e.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start();
        System.out.println("Websocket onCreate");
    }
}
