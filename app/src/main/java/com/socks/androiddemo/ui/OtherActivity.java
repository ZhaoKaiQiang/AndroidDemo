package com.socks.androiddemo.ui;

import android.os.Bundle;
import android.os.Process;

import com.socks.androiddemo.R;
import com.socks.androiddemo.base.BaseActivity;
import com.socks.androiddemo.utils.logger.Logger;


public class OtherActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		
		Logger.d("OtherActivity------Process.myPid() = " + Process.myPid());
		
	}

}
