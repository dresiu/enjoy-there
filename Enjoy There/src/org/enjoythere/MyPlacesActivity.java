package org.enjoythere;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MyPlacesActivity extends ListActivity {
	
	private ArrayAdapter<String> myPlaces;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // TODO pobraæ miejsca z parse.com i wyœwietliæ
        
        myPlaces = new ArrayAdapter<String>(getApplicationContext(),
        		R.layout.list_item);
        setListAdapter(myPlaces);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

//    	super.onListItemClick(l, v, position, id);
    	
    	
    }
}