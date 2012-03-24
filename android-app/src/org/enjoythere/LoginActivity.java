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

public class LoginActivity extends Activity {

	EditText login;
	Button loginBtn;
	Button scanBtn;
	Button searchBtn;
	Button myPlacesBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        loginBtn = (Button) findViewById(R.id.loginBtn);
        login = (EditText) findViewById(R.id.login);
        
        Preferences pref = Preferences.Instance();
        pref.Initialize(getApplicationContext());
        
        if (pref.GetLogin() != null) {
        	login.setText(pref.GetLogin());
        }
        
        loginBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (login.getText().toString() != null &&
						login.getText().toString().trim() != "" && 
						login.getText().toString().length() > 0) {
					Preferences pref = Preferences.Instance();
			        pref.Initialize(getApplicationContext());
					pref.SetCredentials(login.getText().toString(), "");
					pref.Save();
					
					LoginActivity.this.finish();
				}
			}
		});
    }
}