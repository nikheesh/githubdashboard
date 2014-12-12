package com.parse.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class Home extends Activity{
	
	private ParseQueryAdapter<ParseObject> mainAdapter;
	private CustomParsequeryadapter adapterclass;
	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.main);
		// Execute RemoteDataTask AsyncTask
/*		mainAdapter = new ParseQueryAdapter<ParseObject>(this, "TestObject");
		
		mainAdapter.setImageKey("imagefield");
		mainAdapter.setTextKey("name");
		mainAdapter.setTextKey("age");     */

		// Initialize the subclass of ParseQueryAdapter
		adapterclass = new CustomParsequeryadapter(this);

		// Initialize ListView and set initial view to mainAdapter
		listView = (ListView) findViewById(R.id.listview);
	//	listView.setAdapter(mainAdapter);
	//	mainAdapter.loadObjects();
		listView.setAdapter(adapterclass);
		adapterclass.loadObjects();
		adapterclass.setObjectsPerPage(8);
		listView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
				ParseQueryAdapter<ParseObject> parseobject=  adapterclass;
				String id1=parseobject.getItem(position).getObjectId(); 
				
				Intent i=new Intent(getApplicationContext(),singleview.class);
				i.putExtra("id1", id1);
				//Log.d("home   id  :",id1);
				
				startActivity(i);
			}
		});
		// Initialize toggle button
	/*	Button toggleButton = (Button) findViewById(R.id.toggleButton);
		toggleButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listView.getAdapter() == mainAdapter) {
					listView.setAdapter(urgentTodosAdapter);
					urgentTodosAdapter.loadObjects();
				}
				
				else {
					listView.setAdapter(mainAdapter);
					mainAdapter.loadObjects();
				} 
			}

		});*/
	//	new RemoteDataTask().execute();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu1, menu);
        return true;
	}
 @Override
 public boolean onOptionsItemSelected(MenuItem item){
	 switch(item.getItemId())
	 {case R.id.create : 
		  Intent i = new Intent(getApplicationContext(),LogIn.class);
		  startActivity(i);
		  return true;
	 case R.id.back:
		 Toast.makeText(Home.this,"Nothing to Display",1000).show();
         return true;

	 }
	return false;
	 
 }
	// RemoteDataTask AsyncTask
/*	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(Listview1.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Loading Names and images.....!");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}
 
		@Override
		protected Void doInBackground(Void... params) {
			// Create the array
			imagetext = new ArrayList<ImageText>();
			try {
				// Locate the class table named "Country" in Parse.com
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"TestObject");
				// Locate the column named "ranknum" in Parse.com and order list
				// by ascending
				query.orderByAscending("name");
				//query.setLimit(4);
				ob = query.find();
				for (ParseObject country : ob) {
					// Locate images in flag column
					ParseFile imageHolder = (ParseFile) country.get("imagefield");
					byte[] file = imageHolder.getData();
		             Bitmap image = BitmapFactory.decodeByteArray(file,0,file.length);
		            
		            
		             File file1 = new File(Environment.getExternalStorageDirectory()+"/",country.get("name")+".jpeg");
		             file1.mkdir();
		             Log.e("filepath",""+file1.getAbsolutePath());
		             if(file1.exists()){
		            	 file1.delete();
		             }
		             OutputStream stream = new FileOutputStream(file1.getAbsolutePath());
		             image.compress(CompressFormat.JPEG, 100, stream);
		             
					ImageText map = new ImageText();
					
					
					map.setNames((String) country.get("name"));
					
					map.setImg(file1.getAbsolutePath());
					imagetext.add(map);
				}
			} catch (ParseException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
 
		@Override
		protected void onPostExecute(Void result) {
			// Locate the listview in listview_main.xml
			listview = (ListView) findViewById(R.id.listview);
			// Pass the results into ListViewAdapter.java
			adapter = new ListViewAdapter(Listview1.this,imagetext);
			// Binds the Adapter to the ListView
			listview.setAdapter(adapter);
			// Close the progressdialog
			mProgressDialog.dismiss();
		}
	}*/
}