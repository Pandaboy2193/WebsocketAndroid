package com.example.jasonwu.slicenet_test;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import com.google.gson.Gson;
import io.crossbar.autobahn.websocket.WebSocketConnection;
import io.crossbar.autobahn.websocket.WebSocketConnectionHandler;
import io.crossbar.autobahn.websocket.exceptions.WebSocketException;
import io.crossbar.autobahn.websocket.interfaces.IWebSocket;
import io.crossbar.autobahn.websocket.types.WebSocketOptions;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class WebIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
/*    public static final String ACTION_FOO = "com.example.jasonwu.slicenet_test.action.FOO";
    public static final String ACTION_BAZ = "com.example.jasonwu.slicenet_test.action.BAZ";*/
    private static final String TAG = "WebIntentService";

    // TODO: Rename parameters
/*    public static final String EXTRA_PARAM1 = "com.example.jasonwu.slicenet_test.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.jasonwu.slicenet_test.extra.PARAM2";*/

    public WebIntentService() {
        super("WebIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        /*if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }*/
/*        long addr = intent.getLongExtra("image",0);
        Mat tempImg = new Mat(addr);
        Mat img = tempImg.clone();*/
        System.out.println("Web server started");
        start();
    }
    private final IWebSocket mConnection = new WebSocketConnection();

    private void start() {

        WebSocketOptions connectOptions = new WebSocketOptions();
        connectOptions.setReconnectInterval(5000);
        try {
            mConnection.connect("ws://192.168.113.128:8888", new WebSocketConnectionHandler() {


                public void onOpen() {
                    System.out.println("Connection Opened");
                    Gson gson = new Gson();
                    mConnection.sendMessage(gson.toJson("\"type\": \"init\", \"mode\": \"identify\""));
//                    mConnection.sendMessage(image);
                }


                public void onClose(int code, String reason) {
                    System.out.println("Connection closed");

                }


                public void onMessage(String payload) {
                    System.out.println("Received message: " + payload);
//                    mConnection.sendMessage(payload);
                }
            }, connectOptions);
        } catch (WebSocketException e) {
            Log.d(TAG, e.toString());
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    /*private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }*/

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
//    private void handleActionBaz(String param1, String param2) {
//        // TODO: Handle action Baz
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
}
