package com.example.movie;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.movie.R;

public class MainTabsActivity extends TabActivity implements
		CompoundButton.OnCheckedChangeListener {

	private static final String HOME_TAB = "home_tab";
	private static final String PERSON_TAB = "person_tab";
	
	private Intent mHomeIntent = null;
	private Intent mPersonIntent = null;

	private TabHost mTabHost = null;

	private TextView mMessageTipsMention = null;
	private TextView mMessageTipsPerson = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main_tabs);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);

		mTabHost = getTabHost();
		initIntents();
		initTips();
		initRadios();
		setupIntents();
	}
	
	private void initIntents() {
		mHomeIntent = new Intent(this, MainActivity.class);
		mPersonIntent = new Intent(this, User_info.class);
	}
	
	private void initTips() {
	}

	private void initRadios() {
		((RadioButton) findViewById(R.id.radio_home))
				.setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.radio_person_info))
				.setOnCheckedChangeListener(this);
	}

	private void setupIntents() {
		((RadioButton) findViewById(R.id.radio_home)).setChecked(true);
		mTabHost.addTab(buildTabSpec(HOME_TAB, mHomeIntent));
		mTabHost.addTab(buildTabSpec(PERSON_TAB, mPersonIntent));
		mTabHost.setCurrentTabByTag(HOME_TAB);
	}

	private TabHost.TabSpec buildTabSpec(String tag, Intent intent) {
		TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tag);
		tabSpec.setContent(intent).setIndicator("",
				getResources().getDrawable(R.drawable.icon));
		return tabSpec;
	}

	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.radio_home:
				mTabHost.setCurrentTabByTag(HOME_TAB);
				break;
			case R.id.radio_person_info:
				mTabHost.setCurrentTabByTag(PERSON_TAB);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if ((event.getAction() == KeyEvent.ACTION_DOWN)
				&& (event.getKeyCode() == KeyEvent.KEYCODE_BACK)) {
			quitDialog();
		}
		return super.dispatchKeyEvent(event);
	}

	private void quitDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.alerm_title)
				.setIcon(null)
				.setCancelable(false)
				.setMessage(R.string.alert_quit_confirm)
				.setPositiveButton(R.string.alert_yes_button,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								MainTabsActivity.this.finish();
							}
						})
				.setNegativeButton(R.string.alert_no_button,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).create().show();
	}
}
