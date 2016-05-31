package com.example.movie;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

	EditText pw = (EditText)findViewById(R.id.password_register);
	EditText name = (EditText)findViewById(R.id.name_register);
	EditText sex = (EditText)findViewById(R.id.sex_register);
	EditText phone = (EditText)findViewById(R.id.phone_register);
	EditText e_add = (EditText)findViewById(R.id.email_address);
	EditText add = (EditText)findViewById(R.id.address_register);
	Button rg = (Button)findViewById(R.id.register_button);
	
	
	HttpURLConnection connection = null;
	DataOutputStream out;
	InputStream in;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
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
							if (result.equals("")) {
								Toast.makeText(Register.this, "此用户名已存在", Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(Register.this, "注册成功！", Toast.LENGTH_SHORT).show();
								Login.user = new User(name.getText().toString(), pw.getText().toString(), sex.getText().toString(), phone.getText().toString(), e_add.getText().toString(), add.getText().toString());
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
}
