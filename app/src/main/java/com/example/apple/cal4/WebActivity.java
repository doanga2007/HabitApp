package com.example.apple.cal4;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by anubissmile on 26/04/2016.
 */
public class WebActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView WebViw = (WebView) findViewById(R.id.webView1);
        WebViw.getSettings().setJavaScriptEnabled(true);
        WebViw.loadUrl("https://www.16personalities.com/free-personality-test");
    }

}
