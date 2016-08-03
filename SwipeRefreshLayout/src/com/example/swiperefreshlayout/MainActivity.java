package com.example.swiperefreshlayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);
		final TextView rndNum = (TextView) findViewById(R.id.lb1);
		swipeView.setColorScheme(android.R.color.holo_blue_dark, android.R.color.holo_blue_bright,
				android.R.color.holo_green_light, android.R.color.holo_green_dark);
		swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

			@Override
			public void onRefresh() {
				swipeView.setRefreshing(true);
				Log.d("Swipe", "Refreshing Number");
				(new Handler()).postDelayed(new Runnable() {

					@Override
					public void run() {
						swipeView.setRefreshing(false);
						double f = Math.random();
						rndNum.setText(String.valueOf(f));
					}
				}, 3000);
			}
		});
	}

}
