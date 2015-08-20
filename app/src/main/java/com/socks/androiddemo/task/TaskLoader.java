package com.socks.androiddemo.task;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by zhaokaiqiang on 15/3/31.
 */
public class TaskLoader extends AsyncTaskLoader {

	public TaskLoader(Context context) {
		super(context);
	}

	@Override
	public Object loadInBackground() {
		return null;
	}
}
