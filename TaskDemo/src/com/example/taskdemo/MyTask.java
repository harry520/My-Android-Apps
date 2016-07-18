package com.example.taskdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

public class MyTask extends AsyncTask<Void, Integer, String> {
	Context context;
	TextView textView;
	Button button;
	ProgressDialog progressDialog;

	MyTask(Context context, TextView textView, Button button) {
		this.context = context;
		this.textView = textView;
		this.button = button;
	}

	@Override
	protected String doInBackground(Void... params) {
		int i = 0;
		synchronized (this) {
			while (i < 10) {
				try {
					wait(1500);
					i++;
					publishProgress(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return "Download Complete...";
	}

	protected void onPreExecture() {
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Download in Progress...");
		progressDialog.setMax(10);
		progressDialog.setProgress(0);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.show();
	}

	protected void onPostExecture(String result) {
		textView.setText(result);
		button.setEnabled(true);
		progressDialog.hide();
	}

	protected void onProgressUpdate(Integer... values) {
		int progress = values[0];
		progressDialog.setProgress(progress);
		textView.setText("Download in Progress...");
	}

}
