package org.enjoythere;

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

public class PlaceActivity extends Activity {

	Button sendBtn;
	TextView placeName;
	EditText placeDesc;
	RatingBar ratingBar;
	ImageView image;
	Spinner occupancy;

	String currentId;

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

		placeName = (TextView) findViewById(R.id.placeName);
		placeDesc = (EditText) findViewById(R.id.placeDesc);
		ratingBar = (RatingBar) findViewById(R.id.placeratingBar);
		image = (ImageView) findViewById(R.id.imageView1);

		sendBtn = (Button) findViewById(R.id.placeSendBtn);

		occupancy = (Spinner) findViewById(R.id.spinner);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.occupancy_array, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    occupancy.setAdapter(adapter);

	    
		image.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				startActivityForResult(new Intent(Intent.ACTION_PICK,
				         android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), 2);

				//startActivityForResult(intent, 2);

			}
		});

		sendBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				addUserPlace();
				
			}
		});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK && requestCode == 0) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				Log.d("Reader", contents);
				// Handle successful scan
				currentId = contents.substring(3);

				loadPlace();


			} else if (resultCode == RESULT_CANCELED && requestCode == 0) {
				this.finish();
			}

			if (resultCode == Activity.RESULT_OK && requestCode == 2) {
				Uri uri = intent.getData();
				image.setImageURI(uri);
			}
		}
	}

	private void loadPlace() {
		ParseQuery query = new ParseQuery("Place");
		query.whereEqualTo("objectId", currentId);
		try {
			List<ParseObject> places = query.find();
			placeName.setText(places.get(0).getString("name"));
			placeDesc.setText(places.get(0).getString("placeDesc"));

		} catch (ParseException e) {
			Log.d("Place", "Error: " + e.getMessage());
		}

	}
	
	private void addUserPlace(){
		if (placeName.getText() != null && placeDesc.getText() != null) {

			Preferences pref = Preferences.Instance();
	        pref.Initialize(getApplicationContext());
			
	        ParseObject testObject = new ParseObject("UserPlace");
	        testObject.put("user", pref.GetLogin());
	        testObject.put("placeVisitDate", new Date());
			testObject.put("placeName", placeName.getText().toString());
			testObject.put("placeDesc", placeDesc.getText().toString());
			testObject.put("myRating", ratingBar.getRating());
			testObject.put("currOccupancy", occupancy.getSelectedItem().toString());
			testObject.saveInBackground();
			PlaceActivity.this.finish();

		} else {
			Toast.makeText(getApplicationContext(),
					"Wype³nij wszystkie dane!", Toast.LENGTH_SHORT);
		}
		
	}
}