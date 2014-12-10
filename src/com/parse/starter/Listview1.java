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
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Listview1 extends Activity{
	
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewAdapter adapter;
	private List<ImageText> imagetext = null;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.listviewmain);
		// Execute RemoteDataTask AsyncTask
		new RemoteDataTask().execute();
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
		  Intent i = new Intent(getApplicationContext(),ParseStarterProjectActivity.class);
		  startActivity(i);
		  return true;
	 case R.id.back:
		 Toast.makeText(Listview1.this,"Nothing to Display",1000).show();
         return true;

	 }
	return false;
	 
 }
	// RemoteDataTask AsyncTask
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
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
	}
}