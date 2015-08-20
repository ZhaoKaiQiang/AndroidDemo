package com.socks.androiddemo.ui;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by zhaokaiqiang on 15/5/18.
 */
public class MyIntentService extends IntentService {

	public MyIntentService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
	}
}
