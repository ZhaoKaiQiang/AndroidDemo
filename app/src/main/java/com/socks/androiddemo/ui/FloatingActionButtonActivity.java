package com.socks.androiddemo.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import com.socks.androiddemo.R;
import com.socks.androiddemo.base.BaseActivity;


public class FloatingActionButtonActivity extends BaseActivity {

	private FloatingActionButton button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_floating_button);
		button = (FloatingActionButton) findViewById(R.id.btn);
	}

}
