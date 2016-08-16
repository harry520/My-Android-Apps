package com.example.samplejunit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SampleJUnitActivity extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	Button mCKilos, mCPounds;
	EditText mKilos, mPounds;
	TextView mResult;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mResult = (TextView) findViewById(R.id.resultview);
		mKilos = (EditText) findViewById(R.id.inputvalueK);
		mPounds = (EditText) findViewById(R.id.inputvalueP);
		mCKilos = (Button) findViewById(R.id.Kilos);
		mCPounds = (Button) findViewById(R.id.Pounds);
		mCKilos.setOnClickListener(this);
		mCPounds.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Kilos: {
			double pounds = Double.parseDouble(mPounds.getText().toString());
			double kilos = pounds * 0.45359237;
			mResult.setText(new Double(kilos).toString());
		}
			break;
		case R.id.Pounds: {
			double kilos = Double.parseDouble(mKilos.getText().toString());
			double pounds = kilos * 2.20462262;
			mResult.setText(new Double(pounds).toString());
		}
			break;
		}
	}
}
