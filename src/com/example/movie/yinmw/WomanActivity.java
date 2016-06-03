package com.example.ymw.mymyotee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by ymw on 2016/6/29.
 */
public class WomanActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.woman);
        final ImageButton womanimagebutton1 = (ImageButton) findViewById(R.id.womanimageButton1);
        womanimagebutton1.setImageResource(R.drawable.img_return);
        womanimagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final ImageButton womanimagebutton2 = (ImageButton) findViewById(R.id.womanimageButton2);
        womanimagebutton2.setImageResource(R.drawable.bt_save_up);
        womanimagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WomanActivity.this, "已保存", Toast.LENGTH_SHORT).show();
            }
        });
        final ImageButton womanimagebuton3 = (ImageButton) findViewById(R.id.womanimageButton3);
        womanimagebuton3.setImageResource(R.drawable.bt_share_up);
        womanimagebuton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WomanActivity.this, "已分享", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
