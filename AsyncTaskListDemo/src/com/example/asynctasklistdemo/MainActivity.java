package com.example.asynctasklistdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String[] names = { "Eunice", "Ayanna", "Fe", "Denisse", "Babette", "Bettyann", "Eleanore", "Julienne",
			"Arlean", "Malisa" };
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(
				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
		new MyTask().execute();
	}

	class MyTask extends AsyncTask<Void, String, String> {

		ArrayAdapter<String> adapter;
		ProgressBar progressBar;
		int count;

		@Override
		protected void onPreExecute() {
			adapter = (ArrayAdapter<String>) listView.getAdapter();
			progressBar = (ProgressBar) findViewById(R.id.progress_bar);
			progressBar.setMax(10);
			progressBar.setProgress(0);
			progressBar.setVisibility(View.VISIBLE);
			count = 0;
		}

		@Override
		protected String doInBackground(Void... params) {
			for (String Name : names) {
				publishProgress(Name);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return "All the names were added successfully.";
		}

		@Override
		protected void onProgressUpdate(String... values) {
			adapter.add(values[0]);
			count++;
			progressBar.setProgress(count);
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			progressBar.setVisibility(View.GONE);
		}

	}

}
