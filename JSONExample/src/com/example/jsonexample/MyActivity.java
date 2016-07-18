package com.example.jsonexample;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MyActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_activity);
		String a = "Name: Ramkumar-Mark: Eng: 1243, Tamil: 245, Phy: 11200-Sub: Math-Status: Pass";
		String[] array = a.split("-");
		for (int i = 0; i < array.length; i++) {
			Log.w("APP", array[i]);
			String b = array[i];
			String[] arr = b.split(": ");
			for (int j = 0; j < arr.length; j++) {
				Log.w("VALUES", arr[j]);
			}
		}
		String ja = "{\"Name\": \"Ramkumr\",\"Mark\": {\"PHY\": 123, \"MATH\": 167, \"CHE\": 156}, \"Sub\": \"Math\",\"Status\": \"Pass\"}";
		try {
			JSONObject jsonObject = new JSONObject(ja);
			Log.w("VALUES_JSON", jsonObject.getString("Name"));
			Log.w("VALUES_JSON", jsonObject.getString("Mark"));
			JSONObject markJsonObject = jsonObject.getJSONObject("Mark");
			Log.w("PHY", markJsonObject.getInt("PHY") + "");
			Log.w("CHE", markJsonObject.getInt("CHE") + "");
			Log.w("MATH", jsonObject.getInt("MATH") + "");
			Log.w("VALUES_JSON", jsonObject.getString("Sub"));
			Log.w("VALUES_JSON", jsonObject.getString("Status"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String cj = "{\"Result\": [{\"Name\": \"Ramkumar\"}, {\"Name\": \"Vinodh\"}, {\"Name\": \"Krishna\"}]}";
		try {
			JSONObject jsonObject = new JSONObject(cj);
			JSONArray resultArray = jsonObject.getJSONArray("Result");
			for (int i = 0; i < resultArray.length(); i++) {
				Log.w("J-NAME", resultArray.getJSONObject(i).getString("Name"));

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String dj = "{Result: {\"Name\": [{\"first name\": \"Ram\", \"last name\": \"Kumar\"}, {\"first name\": \"Raj\", \"last name\": \"Kumar\"}, {\"first name\": \"Shiva\", \"last name\": \"Kumar\"}]}}";
		try {
			JSONObject jsonObject = new JSONObject(dj);
			JSONObject resultJsonObject = jsonObject.getJSONObject("Result");
			JSONArray nameJsonArray = resultJsonObject.getJSONArray("Name");
			for (int i = 0; i < nameJsonArray.length(); i++) {
				Log.w("Last name", nameJsonArray.getJSONObject(i).getString("last name"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// JSON encoding
		ArrayList<HashMap<String, Object>> students = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Ram Kumar");
		map.put("mark", 100);
		map.put("sub", "math");
		map.put("status", "pass");
		students.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "Shiva Kumar");
		map.put("mark", 243);
		map.put("sub", "Phy");
		map.put("status", "pass");
		students.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "Raj Kumar");
		map.put("mark", 123);
		map.put("sub", "Che");
		map.put("status", "pass");
		students.add(map);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < students.size(); i++) {
			JSONObject jsonObject = new JSONObject();
            try {
				jsonObject.put("name", students.get(i).get("name"));
				jsonObject.put("mark", students.get(i).get("mark"));
				jsonObject.put("sub", students.get(i).get("sub"));
				jsonObject.put("status", students.get(i).get("status"));
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		Log.w("The encoded JSON", jsonArray.toString());
		try {
			JSONArray baseArray = new JSONArray(jsonArray.toString());
			for (int i = 0; i < baseArray.length(); i++) {
				Log.w("Name", baseArray.getJSONObject(i).getString("name"));
				Log.w("Status", baseArray.getJSONObject(i).getString("status"));
				Log.w("Mark", baseArray.getJSONObject(i).getInt("mark") + "");
				Log.w("Sub", baseArray.getJSONObject(i).getString("sub"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
