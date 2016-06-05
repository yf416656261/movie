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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
	
	public static List<Movie> search_list = null;
	
	EditText s_in;
	Button search;
	HttpURLConnection connection = null;
	DataOutputStream out;
	InputStream in;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		s_in = (EditText)findViewById(R.id.search_in);
		search = (Button)findViewById(R.id.search);
		
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				search_list = null;
				String str_search = s_in.getText().toString();
				final String url = Login.URL + "/search";
				final String query = "name=" + str_search;
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String result = "";
						try {
							connection = (HttpURLConnection)((new URL(url).openConnection()));
							connection.setRequestMethod("POST");
							connection.setConnectTimeout(40000);
							connection.setReadTimeout(40000);
							
							out = new DataOutputStream(connection.getOutputStream());
							out.writeBytes(query);
							
							in = connection.getInputStream();
							BufferedReader reader = new BufferedReader(new InputStreamReader(in));
							StringBuilder response = new StringBuilder();
							String line;
							while ((line = reader.readLine()) != null) {
								response.append(line);
							}
							result = response.toString();
							if (result.equals("")) {
								Toast.makeText(FirstActivity.this, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ", Toast.LENGTH_SHORT).show();
							} else {
								search_list = new ArrayList<Movie>();
								Login.Parse_Movies(result, 1);
							}
						} catch (Exception e) {
							e.printStackTrace();
							result = "";
						} finally {
							if (connection != null) {
								connection.disconnect();
							}
						}
					}
					
				}).start();
			}
			
		});
		
		
		l = (ListView)findViewById(R.id.home_lv_forum);
		if (search_list == null) {
			mAdapter = new movieAdapter(this, Login.movie_list);
		} else {
			mAdapter = new movieAdapter(this, search_list);
		}
		l.setAdapter(mAdapter);
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
			mListView.imageID.setImageResource(R.drawable.face);
			//∂ØÃ¨Õº∆¨µ˜”√
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

