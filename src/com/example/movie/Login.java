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
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.movie.home.FirstActivity;

public class Login extends Activity {

	public static User user = null;
	public static ArrayList<Movie> movie_list = new ArrayList<Movie>();
	public static ArrayList<Cinema> cinema_list = new ArrayList<Cinema>();
	public final static String URL = "http://115.28.70.78/film";
	HttpURLConnection connection = null;
	DataOutputStream out;
	InputStream in;
	EditText acc, pw;
	Button login, register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        acc = (EditText)findViewById(R.id.account_login);
        pw = (EditText)findViewById(R.id.password_login);
        login = (Button)findViewById(R.id.login_button);
        register = (Button)findViewById(R.id.register_button);
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
							Log.w("cccc", "00000");
							if (result.equals("")) {
								Toast.makeText(Login.this, "锟剿号伙拷锟斤拷锟斤拷锟斤拷锟�", Toast.LENGTH_SHORT).show();
							} else {
								user = new User();
								Log.w("lll", result);
								Parse_User(result);
								LoadAllMovies();
								
								Intent intent = new Intent(Login.this, MainTabsActivity.class);
								startActivity(intent);
								finish();
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
					if (parser.getName().equals("SEX")) {
						user.setSex(parser.nextText());
					}
					if (parser.getName().equals("PHONE")) {
						user.setPhone(parser.nextText());
					}
					if (parser.getName().equals("E_ADD")) {
						user.setEmail_address(parser.nextText());
					}
					if (parser.getName().equals("ADD")) {
						user.setAddress(parser.nextText());
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
					Log.w("moviessss", result);
					Parse_Movies(result);
					LoadAllCinema();
					
					/*Intent intent = new Intent(Login.this, MainTabsActivity.class);
					startActivity(intent);
					finish();*/
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
    
    private void Parse_Movies(String xml) {
    	String title = "", comment = "", I_ID = "";
		int ID = 0;
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
						I_ID = "";
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
						I_ID = parser.nextText();
					    movie_list.add(new Movie(title, comment, ID, I_ID));
						Log.w("title", title);
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

	private void LoadAllCinema() {
		cinema_list.clear();
		final String url = URL + "/querycinema";
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
					out.writeBytes("getAllCinemas");

					in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					result = response.toString();
					//Log.w("hehehe", result);
					Parse_Cinema(result);
//
//
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
    
    private void Parse_Cinema(String xml) {
		String title = "", address = "";
		int price = 0;
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(xml));
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
					case XmlPullParser.START_TAG:
						if (parser.getName().equals("Cinema")) {
							title = "";
							address = "";
							price = 0;
						}
						if (parser.getName().equals("NAME")) {
							title = parser.nextText();
						}
						if (parser.getName().equals("ADDRESS")) {
							address = parser.nextText();
						}
						if (parser.getName().equals("PRICE")) {
							price = Integer.parseInt(parser.nextText());
							cinema_list.add(new Cinema(title, address, price));
							Log.w("title", title);
						}
					case XmlPullParser.END_TAG:
						if (parser.getName().equals("Cinema")) {
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
