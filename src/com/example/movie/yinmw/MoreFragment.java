package com.example.ymw.mymyotee;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.shizhefei.fragment.LazyFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreFragment extends LazyFragment {
    private GridView gridView;
    private int tabIndex;
    private boolean mantype = true;
    private Handler handler;
    private static final String INTENT_INT_INDEX = "intent_int_index";
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
    private int[] nose = new int[] {
            R.drawable.pic_s14_20000, R.drawable.pic_s14_20001, R.drawable.pic_s14_20002, R.drawable.pic_s14_20003, R.drawable.pic_s14_20004,
            R.drawable.pic_s14_20004, R.drawable.pic_s14_20006, R.drawable.pic_s14_20007, R.drawable.pic_s14_20008, R.drawable.pic_s14_20009,
            R.drawable.pic_s14_20010, R.drawable.pic_s14_20011, R.drawable.pic_s14_20012, R.drawable.pic_s14_20013, R.drawable.pic_s14_20014,
            R.drawable.pic_s14_20015, R.drawable.pic_s14_20016, R.drawable.pic_s14_20017, R.drawable.pic_s14_20018, R.drawable.pic_s14_20019,
            R.drawable.pic_s14_20020, R.drawable.pic_s14_20021, R.drawable.pic_s14_20022, R.drawable.pic_s14_20023, R.drawable.pic_s14_20024,
            R.drawable.pic_s14_20025, R.drawable.pic_s14_20026, R.drawable.pic_s14_20027, R.drawable.pic_s14_20028, R.drawable.pic_s14_20029,
            R.drawable.pic_s14_20030, R.drawable.pic_s14_20031, R.drawable.pic_s14_20032, R.drawable.pic_s14_20033, R.drawable.pic_s14_20034,
            R.drawable.pic_s14_20035, R.drawable.pic_s14_20036, R.drawable.pic_s14_20037, R.drawable.pic_s14_20038
    };
    private int[] mouth = new int[] {
            R.drawable.pic_s5_1,R.drawable.pic_s5_2,R.drawable.pic_s5_3,R.drawable.pic_s5_4,R.drawable.pic_s5_5,R.drawable.pic_s5_6,
            R.drawable.pic_s5_7,R.drawable.pic_s5_8,R.drawable.pic_s5_9,R.drawable.pic_s5_10,R.drawable.pic_s5_11,R.drawable.pic_s5_12,
            R.drawable.pic_s5_13,R.drawable.pic_s5_14,R.drawable.pic_s5_15,R.drawable.pic_s5_16,R.drawable.pic_s5_17,R.drawable.pic_s5_18,
            R.drawable.pic_s5_19,R.drawable.pic_s5_20,R.drawable.pic_s5_21,R.drawable.pic_s5_22,R.drawable.pic_s5_23,R.drawable.pic_s5_24,
            R.drawable.pic_s5_25,R.drawable.pic_s5_26,R.drawable.pic_s5_27,R.drawable.pic_s5_28,R.drawable.pic_s5_29,R.drawable.pic_s5_30,
            R.drawable.pic_s5_31,R.drawable.pic_s5_32,R.drawable.pic_s5_33,R.drawable.pic_s5_34,R.drawable.pic_s5_35,R.drawable.pic_s5_36,
            R.drawable.pic_s5_37,R.drawable.pic_s5_38,R.drawable.pic_s5_39,R.drawable.pic_s5_40,R.drawable.pic_s5_41,R.drawable.pic_s5_42,
            R.drawable.pic_s5_43,R.drawable.pic_s5_44,R.drawable.pic_s5_45,R.drawable.pic_s5_46,R.drawable.pic_s5_47,R.drawable.pic_s5_48,
            R.drawable.pic_s5_49,R.drawable.pic_s5_50,R.drawable.pic_s5_51,R.drawable.pic_s5_52,R.drawable.pic_s5_53,R.drawable.pic_s5_54,
            R.drawable.pic_s5_55,R.drawable.pic_s5_56,R.drawable.pic_s5_57,R.drawable.pic_s5_58,R.drawable.pic_s5_59,R.drawable.pic_s5_60,
            R.drawable.pic_s5_61
    };
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        Looper looper = Looper.getMainLooper(); //获取主线程的looper
        //将handler与主线程关联
        handler = new Handler(looper) {
            public void handleMessage(android.os.Message msg) { //处理消息
                gridView.setVisibility(View.VISIBLE);
            }
        };
        setContentView(R.layout.fragment_tabmain_item);
        tabIndex = getArguments().getInt(INTENT_INT_INDEX);
        gridView = (GridView) findViewById(R.id.manselectgridView1);
        List<Map<String, Object>> hairlistItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < hair.length; i++) {
            Map<String, Object> hairlistItem = new HashMap<String, Object>();
            hairlistItem.put("hair",hair[i]);
            hairlistItems.add(hairlistItem);
        }
        List<Map<String, Object>> facelistItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < faces.length; i++) {
            Map<String, Object> facelistItem = new HashMap<String, Object>();
            facelistItem.put("face",faces[i]);
            facelistItems.add(facelistItem);
        }
        List<Map<String, Object>> eyelistItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < faces.length; i++) {
            Map<String, Object> eyelistItem = new HashMap<String, Object>();
            eyelistItem.put("eye",eyes[i]);
            eyelistItems.add(eyelistItem);
        }
        List<Map<String, Object>> womanhairlistItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < womanhair.length; i++) {
            Map<String, Object> womanhairlistItem = new HashMap<String, Object>();
            womanhairlistItem.put("womanhair",womanhair[i]);
            womanhairlistItems.add(womanhairlistItem);
        }
        List<Map<String, Object>> noselistItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < nose.length; i++) {
            Map<String, Object> mannoselistItem = new HashMap<String, Object>();
            mannoselistItem.put("nose",nose[i]);
            noselistItems.add(mannoselistItem);
        }
        List<Map<String, Object>> mouthlistItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mouth.length; i++) {
            Map<String, Object> mouthlistItem = new HashMap<String, Object>();
            mouthlistItem.put("mouth",mouth[i]);
            mouthlistItems.add(mouthlistItem);
        }
        //judge the type
        mantype = ((ManActivity)getActivity()).gettype();
        if (mantype == true) {
            if (tabIndex == 0) {
                SimpleAdapter hairAdapter = new SimpleAdapter(MoreFragment.this.getContext(),hairlistItems,R.layout.cell_select,
                        new String[]{"hair"}, new int[] {R.id.cell_selectimageview1});
                gridView.setAdapter(hairAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ManActivity)getActivity()).changeImage("hair",position);
                        //ImageView changeimageview = (ImageView)findViewById(R.id.cell_selectimageview1);
                        //changeimageview.setBackground(getResources().getDrawable(R.drawable.chang_shape));
                        //changeimageview.setBackground(getResources().getDrawable(R.drawable.chang_shape));
                       // gridView.setSelector(new ColorDrawable(Color.BLACK));
                        //Toast toast = Toast.makeText(getApplicationContext(),"点击"+position,Toast.LENGTH_SHORT);
                        //toast.show();
                    }
                });
            }
            if (tabIndex == 1) {
                SimpleAdapter faceAdapter = new SimpleAdapter(MoreFragment.this.getContext(),facelistItems,R.layout.cell_select,
                        new String[] {"face"}, new int[] {R.id.cell_selectimageview1});
                gridView.setAdapter(faceAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ManActivity)getActivity()).changeImage("face",position);
                        //ImageView changeimageview = (ImageView)findViewById(R.id.cell_selectimageview1);
                        //changeimageview.setBackground(getResources().getDrawable(R.drawable.chang_shape));
                       // gridView.setSelector(new ColorDrawable(Color.BLACK));
                        //ImageView changeimageview = (ImageView)findViewById(R.id.cell_selectimageview1);
                        //changeimageview.setBackground(getResources().getDrawable(R.drawable.chang_shape));
                        //Toast toast = Toast.makeText(getApplicationContext(),"点击"+position,Toast.LENGTH_SHORT);
                        //toast.show();
                    }
                });
            }
            if (tabIndex == 2) {
                SimpleAdapter eyeAdapter = new SimpleAdapter(MoreFragment.this.getContext(),eyelistItems,R.layout.cell_select,
                        new String[] {"eye"}, new int[] {R.id.cell_selectimageview1});
                gridView.setAdapter(eyeAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ManActivity)getActivity()).changeImage("eye",position);
                       // ImageView changeimageview = (ImageView)findViewById(R.id.cell_selectimageview1);
                       // changeimageview.setBackground(getResources().getDrawable(R.drawable.chang_shape));
                        //gridView.setSelector(new ColorDrawable(Color.BLACK));
                        //ImageView changeimageview = (ImageView)findViewById(R.id.cell_selectimageview1);
                       // changeimageview.setBackground(getResources().getDrawable(R.drawable.chang_shape));
                        //Toast toast = Toast.makeText(getApplicationContext(),"点击"+position,Toast.LENGTH_SHORT);
                        //toast.show();
                    }
                });
            }
            if (tabIndex == 3) {
                SimpleAdapter noseAdapter = new SimpleAdapter(MoreFragment.this.getContext(),noselistItems,R.layout.cell_select,
                        new String[] {"nose"}, new int[] {R.id.cell_selectimageview1});
                gridView.setAdapter(noseAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ManActivity)getActivity()).changeImage("nose",position);
                        // ImageView changeimageview = (ImageView)findViewById(R.id.cell_selectimageview1);
                        // changeimageview.setBackground(getResources().getDrawable(R.drawable.chang_shape));
                        //gridView.setSelector(new ColorDrawable(Color.BLACK));
                        //ImageView changeimageview = (ImageView)findViewById(R.id.cell_selectimageview1);
                        // changeimageview.setBackground(getResources().getDrawable(R.drawable.chang_shape));
                        //Toast toast = Toast.makeText(getApplicationContext(),"点击"+position,Toast.LENGTH_SHORT);
                        //toast.show();
                    }
                });
            }
            if (tabIndex == 4) {
                SimpleAdapter mouthAdapter = new SimpleAdapter(MoreFragment.this.getContext(),mouthlistItems,R.layout.cell_select,
                        new String[] {"mouth"}, new int[] {R.id.cell_selectimageview1});
                gridView.setAdapter(mouthAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ManActivity)getActivity()).changeImage("mouth",position);
                        // ImageView changeimageview = (ImageView)findViewById(R.id.cell_selectimageview1);
                        // changeimageview.setBackground(getResources().getDrawable(R.drawable.chang_shape));
                        //gridView.setSelector(new ColorDrawable(Color.BLACK));
                        //ImageView changeimageview = (ImageView)findViewById(R.id.cell_selectimageview1);
                        // changeimageview.setBackground(getResources().getDrawable(R.drawable.chang_shape));
                        //Toast toast = Toast.makeText(getApplicationContext(),"点击"+position,Toast.LENGTH_SHORT);
                        //toast.show();
                    }
                });
            }
        }
        //woman
        if (mantype == false) {
            if (tabIndex == 0) {
                SimpleAdapter hairAdapter = new SimpleAdapter(MoreFragment.this.getContext(),hairlistItems,R.layout.cell_select,
                        new String[]{"hair"}, new int[] {R.id.cell_selectimageview1});
                gridView.setAdapter(hairAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ManActivity)getActivity()).changeImage("hair",position);
                    }
                });
            }
            if (tabIndex == 1) {
                SimpleAdapter faceAdapter = new SimpleAdapter(MoreFragment.this.getContext(),facelistItems,R.layout.cell_select,
                        new String[] {"face"}, new int[] {R.id.cell_selectimageview1});
                gridView.setAdapter(faceAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ManActivity)getActivity()).changeImage("face",position);
                    }
                });
            }
            if (tabIndex == 2) {
                SimpleAdapter eyeAdapter = new SimpleAdapter(MoreFragment.this.getContext(),eyelistItems,R.layout.cell_select,
                        new String[] {"eye"}, new int[] {R.id.cell_selectimageview1});
                gridView.setAdapter(eyeAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ManActivity)getActivity()).changeImage("eye",position);
                    }
                });
            }
        }

//       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               //ManActivity manActivity = new ManActivity();
//               if (mantype = true) {
//                   if (tabIndex == 0) {
//                       ((ManActivity)getActivity()).sethairImageView(position);
//                   }
//                   if (tabIndex == 1) {
//                       ((ManActivity)getActivity()).setfaceImageView(position);
//                   }
//                   if (tabIndex == 2) {
//                       ((ManActivity)getActivity()).seteyeImageView(position);
//                   }
//               }
//               if (mantype == false) {
//                   if (tabIndex == 0) {
//                       ((ManActivity)getActivity()).setWomanhairImageView(position);
//                   }
//                   if (tabIndex == 1) {
//                       ((ManActivity)getActivity()).setfaceImageView(position);
//                   }
//                   if (tabIndex == 2) {
//                       ((ManActivity)getActivity()).seteyeImageView(position);
//                   }
//               }
//               Toast toast = Toast.makeText(getApplicationContext(),"点击"+position,Toast.LENGTH_SHORT);
//               toast.show();
//           }
//       });
        handler.sendEmptyMessageDelayed(1, 2000);
    }
    @Override
    public void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        handler.removeCallbacksAndMessages(null);
    }
    public static  String  getIndex() {
        return INTENT_INT_INDEX;
    }
    public int getTabIndex() {
        return tabIndex;
    }
}
