package com.example.movie;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

	EditText pw;
	EditText name;
	EditText sex;
	EditText phone;
	EditText e_add;
	EditText add;
	Button rg;
	
	private Handler handler;
	private Message msg;
	
	
	HttpURLConnection connection = null;
	DataOutputStream out;
	InputStream in;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        pw = (EditText)findViewById(R.id.password_register);
        name = (EditText)findViewById(R.id.name_register);
        sex = (EditText)findViewById(R.id.sex_register);
        phone = (EditText)findViewById(R.id.phone_register);
        e_add = (EditText)findViewById(R.id.email_address);
        add = (EditText)findViewById(R.id.address_register);
        rg = (Button)findViewById(R.id.register_button);
        
        handler = new MsgHandler(Register.this);
        
        rg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String acc = name.getText().toString();
				String password = pw.getText().toString();
				final String url = Login.URL + "/register";
				final String query = "account=" + acc + "&password=" + password + "&sex=" + sex.getText().toString() + "&phone=" + phone.getText().toString() + "&e_add=" + e_add.getText().toString() + "&add=" + add.getText().toString(); 
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String result = "";
						try {
							connection = (HttpURLConnection)((new URL(url).openConnection()));
							connection.setRequestMethod("POST");
							connection.setConnectTimeout(40000);
							connection.setReadTimeout(40000);
							
							out = new DataOutputStream(connection.getOutputStream());
							out.writeBytes(query);
							
							in = connection.getInputStream();
							BufferedReader reader = new BufferedReader(new InputStreamReader(in));
							StringBuilder response = new StringBuilder();
							String line;
							while ((line = reader.readLine()) != null) {
								response.append(line);
							}
							result = response.toString();
							Log.w("cccc", result);
							if (result.equals("")) {
								msg = handler.obtainMessage();
								msg.arg1 = 1;
								handler.sendMessage(msg);
							} else {
							    Login.user = new User(name.getText().toString(), pw.getText().toString(), sex.getText().toString(), phone.getText().toString(), e_add.getText().toString(), add.getText().toString());
								
							    msg = handler.obtainMessage();
								msg.arg1 = 2;
								handler.sendMessage(msg);
								
							    Intent intent = new Intent(Register.this, MainTabsActivity.class);
								startActivity(intent);
								finish();
							}
						} catch (Exception e) {
							e.printStackTrace();
							result = "";
						} finally {
							if (connection != null) {
								connection.disconnect();
							}
						}
					}
					
				}).start();
			}
		});
    }
    
    public class MsgHandler extends Handler {
    	private Activity activity;
    	public MsgHandler(Activity act) {
    	     activity = new WeakReference<Activity>(act).get();
    	}
    	@Override
    	public void handleMessage(Message msg) {
    		switch (msg.arg1) {
    		case 1:
    			showInfo("此用户名已存在");
    			break;
    		case 2:
    			showInfo("注册成功！");
    			break;
    		default:
    			break;
    		}
    		super.handleMessage(msg);
    	}
    	public void showInfo(String info) {
        	Toast.makeText(activity.getApplicationContext(), info, Toast.LENGTH_SHORT).show();
        }
    }
}
