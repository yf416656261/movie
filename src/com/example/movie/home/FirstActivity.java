package com.example.movie.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.movie.ContentActivity;
import com.example.movie.Login;
import com.example.movie.Movie;
import com.example.movie.R;

import android.app.Activity;
import android.os.Bundle;

public class FirstActivity extends Activity {
	
	
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView l;
	private movieAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		l = (ListView)findViewById(R.id.home_lv_forum);
		mAdapter = new movieAdapter(this, Login.movie_list);
		//l.setAdapter(mAdapter);
	}
	
	public class movieAdapter extends BaseAdapter {

		private List<Movie> movieList;
		private Context context;
		private LayoutInflater layoutInflater;
		public final class movieListView {
			public TextView title;
			public TextView content;
			public ImageView imageID;
		}
		public movieAdapter(Context context, List<Movie> objects) {
			this.context = context;
			layoutInflater = LayoutInflater.from(context);
			movieList = objects;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return movieList.size();
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			movieListView mListView = null;
			if (convertView == null) {
				mListView = new movieListView();
				convertView = layoutInflater.inflate(R.layout.listview, null);
				mListView.title = (TextView)findViewById(R.id.listview_title);
				mListView.content = (TextView)findViewById(R.id.listview_content);
				mListView.imageID = (ImageView)findViewById(R.id.listview_image);
				convertView.setTag(mListView);
			} else {
				mListView = (movieListView)convertView.getTag();
			}
			mListView.title.setText((String)movieList.get(position).getTitle());
			mListView.content.setText((String)movieList.get(position).getComment());
			//¶¯Ì¬Í¼Æ¬µ÷ÓÃ
			l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(FirstActivity.this, ContentActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("MID", String.valueOf(movieList.get(position).getID()));
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
				
			});
			return convertView;
		}
		
	}
	
}

