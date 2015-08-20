package com.socks.androiddemo.ui;

import android.os.Bundle;
import android.os.Environment;

import com.socks.androiddemo.R;
import com.socks.androiddemo.base.BaseActivity;


public class OtherActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);

		getExternalCacheDir();
		getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

		getCacheDir();
		getFilesDir();

	}

}
