package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
 
public class SingleItemView extends Activity {
	// Declare Variables
	String names;
	//String country;
	//String population;
	String img;
	String position;
	ImageLoader imageLoader = new ImageLoader(this);
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.listviewrow);
 
		Intent i = getIntent();
		// Get the result of rank
		names = i.getStringExtra("names");
		// Get the result of country
		//country = i.getStringExtra("country");
		// Get the result of population
	//	population = i.getStringExtra("population");
		// Get the result of flag
		img = i.getStringExtra("img");
 
		// Locate the TextViews in singleitemview.xml
		TextView txtrank = (TextView) findViewById(R.id.names);
		//TextView txtcountry = (TextView) findViewById(R.id.country);
		//TextView txtpopulation = (TextView) findViewById(R.id.population);
 
		// Locate the ImageView in singleitemview.xml
		ImageView imgflag = (ImageView) findViewById(R.id.img1);
 
		// Set results to the TextViews
		txtrank.setText(names);
		//txtcountry.setText(country);
		//txtpopulation.setText(population);
 
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		imageLoader.DisplayImage(img, imgflag);
	}
}
