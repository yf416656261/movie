package com.example.ymw.mymyotee;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by ymw on 2016/6/28.
 */
public class JoinUsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinus);
        final ImageButton jionusimabutton1 = (ImageButton) findViewById(R.id.jionusButton1);
        jionusimabutton1.setImageResource(R.drawable.img_return);
        jionusimabutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final ImageView jionusimgview1 = (ImageView) findViewById(R.id.jionusView1);
        jionusimgview1.setImageResource(R.drawable.pic_joinus);
    }
}
