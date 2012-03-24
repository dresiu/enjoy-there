package org.enjoythere;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PlaceActivity extends Activity {

	Button sendBtn;
	RatingBar ratingBar;
	Button myPlacesBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        myPlacesBtn = (Button) findViewById(R.id.myPlacesBtn);
        
        myPlacesBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				
			}
		});
    }
}