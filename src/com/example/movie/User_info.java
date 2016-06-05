package com.example.movie;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.movie.Login;

public class User_info extends Activity {

    private TextView name, sex, phone, email_address, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        
        name = (TextView)findViewById(R.id.info_name);
        sex = (TextView)findViewById(R.id.sex);
        phone = (TextView)findViewById(R.id.phone);
        email_address = (TextView)findViewById(R.id.email_address);
        address = (TextView)findViewById(R.id.address);
        
        name.setText(Login.user.getName());
        sex.setText(Login.user.getSex());
        phone.setText(Login.user.getPhone());
        email_address.setText(Login.user.getEmail_address());
        address.setText(Login.user.getAddress());
    }
}
