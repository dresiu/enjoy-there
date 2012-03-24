package org.enjoythere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EnjoyThereActivity extends Activity {

	Button scanBtn;
	Button searchBtn;
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

		scanBtn = (Button) findViewById(R.id.scanBtn);
		scanBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				startActivity(new Intent(EnjoyThereActivity.this, ScanActivity.class));
			}
		});

	}
}