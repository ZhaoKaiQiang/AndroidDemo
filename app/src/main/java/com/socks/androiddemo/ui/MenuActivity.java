package com.socks.androiddemo.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.socks.androiddemo.R;
import com.socks.androiddemo.base.BaseActivity;

/**
 * Created by zhaokaiqiang on 15/5/21.
 */
public class MenuActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_intent, menu);

		return super.onCreateOptionsMenu(menu);
	}

	public void onGroupItemClick(MenuItem item) {
		// One of the group items (using the onClick attribute) was clicked
		// The item parameter passed here indicates which item it is
		// All other menu item clicks are handled by onOptionsItemSelected()
	}
}
