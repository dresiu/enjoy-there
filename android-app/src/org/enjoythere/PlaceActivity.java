package org.enjoythere;

import com.parse.ParseObject;

import android.app.Activity;
import android.os.Bundle;
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
        
        placeName = (EditText) findViewById(R.id.placeName);
        placeDesc = (EditText) findViewById(R.id.placeDesc);
        ratingBar = (RatingBar) findViewById(R.id.placeratingBar);
        
        sendBtn = (Button) findViewById(R.id.placeSendBtn);
        
        sendBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				if (placeName.getText() != null &&
						placeDesc.getText() != null) {
					
					ParseObject testObject = new ParseObject("Place");
			        testObject.put("placeName", placeName.getText().toString());
			        testObject.put("placeDesc", placeDesc.getText().toString());
			        testObject.saveInBackground();
				} else {
					Toast.makeText(getApplicationContext(), 
							"Wype³nij wszystkie dane!", Toast.LENGTH_SHORT);
				}
			}
		});
    }
}