package com.codepath.example.simpleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class FirstActivity extends Activity {
	EditText etResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		etResult = (EditText) findViewById(R.id.etResult);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}
	
	public void onLaunch(View v) {
		Intent i = new Intent(this, SecondActivity.class);
		i.putExtra("result", etResult.getText().toString());
		startActivity(i);
	}

}
