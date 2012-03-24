package org.enjoythere;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EnjoyThereActivity extends Activity {

	EditText login;
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
        
//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();
        
        myPlacesBtn = (Button) findViewById(R.id.myPlacesBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        login = (EditText) findViewById(R.id.login);
        
        Preferences pref = Preferences.Instance();
        pref.Initialize(getApplicationContext());
        
        if (pref.GetLogin() != null) {
        	login.setText(pref.GetLogin());
        }
        
        loginBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Preferences pref = Preferences.Instance();
		        pref.Initialize(getApplicationContext());
				pref.SetCredentials(login.getText().toString(), "");
				pref.Save();
			}
		});
        
        myPlacesBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        	}
        	
        });
        
        scanBtn = (Button) findViewById(R.id.scanBtn);
        
        scanBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				startActivity(new Intent(EnjoyThereActivity.this, PlaceActivity.class));
			}
		});
    }
}