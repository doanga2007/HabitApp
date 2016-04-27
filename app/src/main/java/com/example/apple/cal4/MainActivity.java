package com.example.apple.cal4;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        final EditText txtHabit = (EditText)findViewById(R.id.txtHabit);

        // btnLogin
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnHabit = (Button) findViewById(R.id.btnHabit);
        final Button btnWeb = (Button) findViewById(R.id.btnWeb);

        // Perform action on click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String url = "http://10.0.3.2/checkLogin.php";
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("strHabit", txtHabit.getText().toString()));

                String resultServer  = getHttpPost(url,params);

                /*** Default Value ***/
                String strStatusID = "0";
                String strMemberID = "0";
                String strHabit = "";
                String strError = "Unknow Status!";

                JSONObject c;
                try {
                    c = new JSONObject(resultServer);
                    strStatusID = c.getString("StatusID");
                    strMemberID = c.getString("MemberID");
                    strHabit = c.getString("Habit");
                    strError = c.getString("Error");

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // Prepare Login
                if(strStatusID.equals("0"))
                {
                    // Dialog
                    ad.setTitle("Error! ");
                    ad.setIcon(android.R.drawable.btn_star_big_on);
                    ad.setPositiveButton("Close", null);
                    ad.setMessage(strError);
                    ad.show();
                    txtHabit.setText("");
                }

                else if(strHabit.equals("ISTJ"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("ISTP"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity2.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("ISFJ"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity3.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }
                else if(strHabit.equals("ISFP"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity4.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("INFJ"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity5.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("INFP"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity6.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }
                else if(strHabit.equals("INTJ"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity7.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("INTP"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity8.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("ESTJ"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity9.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }
                else if(strHabit.equals("ESTP") )
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity10.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("ESFJ"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity11.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("ESFP"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity12.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }
                else if(strHabit.equals("ENFJ") )
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity13.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("ENFP"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity14.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("ENTJ"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity15.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("ENTP"))
                {
                    Toast.makeText(MainActivity.this, "มาถึงแหล่งข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity16.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }
            }
        });

        btnHabit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form 2
                Intent newActivity = new Intent(MainActivity.this,HabitActivity.class);
                startActivity(newActivity);
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form 2
                Intent newActivity = new Intent(MainActivity.this,WebActivity.class);
                startActivity(newActivity);
            }
        });

    }

    public String getHttpPost(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
