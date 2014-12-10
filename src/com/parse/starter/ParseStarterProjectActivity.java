package com.parse.starter;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class ParseStarterProjectActivity extends Activity {
	private final int SELECT_PHOTO = 1;
	private ImageView imageView;
	byte[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        imageView = (ImageView)findViewById(R.id.imageView);
        
        

        Button pickImage = (Button) findViewById(R.id.btn_pick);
        pickImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {				
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);
			}
		});
        
        Button savedata = (Button) findViewById(R.id.button1);
        
       savedata.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {	
				
		
				 final EditText etext = (EditText) findViewById(R.id.editText1);
			/*	if(etext.getText().toString()==""){
					Toast.makeText(getApplicationContext(), "Enter Your Name...!",10).show();
				}
				else{*/
				 Log.e("array",array+"");
				ParseFile file = new ParseFile("img1.png", array);
				ParseObject testobj = new ParseObject("TestObject");
				testobj.put("name",etext.getText().toString());
				testobj.put("imagefield", file);
				testobj.saveInBackground(new SaveCallback() {
					  @Override
					public void done(com.parse.ParseException e) {
						  Toast.makeText(getApplicationContext(), "Successfully uploaded..!",10).show();
						  Intent i = new Intent(getApplicationContext(), Listview1.class);
						  startActivity(i);
						  
						  
						  
						 /* imageView.setImageResource(R.drawable.defimg);
							etext.setText(""); */ 
						  
						
					}});			
				
			//	}
			}
		});
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

        switch(requestCode) { 
        case SELECT_PHOTO:
            if(resultCode == RESULT_OK){
				try {
					final Uri imageUri = imageReturnedIntent.getData();
				
					final InputStream imageStream = getContentResolver().openInputStream(imageUri);
		Log.e("image",imageStream+"");
					final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
				/*	int b=selectedImage.getByteCount();
					ByteBuffer buffer = ByteBuffer.allocate(b); //Create a new buffer
					selectedImage.copyPixelsToBuffer(buffer); //Move the byte data to the buffer
*/
					
					
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					selectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
					//byte[] byteArray 
					array= stream.toByteArray();
					// array = buffer.array();
					
					imageView.setImageBitmap(selectedImage);
			
				
					
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

            }
       
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }    
    
}