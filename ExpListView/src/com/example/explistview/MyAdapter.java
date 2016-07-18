package com.example.explistview;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseExpandableListAdapter {

	private List<String> headerTitles;
	private HashMap<String, List<String>> childTitles;
	private Context context;

	MyAdapter(Context context, List<String> headerTitles, HashMap<String, List<String>> childTitles) {
		this.context = context;
		this.headerTitles = headerTitles;
		this.childTitles = childTitles;
	}

	@Override
	public int getGroupCount() {
		return headerTitles.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childTitles.get(headerTitles.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return headerTitles.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childTitles.get(headerTitles.get(groupPosition)).get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		String title = (String) this.getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.parent_layout, null);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.heading_item);
		textView.setTypeface(null, Typeface.BOLD);
		textView.setText(title);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		String title = (String) this.getChild(groupPosition, childPosition);
		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.child_layout, null);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.child_item);
		textView.setText(title);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
