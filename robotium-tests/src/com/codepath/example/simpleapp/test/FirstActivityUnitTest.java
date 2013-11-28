package com.codepath.example.simpleapp.test;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.example.simpleapp.R;
import com.codepath.example.simpleapp.FirstActivity;

public class FirstActivityUnitTest extends
		android.test.ActivityUnitTestCase<FirstActivity> {

	private FirstActivity activity;

	public FirstActivityUnitTest() {
		super(FirstActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				FirstActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}

	// Sanity check for the layout
	@SmallTest
	public void testLayoutExists() {
		// Verifies the button and text field exist
		assertNotNull(activity.findViewById(R.id.btnLaunch));
		assertNotNull(activity.findViewById(R.id.etResult));
		// Verifies the text of the button
		Button view = (Button) activity.findViewById(R.id.btnLaunch);
		assertEquals("Incorrect label of the button", "Launch", view.getText());
	}

	// Validate the intent is fired on button press with correct result from
	// text field
	@SmallTest
	public void testIntentTriggerViaOnClick() {
		String fieldValue = "Testing Text";
		// Set a value into the text field
		EditText etResult = (EditText) activity.findViewById(R.id.etResult);
		etResult.setText(fieldValue);
		// Verify button exists on screen
		Button btnLaunch = (Button) activity.findViewById(R.id.btnLaunch);
		assertNotNull("Button should not be null", btnLaunch);
		// Trigger a click on the button
		btnLaunch.performClick();
		// Verify the intent was started with correct result extra
		Intent triggeredIntent = getStartedActivityIntent();
		assertNotNull("Intent should have triggered after button press",
				triggeredIntent);
		String data = triggeredIntent.getExtras().getString("result");
		assertEquals("Incorrect result data passed via the intent",
				"Testing Text", data);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}