package com.parse.starter;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


public class singleview extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.singleitemview);
	//	ImageView listimage=(ImageView) findViewById(R.id.icon);
	/*	TextView listname=(TextView) findViewById(R.id.names);
		TextView listage=(TextView) findViewById(R.id.ages);*/
//		singleimg.setParseFile(i.getParseFile("imagefield"));
		final TextView singlename=(TextView) findViewById(R.id.singlename);
		final TextView singleage=(TextView) findViewById(R.id.singleage);
		final ParseImageView singleimg=(ParseImageView) findViewById(R.id.singleimg);
		
		Intent i= getIntent();
		String name1=i.getStringExtra("id1");
		Log.d("obj id   : ", name1);
		ParseQuery po=new ParseQuery("TestObject");
		po.whereEqualTo("objectId", name1);
		po.findInBackground(new FindCallback<ParseObject>() {
		  @Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				 if (e == null) {
					 singlename.setText("Name : "+objects.get(0).getString("name"));
						singleage.setText("Age : "+objects.get(0).getNumber("age"));
						ParseFile image=objects.get(0).getParseFile("imagefield");
						singleimg.setParseFile(image);
						singleimg.loadInBackground();
						
			        } else {
			            Log.d("score", "Error: " + e.getMessage());
			        }
				
			}
		});
		//
		
		
		
	
		Button backbutton=(Button) findViewById(R.id.singlebtnback);
		backbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),Home.class);
				startActivity(i);
			}
		});
		
		
	}
}
