package com.example.ymw.mymyotee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.GridHolder;
import com.orhanobut.dialogplus.OnBackPressListener;
import com.orhanobut.dialogplus.OnCancelListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private  boolean isman = true;
    private boolean isVoice = true;
    private int img1count = 0;
    private int img2count = 0;
    private int img3count = 0;
    private int img4count = 0;
    private int img5count = 0;
    private int img6count = 0;
    private int img7count = 0;
    private int img8count = 0;
    private RadioGroup radioGroup;
    private CheckBox headerCheckBox;
    private CheckBox footerCheckBox;
    private CheckBox expandedCheckBox;
    private int[] firstimgbutton = new int[] {
            R.drawable.bt_homepage_sound_on,
            R.drawable.bt_homepage_sound_off,
    };
    private int[] secondimgbutton = new int[] {
            R.drawable.bt_homepage_share_up,
            R.drawable.bt_homepage_share_down,
    };
    private int[] thirdimgbutton = new int[] {
            R.drawable.bt_yijianfankui_up,
            R.drawable.bt_yijianfankui_down,
    };
    private int[] fourthimgbutton = new int[] {
            R.drawable.bt_man_up,
            R.drawable.bt_man_down,
    };
    private int[] fifthimgbutton = new int[] {
            R.drawable.bt_woman_up,
            R.drawable.bt_woman_down,
    };
    private int[] sixthimgbutton = new int[] {
           R.drawable.bt_double_up,
            R.drawable.bt_double_down,
    };
    private int[] seventhimgbutton = new int[] {
            R.drawable.bt_history_up,
            R.drawable.bt_history_down,
    };
    private int[] eighthimgbutton = new int[] {
            R.drawable.pic_newproduct,
    };
    private String[] sharename = new String[] {
            "QQ",
            "Qzone",
            "Weibo",
            "Moments"
    };
    private int[] shareimg = new int[] {
            R.drawable.share_bt_qqhaoyou_up,
            R.drawable.share_bt_qzone_up,
            R.drawable.share_bt_sinaweibo_up,
            R.drawable.share_bt_pyq_up
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final  ImageButton imgbutton1 = (ImageButton) findViewById(R.id.imagebutton1);
        imgbutton1.setImageResource(firstimgbutton[0]);
        imgbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbutton1.setImageResource(firstimgbutton[++img1count%firstimgbutton.length]);
                if (isVoice) {
                    isVoice = false;
                } else {
                    isVoice = true;
                }
            }
        });
        final ImageButton imgbutton2 = (ImageButton) findViewById(R.id.imagebutton2);
        imgbutton2.setImageResource(secondimgbutton[0]);
        List<Map<String, Object>> dialoglistItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < shareimg.length; i++) {
            Map<String, Object> dialoglistItem = new HashMap<String, Object>();
            dialoglistItem.put("share_img",shareimg[i]);
            dialoglistItem.put("share_name",sharename[i]);
            dialoglistItems.add(dialoglistItem);
        }
      final  SimpleAdapter dialogAdaper = new SimpleAdapter(MainActivity.this,dialoglistItems,R.layout.simple_grid_item,
                new String[] {"share_img","share_name"}, new int[] {R.id.shareimage_view,R.id.sharetext_view});
        imgbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbutton2.setImageResource(secondimgbutton[++img2count % secondimgbutton.length]);
                final DialogPlus dialogPlus = DialogPlus.newDialog(MainActivity.this)
                        .setAdapter(dialogAdaper)
                        .setCancelable(true)
                        .setContentHolder(new GridHolder(2))
                        .setHeader(R.layout.share_header)
                        .setGravity(Gravity.CENTER)
                        .create();
                dialogPlus.getHeaderView().findViewById(R.id.sharebutton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogPlus.dismiss();
                    }
                });
                dialogPlus.show();
            }
        });
        final ImageButton imgbutton3 = (ImageButton) findViewById(R.id.imagebutton3);
        imgbutton3.setImageResource(thirdimgbutton[0]);
        imgbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbutton3.setImageResource(thirdimgbutton[++img3count % thirdimgbutton.length]);
                Intent intent = new Intent(MainActivity.this,AdviceFeedbackActivity.class);
                startActivity(intent);
            }
        });
        final ImageView imgview1 = (ImageView) findViewById(R.id.imageView1);
        imgview1.setImageResource(R.drawable.pic_startpage_logo_faceq);
        final ImageButton imgbutton4 = (ImageButton) findViewById(R.id.imagebutton4);
        imgbutton4.setImageResource(fourthimgbutton[0]);
        imgbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbutton4.setImageResource(fourthimgbutton[++img4count % fourthimgbutton.length]);
                if (isVoice) {
                    MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.boy);
                    mPlayer.start();
                }
                isman = true;
                Bundle bundle=new Bundle();
                bundle.putBoolean("isman",isman);
                Intent intent = new Intent(MainActivity.this,ManActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        final ImageButton imgbutton5 = (ImageButton) findViewById(R.id.imagebutton5);
        imgbutton5.setImageResource(fifthimgbutton[0]);
        imgbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbutton5.setImageResource(fifthimgbutton[++img5count%fifthimgbutton.length]);
                if (isVoice) {
                    MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this,R.raw.girl);
                    mPlayer.start();
                }
                isman = false;
                Bundle bundle=new Bundle();
                bundle.putBoolean("isman",isman);
                Intent intent = new Intent(MainActivity.this,ManActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        final ImageButton imgbutton6 = (ImageButton) findViewById(R.id.imagebutton6);
        imgbutton6.setImageResource(sixthimgbutton[0]);
        imgbutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbutton6.setImageResource(sixthimgbutton[++img6count%sixthimgbutton.length]);
                Intent intent = new Intent(MainActivity.this,DoubleModelActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton imgbutton7 = (ImageButton) findViewById(R.id.imagebutton7);
        imgbutton7.setImageResource(seventhimgbutton[0]);
        imgbutton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbutton7.setImageResource(seventhimgbutton[++img7count % seventhimgbutton.length]);
            }
        });
        final ImageButton imgbutton8 = (ImageButton) findViewById(R.id.imagebutton8);
        imgbutton8.setImageResource(eighthimgbutton[0]);
        imgbutton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(MainActivity.this)
                        .setCancelable(true)
                        .setContentHolder(new ViewHolder(R.layout.newcontent))
                        .setFooter(R.layout.newfooter)
                        .setGravity(Gravity.CENTER)
                        .create();
                dialogPlus.getFooterView().findViewById(R.id.newbutton1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogPlus.getFooterView().findViewById(R.id.newbutton1).setBackgroundColor(getResources().getColor(R.color.skyblue));
                        dialogPlus.dismiss();
                    }
                });
                dialogPlus.getFooterView().findViewById(R.id.newbutton2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("http://www.bqtalk.com/indexP.html");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        dialogPlus.dismiss();
                    }
                });
                dialogPlus.show();
            }
        });
        final ImageButton imgbutton9 = (ImageButton) findViewById(R.id.imagebutton9);
        imgbutton9.setImageResource(R.drawable.pic_startpage_joinus);
        imgbutton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,JoinUsActivity.class);
                startActivity(intent);
            }
        });
        final ImageView imgview2 = (ImageView) findViewById(R.id.imageView2);
        imgview2.setImageResource(R.drawable.pic_startpage_aboutus);
        final ImageButton imgbutton10 = (ImageButton) findViewById(R.id.imagebutton10);
        imgbutton10.setImageResource(R.drawable.pic_startpage_team);
        imgbutton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TeamIntroduceActivity.class);
                startActivity(intent);
            }
        });
        final ImageView imgview3 = (ImageView) findViewById(R.id.imageView3);
        imgview3.setImageResource(R.drawable.pic_copyright);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
