package com.socks.androiddemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.socks.androiddemo.R;

public class IntentActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent);
	}

	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d("TAG","onSaveInstanceState");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d("TAG","onRestoreInstanceState");
	}

	public void createAlarm(View view) {
		Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
				.putExtra(AlarmClock.EXTRA_MESSAGE, "createAlarm")
				.putExtra(AlarmClock.EXTRA_HOUR, 14)
				.putExtra(AlarmClock.EXTRA_MINUTES, 10);
		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		}
	}

	public void startTimer(View view) {
		Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
				.putExtra(AlarmClock.EXTRA_MESSAGE, "Timer'")
				.putExtra(AlarmClock.EXTRA_LENGTH, 60)
				.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		} else {
			Toast.makeText(this, "No Activity", Toast.LENGTH_SHORT).show();
		}
	}


}
