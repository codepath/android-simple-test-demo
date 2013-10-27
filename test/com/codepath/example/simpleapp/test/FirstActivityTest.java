package com.codepath.example.simpleapp.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.robolectric.Robolectric.shadowOf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.example.simpleapp.FirstActivity;
import com.codepath.example.simpleapp.R;
import com.codepath.example.simpleapp.SecondActivity;

@RunWith(RobolectricTestRunner.class)
public class FirstActivityTest {
	private Button btnLaunch;
	private EditText etResult;
	private FirstActivity activity;

	@Before
	public void setup() throws Exception{
	  activity = Robolectric.buildActivity(FirstActivity.class).create().visible().get();
	  btnLaunch = (Button) activity.findViewById(R.id.btnLaunch);
	  etResult = (EditText) activity.findViewById(R.id.etResult);
	}
    
	// Sanity check for the layout
	@Test
	public void shouldHaveButtonThatSaysAudit() throws Exception{
	  // Verifies the button and text field exist
	  assertThat(btnLaunch, notNullValue());
	  assertNotNull(etResult);
	  // Verifies the text of the button
	  assertThat((String) btnLaunch.getText(), equalTo("Launch"));
	}

	// Validate the intent is fired on button press with correct result
	@Test
	public void pressingLaunchButtonForSecondActivity() throws Exception {
	  String resultValue = "Testing Text";
	  // Set a value into the text field
	  etResult.setText(resultValue);
	  // Verify button exists on screen
	  assertNotNull(btnLaunch);
	  // Trigger a click on the button
	  btnLaunch.performClick();
	  // Construct Shadow versions of Activity and Intent
	  Intent startedIntent = shadowOf(activity).getNextStartedActivity();
	  // Verify the intent was started with correct result extra
	  assertThat(resultValue, equalTo(startedIntent.getStringExtra("result")));
	  assertThat(startedIntent.getComponent().getClassName(), equalTo(SecondActivity.class.getName()));
	}
	
	@After
    public void tearDown() throws Exception {
	  // Nothing Here
    }
 
}