package org.enjoythere;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MyPlacesActivity extends ListActivity {
	
	private ArrayAdapter<String> myPlaces;
	private static List<ParseObject> places;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ArrayList<String> placesId = getIntent().getStringArrayListExtra("placesId");
        ArrayList<String> placesNames = getIntent().getStringArrayListExtra("placesNames");
        if (placesNames != null) {
        	Log.i("myPlaces", "searchresult " + placesNames.size());
        	
        	places = new ArrayList<ParseObject>();
        	myPlaces = new ArrayAdapter<String>(getApplicationContext(),
	        		R.layout.list_item);
        	
        	for (int i = 0; i < placesId.size(); i++) {
        		ParseObject temp = new ParseObject("Place");
            	temp.setObjectId(placesId.get(i));
            	temp.put("name", placesNames.get(i));
            	places.add(temp);
            	myPlaces.add(placesNames.get(i));
        	}
        	
        	setListAdapter(myPlaces);
        } else {
        
	        Preferences pref = Preferences.Instance();
	        pref.Initialize(getApplicationContext());
	        
	        myPlaces = new ArrayAdapter<String>(getApplicationContext(),
	        		R.layout.list_item);
	        
	        ParseQuery query = new ParseQuery("UserPlace");
	        query.whereEqualTo("user", pref.GetLogin());
	        query.findInBackground(new FindCallback() {
	            public void done(List<ParseObject> placesList, ParseException e) {
	            	myPlaces = new ArrayAdapter<String>(getApplicationContext(),
	                		R.layout.list_item);
	            	
	                if (e == null) {
	                	places = placesList;
	                    for(ParseObject place : places) {
	                    	if (place.getDate("placeVisitDate") != null) {
	                    		myPlaces.add(place.getString("placeName") + " " + 
	                    				place.getDate("placeVisitDate").toLocaleString());
	                    	} else {
	                    		myPlaces.add(place.getString("placeName"));
	                    	}
	                    }
	                } else {
	                    Log.d("score", "Error: " + e.getMessage());
	                }
	                
	                setListAdapter(myPlaces);
	            }
	        });
        }
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	if (places != null) {
    		
    	} else {
    		
    	}
    }
}