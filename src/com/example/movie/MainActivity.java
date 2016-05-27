package com.example.movie;

import com.example.movie.home.FirstActivity;
import com.example.movie.home.SecondActivity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class MainActivity extends ActivityGroup {
	
	private FrameLayout mContent;
	private ImageButton mButtonLike;
	private ImageButton mButtonMark;
	private static final String CURRENT_PAGE = "current_page";
	private static final String HOME_LIKE_ID = "like";
	private static final String HOME_MARK_ID = "mark"; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContent = (FrameLayout) findViewById(R.id.content);
		mButtonLike = (ImageButton) findViewById(R.id.home_bt_like);
		mButtonMark = (ImageButton) findViewById(R.id.home_bt_mark);
		mButtonLike.setOnClickListener(MyClickListener);
		mButtonMark.setOnClickListener(MyClickListener);
		
		switchPages(R.id.home_bt_like);
	}
	
	public void addView(String id, Class<?> clazz) {
		Intent intent = new Intent(this, clazz);
		mContent.removeAllViews();
		mContent.addView(getLocalActivityManager().startActivity(id, intent).getDecorView());
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString(CURRENT_PAGE, getLocalActivityManager().getCurrentId());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		String currentPage = savedInstanceState.getString(CURRENT_PAGE);
		if ((currentPage != null) && (currentPage.equals(HOME_MARK_ID))) {
			switchPages(R.id.home_bt_mark);
		}
	}

	protected void switchPages(int viewId) {
		switch(viewId) {
		case R.id.home_bt_like:
			addView(HOME_LIKE_ID, FirstActivity.class);
			mButtonLike.setBackgroundResource(R.drawable.home_topbar_bt);
			mButtonLike.setImageResource(R.drawable.home_bt_like_on);
			mButtonMark.setBackgroundDrawable(null);
			mButtonMark.setImageResource(R.drawable.home_bt_mark);
			break;
		case R.id.home_bt_mark:
			addView(HOME_MARK_ID, SecondActivity.class);
			mButtonMark.setBackgroundResource(R.drawable.home_topbar_bt);
			mButtonMark.setImageResource(R.drawable.home_bt_mark_on);
			mButtonLike.setBackgroundDrawable(null);
			mButtonLike.setImageResource(R.drawable.home_bt_like);
			break;
		default:
			break;
		}
	}
	
	private View.OnClickListener MyClickListener = new View.OnClickListener() {
		
		public void onClick(View v) {
			switchPages(v.getId());
		}
	};
	
	

}


























