package com.example.explistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

public class MainActivity extends ActionBarActivity {

	ExpandableListView expandableListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		expandableListView = (ExpandableListView) findViewById(R.id.exp_list_view);
		List<String> Headings = new ArrayList<String>();
		List<String> L1 = new ArrayList<String>();
		List<String> L2 = new ArrayList<String>();
		List<String> L3 = new ArrayList<String>();
		HashMap<String, List<String>> childList = new HashMap<String, List<String>>();
		String headingItems[] = getResources().getStringArray(R.array.header_titles);
		String l1[] = getResources().getStringArray(R.array.h1_items);
		String l2[] = getResources().getStringArray(R.array.h2_items);
		String l3[] = getResources().getStringArray(R.array.h3_items);
		for (String title : headingItems) {
			Headings.add(title);
		}
		for (String title : l1) {
			L1.add(title);
		}
		for (String title : l2) {
			L2.add(title);
		}
		for (String title : l3) {
			L3.add(title);
		}
		childList.put(Headings.get(0), L1);
		childList.put(Headings.get(1), L2);
		childList.put(Headings.get(2), L3);
		MyAdapter myAdapter = new MyAdapter(this, Headings, childList);
		expandableListView.setAdapter(myAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
