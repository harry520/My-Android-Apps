package com.example.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyService extends IntentService {

	public MyService() {
		super("myService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d("IntentService", "Intent Service executed(onHandleIntent)");

	}

}
