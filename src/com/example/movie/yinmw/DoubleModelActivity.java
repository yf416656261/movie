package com.example.ymw.mymyotee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by ymw on 2016/6/28.
 */
public class DoubleModelActivity extends Activity {
    private int[] dib1 = new int[] {
            R.drawable.bt_dmode0_up,
            R.drawable.bt_dmode0_down,
    };
    private  int[] dib2 = new int[] {
            R.drawable.bt_dmode1_up,
            R.drawable.bt_dmode1_down,
    };
    private  int[] dib3 = new int[] {
            R.drawable.bt_dmode2_up,
            R.drawable.bt_dmode2_down,
    };
    private int dib1count = 0;
    private int dib2count = 0;
    private int dib3count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doublemodel);
        final  ImageButton doubleimagebutton0 = (ImageButton)findViewById(R.id.doubleimageButton0);
        doubleimagebutton0.setImageResource(R.drawable.img_return);
        doubleimagebutton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final ImageButton doubleimagebutton1 = (ImageButton)findViewById(R.id.doubleimageButton1);
        doubleimagebutton1.setImageResource(dib1[0]);
        doubleimagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doubleimagebutton1.setImageResource(dib1[++dib1count % dib1.length]);
            }
        });
        final ImageButton doubleimagebutton2 = (ImageButton)findViewById(R.id.doubleimageButton2);
        doubleimagebutton2.setImageResource(dib2[0]);
        doubleimagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doubleimagebutton2.setImageResource(dib2[++dib2count % dib2.length]);
            }
        });
        final ImageButton doubleimagebutton3 = (ImageButton)findViewById(R.id.doubleimageButton3);
        doubleimagebutton3.setImageResource(dib3[0]);
        doubleimagebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doubleimagebutton3.setImageResource(dib3[++dib3count % dib3.length]);
            }
        });
    }
}
