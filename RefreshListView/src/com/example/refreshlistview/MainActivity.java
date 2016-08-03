package com.example.refreshlistview;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity {

	private ListView numberList;
	MyThumbnailAdapter thadapter = null;
	ArrayList<String> numAl = new ArrayList<String>();
	int number_count = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		numberList = (ListView) findViewById(R.id.listView);
		for (int i = number_count; i < 20; i++)
			numAl.add(i + " ");
		thadapter = new MyThumbnailAdapter(MainActivity.this, R.layout.list_row, numAl);
		numberList.setAdapter(thadapter);
	}

	public class MyThumbnailAdapter extends ArrayAdapter<String> {

		ArrayList<String> arr;
		private TextView text;

		public MyThumbnailAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
			super(context, textViewResourceId, objects);
			this.arr = objects;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = null;
			LayoutInflater inflater = getLayoutInflater();
			view = inflater.inflate(R.layout.list_row, parent, false);
			TextView textnumber = (TextView) view.findViewById(R.id.text);
			Button delButton = (Button) view.findViewById(R.id.btn_del);
			delButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					arr.remove(position);
					thadapter.notifyDataSetChanged();
					Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
				}

			});
			textnumber.setText("Position: " + arr.get(position));
			return view;
		}
	}
}
