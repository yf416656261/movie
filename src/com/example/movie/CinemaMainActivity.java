package com.example.movie;

import HorizontalListView.HorizontalListView;
import HorizontalListView.HorizontalListViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CinemaMainActivity extends Activity {

	
	HorizontalListView hListView;
	HorizontalListViewAdapter  hListViewAdapter;
	TextView name, address, movie_name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cinema_main);
		initUI();
		
		name = (TextView)findViewById(R.id.cinema_act_name);
		address = (TextView)findViewById(R.id.cinema_act_address);
		movie_name = (TextView)findViewById(R.id.cinema_act_mname);
		Intent intent = getIntent();
		String setName = intent.getStringExtra("name");
		name.setText(setName);
		String setAdd = intent.getStringExtra("address");
		address.setText(setAdd);
	}
	
	public void initUI() {
		hListView = (HorizontalListView)findViewById(R.id.horizontal_listview);
		hListViewAdapter = new HorizontalListViewAdapter(this, Login.movie_list);
		hListView.setAdapter(hListViewAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cinema_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
