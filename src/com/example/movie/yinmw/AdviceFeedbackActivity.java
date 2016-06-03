package com.example.ymw.mymyotee;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by ymw on 2016/6/28.
 */
public class AdviceFeedbackActivity extends Activity {
    private String savefeedback = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advicefeedback);
        final int color = Color.BLUE;
        final Button feedbcakbutton1 = (Button) findViewById(R.id.feedbackbutton1);
        feedbcakbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbcakbutton1.setBackgroundColor(getResources().getColor(R.color.skyblue));
                //Intent intent = new Intent(AdviceFeedbackActivity.this,MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });
        final Button feedbackbutton2 = (Button) findViewById(R.id.feedbackbutton2);
        feedbackbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackbutton2.setBackgroundColor(getResources().getColor(R.color.skyblue));
                final EditText edittext = (EditText)findViewById(R.id.feedbackeditText2);
                String aditvalue = edittext.getText().toString();
                if (!aditvalue.isEmpty()) {
                    final TextView textview2 = (TextView)findViewById(R.id.feedtextView2);
                    String textview2text = textview2.getText().toString()+"\n";
                    final Calendar c = Calendar.getInstance();
                    int  mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH)+1;
                    int mDay = c.get(Calendar.DAY_OF_MONTH);
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);
                    int mScond = c.get(Calendar.SECOND);
                    String nowString="---------------------------"+mYear+"-"+mMonth+"-"+mDay+"  "+mHour+":"+mMinute+":"+mScond+"---------------" +
                            "--------------"+"\n";
                    String tempstr =  nowString + aditvalue+"\n";
                    textview2.setText(textview2text+tempstr);
                    edittext.setText("");
                }
            }
        });
    }
}
