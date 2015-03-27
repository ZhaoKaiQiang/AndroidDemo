package com.socks.androiddemo.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Activity»ùÀà
 * 
 * @ClassName: BaseActivity
 * @author ZhaoKaiQiang
 * @date 2015-3-27 ÉÏÎç11:23:20
 */
public class BaseActivity extends Activity implements Initialable {

	protected Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
	}

	@Override
	public void initView() {

	}

	@Override
	public void initDate() {

	}

}
