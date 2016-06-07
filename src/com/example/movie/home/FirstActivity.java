package com.example.movie.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.example.movie.TextViewMultilineEllipse;

import android.app.Activity;
import android.os.Bundle;



public class FirstActivity extends Activity {
	
	
	private List<Movie> movieList = new ArrayList<Movie>();
	private ListView l;
	private movieAdapter mAdapter;
	
	//private RelativeLayout relativeLayout;
	//private TextViewMultilineEllipse textViewM;

	
	/*public static List<Movie> search_list = null;
	
	EditText s_in;
	Button search;
	HttpURLConnection connection = null;
	DataOutputStream out;
	InputStream in;*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		 //  多行显示文本-------------------------------------------------------------------------------------------
        /*LayoutParams lp=new LayoutParams(LayoutParams.FILL_PARENT,100);
        
        relativeLayout = (RelativeLayout)findViewById(R.id.listview_layout);
        textViewM = new TextViewMultilineEllipse(this);
        textViewM.setLayoutParams(lp);//限制TextView的宽高  
        textViewM.setEllipsis("...");//...替换剩余字符串  
        textViewM.setMaxLines(5);
        textViewM.setTextSize(10);//设置文字大小  
        textViewM.setTextColor(Color.BLACK);
        textViewM.setText("哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        relativeLayout.addView(textViewM); */
        //  多行显示文本-------------------------------------------------------------------------------------------
		
		/*s_in = (EditText)findViewById(R.id.search_in);
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
							Log.w("aaaaaa", result);
							if (result.equals("")) {
								//Toast.makeText(FirstActivity.this, "未搜索到此电影", Toast.LENGTH_SHORT).show();
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
			
		});*/
		
		
		l = (ListView)findViewById(R.id.home_lv_forum);
		if (Login.movie_list.size() < 1) {
			Log.w("fail", "movie");
		}
	    mAdapter = new movieAdapter(this, Login.movie_list);
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
				mListView.title = (TextView)convertView.findViewById(R.id.listview_title);
				mListView.content = (TextView)convertView.findViewById(R.id.listview_content);
				mListView.imageID = (ImageView)convertView.findViewById(R.id.listview_image);
				convertView.setTag(mListView);
			} else {
				mListView = (movieListView)convertView.getTag();
			}
			mListView.title.setText((String)movieList.get(position).getTitle());
			
			//---------------------------------------------------------------------------
			/*ViewTreeObserver observer = mListView.title.getViewTreeObserver();
			observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

				@Override
				public void onGlobalLayout() {
					// TODO Auto-generated method stub
					ViewTreeObserver obs = mListView.title.getViewTreeObserver();
					obs.removeGlobalOnLayoutListener(this);
					if (mListView.title.getLineCount() > 1) {
						int lineEndIndex = mListView.title.getLayout().getLineEnd(1);
						String text = mListView.title.getText().subSequence(0, lineEndIndex - 3) + "...";
						mListView.title.setText(text);
					}
				}
				
			});*/
			//---------------------------------------------------------------------------
			
			mListView.content.setText((String)movieList.get(position).getComment());
			String im = (String)movieList.get(position).getImageID();
			Context ctx=getBaseContext();
			int id = getResources().getIdentifier(im, "drawable", ctx.getPackageName());
			mListView.imageID.setImageDrawable(getResources().getDrawable(id));
			//Bitmap bm = BitmapFactory.decodeFile(file);
			//mListView.imageID.setImageBitmap(bm);
			//动态图片调用
			l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(FirstActivity.this, ContentActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("MID", String.valueOf(movieList.get(position).getID()));
					intent.putExtras(bundle);
					startActivity(intent);
				}
				
			});
			return convertView;
		}
		
	}
	
}

