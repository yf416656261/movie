package com.example.movie;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movie.home.FirstActivity;

public class Login extends Activity {

	public static User user = null;
	public static ArrayList<Movie> movie_list = new ArrayList<Movie>();
	public final static String URL = "http://115.28.70.78";
	HttpURLConnection connection = null;
	DataOutputStream out;
	InputStream in;
	EditText acc = (EditText)findViewById(R.id.account);
	EditText pw = (EditText)findViewById(R.id.password);
	Button login = (Button)findViewById(R.id.login_button);
	Button register = (Button)findViewById(R.id.register_button);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String account = acc.getText().toString();
				String password = pw.getText().toString();
				final String url = URL + "/login";
				final String query = "account=" + account + "&password=" + password;
				
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
								Toast.makeText(Login.this, "�˺Ż��������", Toast.LENGTH_SHORT).show();
							} else {
								user = new User();
								Parse_User(result);
								LoadAllMovies();
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
        
        register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Login.this, Register.class);
				startActivity(intent);
				finish();
			}
        });
    }
    
    private void Parse_User(String xml) {
    	try {
    		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(xml));
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("PASSWORD")) {
						user.setPassword(parser.nextText());
					}
					if (parser.getName().equals("NAME")) {
						user.setName(parser.nextText());
					}
					if (parser.getName().equals("P_ID")) {
						user.setPicture_id(Integer.parseInt(parser.nextText()));
					}
				case XmlPullParser.END_TAG:
					break;
				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void LoadAllMovies() {
    	movie_list.clear();
    	final String url = URL + "/querymovies";
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
					
					out  = new DataOutputStream(connection.getOutputStream());
					//out.writeBytes("mobileCode="+ phone_number.getText().toString() + "&userID=");
					out.writeBytes("getAllMovies");
					
					in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					result = response.toString();
					Parse_Movies(result, 0);
					
					
					Intent intent = new Intent(Login.this, MainTabsActivity.class);
					startActivity(intent);
					finish();
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
    
    public static void Parse_Movies(String xml, int type) {
    	String title = "", comment = "";
		int ID = 0, I_ID = 0;
    	try {
    		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(xml));
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("movies")) {
						title = "";
						comment = "";
						ID = 0;
						I_ID = 0;
					}
					if (parser.getName().equals("ID")) {
						ID = Integer.parseInt(parser.nextText());
					}
					if (parser.getName().equals("TITLE")) {
						title = parser.nextText();
					}
					if (parser.getName().equals("COMMENT")) {
						comment = parser.nextText();
					}
					if (parser.getName().equals("I_ID")) {
						I_ID = Integer.parseInt(parser.nextText());
						if (type == 0) {
							movie_list.add(new Movie(title, comment, ID, I_ID));
						} else {
							FirstActivity.search_list.add(new Movie(title, comment, ID, I_ID));
						}
					}
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("movies")) {
					}
					break;
				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
