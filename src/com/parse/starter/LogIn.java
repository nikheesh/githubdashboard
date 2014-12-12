package com.parse.starter;

import com.parse.*;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class LogIn extends Activity {
	
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
	        setContentView(R.layout.login);
	        final Button log=(Button) findViewById(R.id.loginbtn);
	        log.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View view) {	
					startLoading();
	        EditText us=(EditText) findViewById(R.id.usetext);
	        EditText pass=(EditText) findViewById(R.id.passtext);
	        ParseUser.logInInBackground(us.getText().toString(), pass.getText().toString(), new LogInCallback() {
	        	  public void done(ParseUser user, ParseException e) {
	        		  stopLoading();
	        	    if (user != null) {
	        	    	Intent i = new Intent(getApplicationContext(),AddNewItem.class);
	        			  startActivity(i);
	        	    } else {
	        	    	Toast.makeText(getApplicationContext(), "User name or password incorrect...!",10).show();
	        	    }
	        	  }
	        	});
				}});
	        
	        final TextView newuser=(TextView)findViewById(R.id.signup1);
	        newuser.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View view) {	
					newuser.setEnabled(false);
					log.setEnabled(false);
					Intent i = new Intent(getApplicationContext(),SignUp.class);
      			  startActivity(i);
					
				}});
				}
					
	        
	  }


