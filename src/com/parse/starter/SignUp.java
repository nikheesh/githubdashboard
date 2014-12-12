package com.parse.starter;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class SignUp extends Activity{ 
	protected ProgressDialog proDialog;
	protected void startLoading() {
	    proDialog = new ProgressDialog(this);
	    proDialog.setMessage(" Please Wait.....");
	    proDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    proDialog.setCancelable(false);
	    proDialog.show();
	}

	protected void stopLoading() {
	    proDialog.dismiss();
	    proDialog = null;
	}
	
	protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.signup);
    final Button log=(Button) findViewById(R.id.signupbtn);
    log.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View view) {	
			//log.setEnabled(false);
			startLoading();
    EditText us=(EditText) findViewById(R.id.usetext1);
    EditText pass=(EditText) findViewById(R.id.passtext1);
    EditText passc=(EditText) findViewById(R.id.passtextc);
    if(pass.getText().toString().equals(passc.getText().toString())){
    	
    	ParseUser user = new ParseUser();
    	if(us.getText().toString().equals("") || pass.getText().toString().equals("")){
    		stopLoading();
    		Toast.makeText(getApplicationContext(), "Username or Password cannot be Empty..",100).show();
        	
    	}
    	else{log.setEnabled(false);
    		startLoading();
    	user.setUsername(us.getText().toString());
    	user.setPassword(pass.getText().toString());
    	
    	//user.put("phone", "650-253-0000");
    	 
    	user.signUpInBackground(new SignUpCallback() {
    	  public void done(ParseException e) {
    		  stopLoading();
    	    if (e == null) {
    	    	Toast.makeText(getApplicationContext(), "Successfully created...!",100).show();
	        	
    	    	Intent i = new Intent(getApplicationContext(),AddNewItem.class);
  			  startActivity(i);
    	      // Hooray! Let them use the app now.
    	    } else {
    	    	Toast.makeText(getApplicationContext(), "Not completed your request..!",100).show();
	        	
    	      // Sign up didn't succeed. Look at the ParseException
    	      // to figure out what went wrong
    	    }
    	  }
    	});}
    }
    
 else {
	 Toast.makeText(getApplicationContext(), "Confirm Password Correctly...!",100).show();
 	
    }
		}});
   
    
  
		}
	

}
