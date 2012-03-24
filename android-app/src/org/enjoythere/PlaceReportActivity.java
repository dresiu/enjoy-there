package org.enjoythere;

import java.util.Arrays;
import java.util.Date;

import java.util.List;
import java.util.regex.Pattern;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceReportActivity extends Activity {

	Button sendBtn;
	TextView placeName;
	TextView placeDesc;
	RatingBar ratingBar;
	ImageView image;
	TextView occupancy;
	String [] occVals;

	String placeId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place_report);
		placeId = this.getIntent().getStringExtra("PLACE_NAME");
		

		placeName = (TextView) findViewById(R.id.report_placeName);
		placeDesc = (TextView) findViewById(R.id.report_placeDesc);
		ratingBar = (RatingBar) findViewById(R.id.report_placeratingBar);
		occupancy = (TextView) findViewById(R.id.report_occupancy);
		occVals = getResources().getStringArray(R.array.occupancy_array);
	    
		loadPlace();

		
	}



	private void loadPlace() {
		ParseQuery query = new ParseQuery("Place");
		query.whereEqualTo("name", placeId);
		try {
			List<ParseObject> places = query.find();
			placeName.setText(places.get(0).getString("name"));

		} catch (ParseException e) {
			Log.d("Place", "Error: " + e.getMessage());
		}
		
		query = new ParseQuery("UserPlace");
//		query.whereEqualTo("placeName", placeName.getText());
		query.whereEqualTo("placeName", placeId);
		try {
			List<ParseObject> places = query.find();
			
			int  [] occupancyCount = new int[4];
			Arrays.fill(occupancyCount, 0);
			
			double ratingSum = 0;
			
			for(ParseObject obj : places){
				String itemOcc = obj.getString("currOccupancy");
				for(int i = 0; i < 4; i++){
					if(itemOcc.equals(occVals[i])){
						occupancyCount[i]++;
					}
				}
				
				ratingSum += obj.getDouble("myRating");
					
			}
			
			int maxInd = 0;
			int val = occupancyCount[0];
			
			for(int i = 0; i < 4; i++){
				if(occupancyCount[i] > val)
				{
					val = occupancyCount[i];
					maxInd = i;
				}
				
			}

			occupancy.setText("oblezenie: "+occVals[maxInd]);
			ratingBar.setRating((float)(ratingSum/places.size()));

		} catch (ParseException e) {
			Log.d("Place", "Error: " + e.getMessage());
		}
		
		

	}
	

}