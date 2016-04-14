package com.example.apple.cal4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;

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

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    final String PREF_NAME = "LoginPreferences";
    final String KEY_USERNAME = "Username";
    final String KEY_PASSWORD = "Password";
    final String KEY_REMEMBER = "RememberUsername";

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    CheckBox cbRemember;

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

        // txtUsername & txtPassword
        final EditText txtUser = (EditText)findViewById(R.id.txtUsername);
        final EditText txtPass = (EditText)findViewById(R.id.txtPassword);
        final EditText txtHabit = (EditText)findViewById(R.id.txtHabit);

        // btnLogin
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnRegis = (Button) findViewById(R.id.btnRegis);

        sp = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

        txtUser.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void afterTextChanged(Editable s) {
                editor = sp.edit();
                editor.putString(KEY_USERNAME, s.toString());
                editor.putString(KEY_PASSWORD, s.toString());
                editor.commit();
            }
        });

        cbRemember = (CheckBox)findViewById(R.id.checkBox);
        cbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(KEY_REMEMBER, isChecked);
                editor.commit();
            }
        });

        boolean isRemember = sp.getBoolean(KEY_REMEMBER, false);
        cbRemember.setChecked(isRemember);

        if(isRemember) {
            String username = sp.getString(KEY_USERNAME, "");
            txtUser.setText(username);
        }

        // Perform action on click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String url = "http://10.0.3.2/checkLogin.php";
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("strUser", txtUser.getText().toString()));
                params.add(new BasicNameValuePair("strPass", txtPass.getText().toString()));
                params.add(new BasicNameValuePair("strHabit", txtHabit.getText().toString()));

                /** Get result from Server (Return the JSON Code)
                 * StatusID = ? [0=Failed,1=Complete]
                 * MemberID = ? [Eg : 1]
                 * Error	= ?	[On case error return custom error message]
                 *
                 * Eg Login Failed = {"StatusID":"0","MemberID":"0","Error":"Incorrect Username and Password"}
                 * Eg Login Complete = {"StatusID":"1","MemberID":"2","Error":""}
                 */

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
                    txtUser.setText("");
                    txtPass.setText("");
                    txtHabit.setText("");
                }

                else if(strHabit.equals("Strong"))
                {
                    Toast.makeText(MainActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("Creative"))
                {
                    Toast.makeText(MainActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity2.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }

                else if(strHabit.equals("Leader"))
                {
                    Toast.makeText(MainActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(MainActivity.this,DetailActivity3.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    startActivity(newActivity);
                }
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Form 2
                Intent newActivity = new Intent(MainActivity.this,RegisActivity.class);
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
