package com.example.apple.cal4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.Random;

/**
 * Created by sumate on 4/14/2016 AD.
 */
public class DetailActivity3 extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_deail3);

        final Button btn4 = (Button) findViewById(R.id.button);

        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Random rnd = new Random();
                int x=rnd.nextInt(2)+1;
                Intent myIntent = new Intent();
                switch(x){
                    case 1:
                        myIntent.setClass(v.getContext(),Sum5.class);
                        break;
                    case 2:
                        myIntent.setClass(v.getContext(),Sum6.class);
                        break;
                }
                startActivity(myIntent);
            }
        });
    }

}