package com.example.sqlitedemo;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity {

	DatabaseHelper myDb;
	EditText editId, editFirstName, editLastName, editMarks;
	Button btnAddData;
	Button btnViewAll;
	Button btnUpdate;
	Button btnDelete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myDb = new DatabaseHelper(this);
		editId = (EditText) findViewById(R.id.editText_id);
		editFirstName = (EditText) findViewById(R.id.editText_firstName);
		editLastName = (EditText) findViewById(R.id.editText_lastName);
		editMarks = (EditText) findViewById(R.id.editText_marks);
		btnAddData = (Button) findViewById(R.id.button_add);
		btnViewAll = (Button) findViewById(R.id.button_viewAll);
		btnUpdate = (Button) findViewById(R.id.button_update);
		btnDelete = (Button) findViewById(R.id.button_delete);
		addData();
		viewAll();
		updateData();
		deleteData();
	}

	public void addData() {
		btnAddData.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean isInserted = myDb.insertData(editFirstName.getText().toString(),
						editLastName.getText().toString(), editMarks.getText().toString());
				if (isInserted == true)
					Toast.makeText(MainActivity.this, "Data inserted.", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(MainActivity.this, "Data not inserted.", Toast.LENGTH_LONG).show();
			}
		});
	}

	public void viewAll() {
		btnViewAll.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Cursor result = myDb.getAllData();
				if (result.getCount() == 0) {
					showMessage("Error", "Nothing found.");
					return;
				}
				StringBuffer buffer = new StringBuffer();
				while (result.moveToNext()) {
					buffer.append("Id: " + result.getString(0) + "\n");
					buffer.append("First Name: " + result.getString(1) + "\n");
					buffer.append("Last Name: " + result.getString(2) + "\n");
					buffer.append("Marks: " + result.getString(3) + "\n\n");
				}
				showMessage("Data", buffer.toString());
			}
		});
	}

	public void showMessage(String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}

	public void updateData() {
		btnUpdate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean isUpdated = myDb.updateData(editId.getText().toString(), editFirstName.getText().toString(),
						editLastName.getText().toString(), editMarks.getText().toString());
				if (isUpdated == true)
					Toast.makeText(MainActivity.this, "Data updated.", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(MainActivity.this, "Data not updated.", Toast.LENGTH_LONG).show();
			}
		});
	}

	public void deleteData() {
		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Integer deleteRows = myDb.deleteData(editId.getText().toString());
				if (deleteRows > 0)
					Toast.makeText(MainActivity.this, "Data deleted.", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(MainActivity.this, "Data not deleted.", Toast.LENGTH_LONG).show();
			}
		});
	}

}
