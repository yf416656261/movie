package com.example.ymw.mymyotee;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.GridHolder;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ymw on 2016/6/29.
 */
public class ManActivity extends FragmentActivity {

    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private int size = 5;
    private int countsaveimage = 0;
    private boolean manorwoman = true;
    private int index_pic;
    private  int[] selectors = new int[] {
            R.drawable.hair_selector,
            R.drawable.face_selector,
            R.drawable.eye_selector,
            R.drawable.nose_selector,
            R.drawable.mouth_selector
    };
    private int[] eyes = new int[] {
            R.drawable.eye_4, R.drawable.eye_5,R.drawable.eye_6,R.drawable.eye_7,R.drawable.eye_8,
            R.drawable.eye_9,R.drawable.eye_10,R.drawable.eye_11, R.drawable.eye_12,R.drawable.eye_13, R.drawable.eye_14,R.drawable.eye_15,R.drawable.eye_16,
            R.drawable.eye_17,R.drawable.eye_18,R.drawable.eye_19,R.drawable.eye_20, R.drawable.eye_21,R.drawable.eye_22,R.drawable.eye_23,R.drawable.eye_24,
            R.drawable.eye_25, R.drawable.eye_26,R.drawable.eye_27,R.drawable.eye_28,R.drawable.eye_29, R.drawable.eye_30,R.drawable.eye_31,R.drawable.eye_32,
            R.drawable.eye_33,R.drawable.eye_34,R.drawable.eye_35,R.drawable.eye_36,R.drawable.eye_37,R.drawable.eye_38, R.drawable.eye_39,R.drawable.eye_40,
            R.drawable.eye_41,R.drawable.eye_42,R.drawable.eye_43,R.drawable.eye_44,R.drawable.eye_45,R.drawable.eye_46,R.drawable.eye_47,R.drawable.eye_48,
            R.drawable.eye_49,R.drawable.eye_50,R.drawable.eye_51,R.drawable.eye_52
    };
    private int[] hair = new int[] {
            R.drawable.hair_0,R.drawable.hair_1,R.drawable.hair_2,R.drawable.hair_3,R.drawable.hair_4,R.drawable.hair_5,R.drawable.hair_6,R.drawable.hair_7,
            R.drawable.hair_8,R.drawable.hair_9,R.drawable.hair_10,R.drawable.hair_11,R.drawable.hair_12,R.drawable.hair_13,R.drawable.hair_14,R.drawable.hair_15,
            R.drawable.hair_16,R.drawable.hair_17,R.drawable.hair_18,R.drawable.hair_19,R.drawable.hair_20,R.drawable.hair_21,R.drawable.hair_22,R.drawable.hair_23,
            R.drawable.hair_24,R.drawable.hair_25,R.drawable.hair_26,R.drawable.hair_27,R.drawable.hair_28,R.drawable.hair_29,R.drawable.hair_30,R.drawable.hair_31,
            R.drawable.hair_32,R.drawable.hair_33,R.drawable.hair_34,R.drawable.hair_35,R.drawable.hair_36,R.drawable.hair_37,R.drawable.hair_38,R.drawable.hair_39,
            R.drawable.hair_40,R.drawable.hair_41,R.drawable.hair_42,R.drawable.hair_43,R.drawable.hair_44,R.drawable.hair_45,R.drawable.hair_46,R.drawable.hair_47,
            R.drawable.hair_48,R.drawable.hair_49,R.drawable.hair_50,R.drawable.hair_51,R.drawable.hair_52,R.drawable.hair_53,R.drawable.hair_54,R.drawable.hair_55,
            R.drawable.hair_56,R.drawable.hair_57,R.drawable.hair_58,R.drawable.hair_59,R.drawable.hair_60,R.drawable.hair_61,R.drawable.hair_62,R.drawable.hair_63,
            R.drawable.hair_64,R.drawable.hair_65,R.drawable.hair_66,R.drawable.hair_67,R.drawable.hair_68,R.drawable.hair_69,R.drawable.hair_70,R.drawable.hair_71,
            R.drawable.hair_72,R.drawable.hair_73,R.drawable.hair_74
    };
    private int[] faces = new int[] {
            R.drawable.face_20000,R.drawable.face_20001,R.drawable.face_20002,R.drawable.face_20003,R.drawable.face_20004,R.drawable.face_20005,
            R.drawable.face_20006,R.drawable.face_20007,R.drawable.face_20008,R.drawable.face_20009,R.drawable.face_20010,R.drawable.face_20011,
            R.drawable.face_20012,R.drawable.face_20013,R.drawable.face_20014,R.drawable.face_20015,R.drawable.face_20016,R.drawable.face_20017,
            R.drawable.face_20018,R.drawable.face_20019
    };
    private int[] womanhair = new int[] {
            R.drawable.hair_0,R.drawable.pic_s1_g_5,R.drawable.pic_s1_g_6,R.drawable.pic_s1_g_7,R.drawable.pic_s1_g_8,R.drawable.pic_s1_g_9,
            R.drawable.pic_s1_g_10,R.drawable.pic_s1_g_11,R.drawable.pic_s1_g_12,R.drawable.pic_s1_g_13,R.drawable.pic_s1_g_14,R.drawable.pic_s1_g_15,
            R.drawable.pic_s1_g_16,R.drawable.pic_s1_g_17,R.drawable.pic_s1_g_18,R.drawable.pic_s1_g_20,R.drawable.pic_s1_g_21,
            R.drawable.pic_s1_g_22,R.drawable.pic_s1_g_23,R.drawable.pic_s1_g_24,R.drawable.pic_s1_g_25,R.drawable.pic_s1_g_26,
            R.drawable.pic_s1_g_27,R.drawable.pic_s1_g_28,R.drawable.pic_s1_g_29,R.drawable.pic_s1_g_30001,R.drawable.pic_s1_g_30002,
            R.drawable.pic_s1_g_30003,R.drawable.pic_s1_g_30004,R.drawable.pic_s1_g_30005,R.drawable.pic_s1_g_30006,R.drawable.pic_s1_g_30007,
            R.drawable.pic_s1_g_30008,R.drawable.pic_s1_g_30009,R.drawable.pic_s1_g_30010
    };
    private ScrollIndicatorView scrollIndicatorView;
    private ToggleButton pinnedToggleButton;
    private ToggleButton splitAutotoggleButton;
    private int unSelectTextColor;
    private Handler mHandler = new Handler();
    private WebView webView;
    private String[] mansharename = new String[] {
            "QQ",
            "Qzone",
            "Weibo",
            "Moments",
            "QQHeader",
            "Wechat"
    };
    private int[] manshareimg = new int[] {
            R.drawable.share_bt_qqhaoyou_up,
            R.drawable.share_bt_qzone_up,
            R.drawable.share_bt_sinaweibo_up,
            R.drawable.share_bt_pyq_up,
            R.drawable.share_bt_qqtouxiang_up,
            R.drawable.share_bt_weixin_up
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man);
        final ImageButton manimagebutton1 = (ImageButton) findViewById(R.id.manimageButton1);
        manimagebutton1.setImageResource(R.drawable.img_return);
        manimagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final ImageButton manimagebutton2 = (ImageButton) findViewById(R.id.manimageButton2);
        manimagebutton2.setImageResource(R.drawable.bt_save_up);
        manimagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String savename = countsaveimage+".png";
                countsaveimage++;
                saveImage(savename);
            }
        });
        final ImageButton manimagebuton3 = (ImageButton) findViewById(R.id.manimageButton3);
        manimagebuton3.setImageResource(R.drawable.bt_share_up);
        manimagebuton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManActivity.this, "已分享", Toast.LENGTH_SHORT).show();
            }
        });
        List<Map<String, Object>> mansharelistItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < manshareimg.length; i++) {
            Map<String, Object>  mansharelistItem = new HashMap<String, Object>();
            mansharelistItem.put("manshare_img",manshareimg[i]);
            mansharelistItem.put("manshare_name",mansharename[i]);
            mansharelistItems.add(mansharelistItem);
        }
        final SimpleAdapter dialogAdaper = new SimpleAdapter(ManActivity.this,mansharelistItems,R.layout.man_simple_grid_item,
                new String[] {"manshare_img","manshare_name"}, new int[] {R.id.manshareimage_view,R.id.mansharetext_view});
        ImageButton manimageButton3 = (ImageButton)findViewById(R.id.manimageButton3);
        manimagebuton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus mansharedialogPlus = DialogPlus.newDialog(ManActivity.this)
                        .setAdapter(dialogAdaper)
                        .setCancelable(true)
                        .setContentHolder(new GridHolder(3))
                        .setHeader(R.layout.manshare_header)
                        .setGravity(Gravity.CENTER)
                        .create();
                mansharedialogPlus.getHeaderView().findViewById(R.id.mansharebutton1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mansharedialogPlus.dismiss();
                    }
                });
                mansharedialogPlus.show();
            }
        });
        //webview
        webView = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view,url);
                webView.loadUrl("javascript: initHeadEdit()");
            }
        });
        webView.loadUrl("file:///android_asset/headEdit.html");
        //manorwoman
        Intent intent = getIntent();
        manorwoman = (boolean)intent.getBooleanExtra("isman",true);
        //third
        ViewPager viewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
        scrollIndicatorView = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);
        scrollIndicatorView.setBackgroundColor(Color.WHITE);
        unSelectTextColor = Color.WHITE;
        viewPager.setOffscreenPageLimit(2);
        int color = Color.BLUE;
        scrollIndicatorView.setScrollBar(new ColorBar(getApplicationContext(),color, 8));
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        inflate = LayoutInflater.from(this);
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }
    //
    //saveWebview
    public Bitmap convertViewToBitmap(WebView view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);
        //把view中的内容绘制在画布上
        view.draw(canvas);
        return bitmap;
    }
//    public static String getPath()  {
//        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
//            return Environment.getExternalStorageDirectory().toString() + "/saving_picture";
//    }
    public void saveImage(String filename) {
        Log.e("save", "保存图片");
        Bitmap bitmap = convertViewToBitmap(webView);
        File extDir = Environment.getExternalStorageDirectory();
        File fullfillname = new File(extDir,filename);
        try{
            fullfillname.createNewFile();
            FileOutputStream fos = new FileOutputStream(fullfillname);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            Log.e("save", "保存成功");
            Toast.makeText(ManActivity.this, "已存储到手机:"+Environment.getExternalStorageDirectory().toString(), Toast.LENGTH_SHORT).show();
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //
    public void changeImage(String type, int pos) {
        //System.out.println(type);
        //System.out.println(pos);
        webView.loadUrl("javascript:personInitA('" + type + "," + pos + "')");
    }
    public boolean gettype() {
        return manorwoman;
    }
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView == splitAutotoggleButton) {
                // 设置是否自动布局
                scrollIndicatorView.setSplitAuto(isChecked);
            } else if (buttonView == pinnedToggleButton) {
                scrollIndicatorView.setPinnedTabView(isChecked);
                // 设置固定tab的shadow，这里不设置的话会使用默认的shadow绘制
                scrollIndicatorView.setPinnedShadow(R.drawable.tabshadow, dipToPix(4));
                scrollIndicatorView.setPinnedTabBgColor(Color.RED);
            }
        }
    };
    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
               // convertView = inflate.inflate(R.layout.cell_select, container, false);
            }
            Button button = (Button) convertView;
            //ImageView imageView = (ImageView)findViewById(R.id.cell_selectimageview1);
            button.setBackground(getResources().getDrawable(selectors[position]));
            int padding = dipToPix(10);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            MoreFragment fragment = new MoreFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(MoreFragment.getIndex(), position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_NONE;
        }


    };
    /**
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }
}
