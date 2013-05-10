package me.pedaling.ouruniv;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        Handler handler = new Handler () {
            @Override
            public void handleMessage(Message msg) {
            }
        };
       
        handler.sendEmptyMessageDelayed(0, 3000);
    }
}