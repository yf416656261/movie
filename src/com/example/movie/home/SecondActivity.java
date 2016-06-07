package com.example.movie.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.movie.R;

import android.app.Activity;
import android.os.Bundle;

import com.example.movie.Cinema;
import com.example.movie.Login;

public class SecondActivity extends Activity {

	private List<Cinema> cinemaList = new ArrayList<Cinema>();
	private ListView l;
	private cinemaAdapter cAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		l = (ListView)findViewById(R.id.home_lv_mark);
		cAdapter = new cinemaAdapter(this, Login.cinema_list);
		l.setAdapter(cAdapter);
	}
	
	public class cinemaAdapter extends BaseAdapter {

		private List<Cinema> cinemaList;
		private Context context;
		private LayoutInflater layoutInflater;
		public final class cinemaListView {
			public TextView title, address, price;
		}
		public cinemaAdapter(Context context, List<Cinema> objects) {
			this.context = context;
			layoutInflater = LayoutInflater.from(context);
			cinemaList = objects;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return cinemaList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			cinemaListView cListView = null;
			if (convertView == null) {
				cListView = new cinemaListView();
				convertView = layoutInflater.inflate(R.layout.listview_cinema, null);
				cListView.title = (TextView)convertView.findViewById(R.id.name_cinema);
				cListView.address = (TextView)convertView.findViewById(R.id.address_cinema);
				cListView.price = (TextView)convertView.findViewById(R.id.price_cinema);
				convertView.setTag(cListView);
			} else {
				cListView = (cinemaListView)convertView.getTag();
			}
			cListView.title.setText((String)cinemaList.get(position).getName());
			cListView.address.setText((String)cinemaList.get(position).getAddress());
			cListView.price.setText(String.valueOf(cinemaList.get(position).getPrice()));
			
			l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					
				}
			});
			return null;
		}
		
	}

}

