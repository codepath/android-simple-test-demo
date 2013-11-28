package com.codepath.example.simpleapp.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.example.simpleapp.FirstActivity;
import com.codepath.example.simpleapp.R;
import com.codepath.example.simpleapp.SecondActivity;
import com.jayway.android.robotium.solo.Solo;

public class RobotiumActivityFunctionalTest extends
		ActivityInstrumentationTestCase2<FirstActivity> {
	// Solo Robotium helper object
	private Solo solo;

	public RobotiumActivityFunctionalTest() {
		super(FirstActivity.class);
	}

	// Setup test case with solo
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	
	public void testStartSecondActivity() throws Exception {		
		final String fieldValue = "Testing Text";
		
		// Set a value into the text field
		solo.enterText((EditText) solo.getView(R.id.etResult), fieldValue);
		
		// find button and click it
		solo.clickOnButton("Launch");
		// or solo.clickOnView(solo.getView(R.id.btnLaunch));

		// Wait 2 seconds for the start of the activity
		solo.waitForActivity(SecondActivity.class, 2000);
		solo.assertCurrentActivity("Should be second activity", SecondActivity.class);
		// Search for the textView
		TextView textView = (TextView) solo.getView(R.id.tvResult);
		
		// Validate the text on the TextView
		assertEquals("Text should be the field value", fieldValue, 
				textView.getText().toString());
	}

	// Finalize solo object
	@Override
	public void tearDown() throws Exception {
		try {
			solo.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		getActivity().finish();
		super.tearDown();
	}
}
