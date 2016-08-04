package com.example.countdown;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button btnStart, btnStop;
	TextView textViewTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStop = (Button) findViewById(R.id.btnStop);
		textViewTime = (TextView) findViewById(R.id.textViewTime);
		textViewTime.setText("00:03:00");
		final CounterClass timer = new CounterClass(180000, 1000);
		btnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				timer.start();
			}

		});
		btnStop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				timer.cancel();
			}

		});
	}

	public class CounterClass extends CountDownTimer {

		public CounterClass(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onTick(long millisUntilFinished) {
			long millis = millisUntilFinished;
			String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
					TimeUnit.MILLISECONDS.toMinutes(millis)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
					TimeUnit.MILLISECONDS.toSeconds(millis)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MICROSECONDS.toMinutes(millis)));
			System.out.println(hms);
			textViewTime.setText(hms);
		}

		@Override
		public void onFinish() {
			textViewTime.setText("Completed.");
		}

	}

}
