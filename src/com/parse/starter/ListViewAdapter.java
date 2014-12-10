package com.parse.starter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ListViewAdapter extends BaseAdapter {
	 
	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ImageLoader imageLoader;
	private List<ImageText> imagetext = null;
	private ArrayList<ImageText> arraylist;
 
	public ListViewAdapter(Context context,
			List<ImageText> imagetext) {
		this.context = context;
		this.imagetext = imagetext;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<ImageText>();
		this.arraylist.addAll(imagetext);
		imageLoader = new ImageLoader(context);
	}
 
	public class ViewHolder {
		TextView names;
		
		ImageView img;
	}
 
	@Override
	public int getCount() {
		return imagetext.size();
	}
 
	@Override
	public Object getItem(int position) {
		return imagetext.get(position);
	}
 
	@Override
	public long getItemId(int position) {
		return position;
	}
 
	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listviewrow, null);
			// Locate the TextViews in listview_item.xml
			holder.names = (TextView) view.findViewById(R.id.names);
		//	holder.country = (TextView) view.findViewById(R.id.country);
			//holder.population = (TextView) view.findViewById(R.id.population);
			// Locate the ImageView in listview_item.xml
			holder.img = (ImageView) view.findViewById(R.id.img1);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.names.setText(imagetext.get(position).getNames());
	//	holder.country.setText(worldpopulationlist.get(position).getCountry());
	//	holder.population.setText(worldpopulationlist.get(position)
			//	.getPopulation());
		// Set the results into ImageView
		//imageLoader.DisplayImage(imagetext.get(position).getImg(),holder.img);
		Bitmap bm = BitmapFactory.decodeFile(imagetext.get(position).getImg());
		Log.e("bitmap","::"+bm);
		Log.e("bitmap url","::"+imagetext.get(position).getImg());
		holder.img.setImageBitmap(bm);
		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
				Intent intent = new Intent(context, SingleItemView.class);
				// Pass all data rank
				intent.putExtra("names",
						(imagetext.get(position).getNames()));
				// Pass all data country
				//intent.putExtra("country",
					//	(worldpopulationlist.get(position).getCountry()));
				// Pass all data population
				//intent.putExtra("population",
				//		(worldpopulationlist.get(position).getPopulation()));
				// Pass all data flag
				intent.putExtra("img",
						(imagetext.get(position).getImg()));
				// Start SingleItemView Class
				context.startActivity(intent);
			}
		});
		return view;
	}
 
}