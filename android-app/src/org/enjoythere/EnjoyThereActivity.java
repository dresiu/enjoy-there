package org.enjoythere;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.PushService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnjoyThereActivity extends Activity {

	TextView loginTW;
	Button loginBtn;
	Button scanBtn;
	Button searchBtn;
	Button myPlacesBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Parse.initialize(this, "bOBUxCViaQbtvA3hTsljZ501zfjEL5jXbefXPR2j", 
        		"Wmd7MO4Wa86GVN7c7LiZVDhGF575lc0ni13pttVE");
        
        PushService.subscribe(getApplicationContext(), "clubbing", EnjoyThereActivity.class);
        
//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();
        
        myPlacesBtn = (Button) findViewById(R.id.myPlacesBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginTW = (TextView) findViewById(R.id.loginTW);
        
        Preferences pref = Preferences.Instance();
        pref.Initialize(getApplicationContext());
        
        if (pref.GetLogin() != null) {
        	loginTW.setText(pref.GetLogin());
        } else {
        	startActivityForResult(new Intent(EnjoyThereActivity.this, LoginActivity.class), 3);
        }
        
        loginBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
//				Preferences pref = Preferences.Instance();
//		        pref.Initialize(getApplicationContext());
//				pref.SetCredentials(login.getText().toString(), "");
//				pref.Save();
//				startActivity(new Intent(EnjoyThereActivity.this, LoginActivity.class));
				startActivityForResult(new Intent(EnjoyThereActivity.this, LoginActivity.class), 3);
			}
		});
        
        myPlacesBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		startActivity(new Intent(EnjoyThereActivity.this, MyPlacesActivity.class));
        	}
        });
        
        searchBtn = (Button) findViewById(R.id.searchBtn);
        
        searchBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				startActivity(new Intent(EnjoyThereActivity.this, SearchActivity.class));
			}
		});
        
        scanBtn = (Button) findViewById(R.id.scanBtn);
        
        scanBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				startActivity(new Intent(EnjoyThereActivity.this, PlaceActivity.class));
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	
    	Preferences pref = Preferences.Instance();
        pref.Initialize(getApplicationContext());
        
        if (pref.GetLogin() != null) {
        	loginTW.setText(pref.GetLogin());
        }
    	
    	super.onActivityResult(requestCode, resultCode, data);
    }
}