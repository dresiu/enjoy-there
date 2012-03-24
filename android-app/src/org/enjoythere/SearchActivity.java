package org.enjoythere;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends Activity {

	EditText name;
	EditText city;
	Button loginBtn;
	Button searchBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        
        Log.d("search", "Search started.");
        
        name = (EditText) findViewById(R.id.searchPlaceName);
        city = (EditText) findViewById(R.id.searchPlaceCity);
        
        searchBtn = (Button) findViewById(R.id.searchBtn);
        
        searchBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				ParseQuery query = new ParseQuery("Place");
				
				if (name.getText().toString() != null && 
						name.getText().toString().trim() != "") {
						
					query.whereContains("name", name.getText().toString());
				}
				
				if (city.getText().toString() != null && 
						city.getText().toString().trim() != "") {
						
					query.whereContains("city", city.getText().toString());
				}
		        
		        query.findInBackground(new FindCallback() {
		            public void done(List<ParseObject> placesList, ParseException e) {
		            	Log.d("search", "found: " + placesList.size());
		                if (e == null) {
		                	ArrayList<String> placesId = new ArrayList<String>();
		                	ArrayList<String> placesNames = new ArrayList<String>();
		                    for(ParseObject place : placesList) {
	                    		Log.d("search", place.getObjectId() + " " + 
	                    				place.getString("name") + " " + 
	                    				place.getString("city"));
	                    		placesId.add(place.getObjectId());
	                    		placesNames.add(place.getString("name"));
		                    }
		                    
		                    Intent searchResults = new Intent(SearchActivity.this, MyPlacesActivity.class);
		                    
		                    searchResults.putStringArrayListExtra("placesId", placesId);
		                    searchResults.putStringArrayListExtra("placesNames", placesNames);
		                    
		                    startActivity(searchResults);
		                } else {
		                    Log.d("score", "Error: " + e.getMessage());
		                }
		                
		            }
		        });
			}
		});
    }
}