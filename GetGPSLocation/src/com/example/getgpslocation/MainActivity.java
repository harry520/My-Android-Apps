package com.example.getgpslocation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnShowLocation;
	GPSTracker gps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnShowLocation = (Button) findViewById(R.id.show_location);
		btnShowLocation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				gps = new GPSTracker(MainActivity.this);
				if (gps.canGetLocation()) {
					double latitude = gps.getLatitude();
					double longitude = gps.getLongitude();
					Toast.makeText(getApplicationContext(),
							"Your location is -\nLat:" + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
				} else {
					gps.showSettingsAlert();
				}
			}
		});
	}

}
