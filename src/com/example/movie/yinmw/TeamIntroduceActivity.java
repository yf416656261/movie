package com.example.ymw.mymyotee;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ymw on 2016/6/28.
 */
public class TeamIntroduceActivity extends Activity {
    private int[] imgs = new int[] {
            R.drawable.img01,
            R.drawable.img02,
            R.drawable.img03,
            R.drawable.img04,
            R.drawable.img05,
            R.drawable.img06,
            R.drawable.img07,
            R.drawable.img08,
            R.drawable.img09,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamintroduce);
        List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imgs.length;i++) {
            Map<String,Object> listItem = new HashMap<String, Object>();
            listItem.put("image",imgs[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.cell,new String[]{"image"},new int[]{R.id.teamcell});
        GridView gridview = (GridView)findViewById(R.id.teamgridView1);
        gridview.setAdapter(simpleAdapter);
        final ImageView teamimgview1 = (ImageView) findViewById(R.id.teamimageView1);
        teamimgview1.setImageResource(R.drawable.pic_aboutus_contactus);
        final ImageButton teamimagebutton1 = (ImageButton) findViewById(R.id.teamimageButton1);
        teamimagebutton1.setImageResource(R.drawable.img_return);
        teamimagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
