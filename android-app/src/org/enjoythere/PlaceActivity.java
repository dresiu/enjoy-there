package org.enjoythere;

import java.util.Date;

import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;



public class PlaceActivity extends Activity {

	Button sendBtn;
	EditText placeName;
	EditText placeDesc;
	RatingBar ratingBar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place);
        
        boolean newPlace = true;
		if (newPlace) {
        	Intent intent = new Intent("com.google.zxing.client.android.SCAN");
    		intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE",
    				"QR_CODE_MODE");
    		startActivityForResult(intent, 0);
        }
        
        placeName = (EditText) findViewById(R.id.placeName);
        placeDesc = (EditText) findViewById(R.id.placeDesc);
        ratingBar = (RatingBar) findViewById(R.id.placeratingBar);
        
        sendBtn = (Button) findViewById(R.id.placeSendBtn);
        
        sendBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				if (placeName.getText() != null &&
						placeDesc.getText() != null) {
					
					Preferences pref = Preferences.Instance();
			        pref.Initialize(getApplicationContext());
					
			        ParseObject testObject = new ParseObject("UserPlace");
			        testObject.put("user", pref.GetLogin());
			        testObject.put("placeName", placeName.getText().toString());
			        testObject.put("placeDesc", placeDesc.getText().toString());
			        testObject.put("placeVisitDate", new Date());
			        
			        testObject.saveInBackground();
				} else {
					Toast.makeText(getApplicationContext(), 
							"Wype³nij wszystkie dane!", Toast.LENGTH_SHORT);
				}
			}
		});
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				Log.d("Reader", contents);
				
				

			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}
}