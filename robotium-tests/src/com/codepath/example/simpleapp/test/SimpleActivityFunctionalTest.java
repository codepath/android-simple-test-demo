package com.codepath.example.simpleapp.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.example.simpleapp.FirstActivity;
import com.codepath.example.simpleapp.R;
import com.codepath.example.simpleapp.SecondActivity;

public class SimpleActivityFunctionalTest extends
		ActivityInstrumentationTestCase2<FirstActivity> {

	private FirstActivity activity;

	public SimpleActivityFunctionalTest() {
		super(FirstActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		activity = getActivity();
	}

	public void testStartSecondActivity() throws Exception {		
		final String fieldValue = "Testing Text";
		
		// Set a value into the text field
		final EditText etResult = (EditText) activity.findViewById(R.id.etResult);
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				etResult.setText(fieldValue);
			}
		});
		
		// Add monitor to check for the second activity
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				SecondActivity.class.getName(), null, false);
		
		// find button and click it
		Button btnLaunch = (Button) activity.findViewById(R.id.btnLaunch);
		TouchUtils.clickView(this, btnLaunch);
		
		// Wait 2 seconds for the start of the activity
		SecondActivity secondActivity = (SecondActivity) monitor
				.waitForActivityWithTimeout(2000);
		assertNotNull(secondActivity);

		// Search for the textView
		TextView textView = (TextView) secondActivity
				.findViewById(R.id.tvResult);
		
		// check that the TextView is on the screen
		ViewAsserts.assertOnScreen(secondActivity.getWindow().getDecorView(),
				textView);
		
		// Validate the text on the TextView
		assertEquals("Text should be the field value", fieldValue, textView
				.getText().toString());

		// Press back and click again
		this.sendKeys(KeyEvent.KEYCODE_BACK);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
