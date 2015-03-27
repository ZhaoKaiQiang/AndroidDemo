package com.socks.androiddemo.ui;

import android.os.Bundle;
import android.os.Process;

import com.socks.androiddemo.R;
import com.socks.androiddemo.ui.base.BaseActivity;
import com.socks.androiddemo.utils.logger.Logger;

/**
 * Õ®”√OtherActivity
 * @ClassName: OtherActivity
 * @author ZhaoKaiQiang
 * @date 2015-3-27 …œŒÁ11:27:20
 */
public class OtherActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		
		Logger.d("OtherActivity------Process.myPid() = " + Process.myPid());
		
	}

}
