package com.socks.androiddemo.base;

import android.app.Application;

import com.socks.androiddemo.utils.logger.LogLevel;
import com.socks.androiddemo.utils.logger.Logger;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Logger.init("debugTAG").setLogLevel(LogLevel.FULL).hideThreadInfo();
	}

}
