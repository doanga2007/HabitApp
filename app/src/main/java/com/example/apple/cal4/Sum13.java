package com.example.apple.cal4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * Created by sumate on 4/25/2016 AD.
 */
public class Sum13 extends Activity {
    Handler handler;
    Runnable runnable;
    long delay_time;
    long time = 3000L;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sum13);
        handler = new Handler();

        runnable = new Runnable() {
            public void run() {
                Intent intent = new Intent(Sum13.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    public void onResume() {
        super.onResume();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }
}
