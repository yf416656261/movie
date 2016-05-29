package com.example.movie;

import android.app.Activity;
import android.os.Bundle;
import com.example.movie.Login;

public class User_info extends Activity {

    private TextView name = (TextView)findViewById(R.id.name);
    private TextView sex = (TextView)findViewById(R.id.sex);
    private TextView phone = (TextView)findViewById(R.id.phone);
    private TextView email_address = (TextView)findViewById(R.id.email_address);
    private TextView address = (TextView)findViewById(R.id.address);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        name.setText(Login.user.getName());
        sex.setText(Login.user.getSex());
        phone.setText(Login.user.getPhone());
        email_address.setText(Login.user.getEmail_address());
        address.setText(Login.user.getAddress());
    }
}
