package com.example.samplejunit.test;

import com.example.samplejunit.SampleJUnitActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SampleTestUnit extends ActivityInstrumentationTestCase2<SampleJUnitActivity> {
	private SampleJUnitActivity mActivity;
	private TextView mTextView;
	private Button mCKilos, mCPounds;
	private EditText mKilos, mPounds;

	@SuppressWarnings("deprecation")
	public SampleTestUnit() {
		super("com.example.samplejunit", SampleJUnitActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		mActivity = this.getActivity();
		mTextView = (TextView) mActivity.findViewById(com.example.samplejunit.R.id.resultview);
		mCKilos = (Button) mActivity.findViewById(com.example.samplejunit.R.id.Kilos);
		mCPounds = (Button) mActivity.findViewById(com.example.samplejunit.R.id.Pounds);
		mKilos = (EditText) mActivity.findViewById(com.example.samplejunit.R.id.inputvalueK);
		mPounds = (EditText) mActivity.findViewById(com.example.samplejunit.R.id.inputvalueP);
	}

	@SmallTest
	public void testViews() {
		assertNotNull(getActivity());
		assertNotNull(mTextView);
		assertNotNull(mCKilos);
		assertNotNull(mCPounds);
		assertNotNull(mKilos);
		assertNotNull(mPounds);
	}

	@SmallTest
	public void testKilosToPounds() {
		mKilos.clearComposingText();
		TouchUtils.tapView(this, mKilos);
		sendKeys("1");
		TouchUtils.clickView(this, mCPounds);
		double pounds;
		try {
			pounds = Double.parseDouble(mTextView.getText().toString());
		} catch (NumberFormatException e) {
			pounds = -1;
		}
		assertTrue("1 kilo is 2.20462262 pounds", pounds > 2.2 && pounds < 2.3);
	}

	@SmallTest
	public void testPoundsToKilos() {
		mPounds.clearComposingText();
		TouchUtils.tapView(this, mPounds);
		sendKeys("1");
		TouchUtils.clickView(this, mCKilos);
		double kilos;
		try {
			kilos = Double.parseDouble(mTextView.getText().toString());
		} catch (NumberFormatException e) {
			kilos = -1;
		}
		assertTrue("1 pound is 0.45359237 kilos", kilos > 0.4 && kilos < 0.5);
	}
}
