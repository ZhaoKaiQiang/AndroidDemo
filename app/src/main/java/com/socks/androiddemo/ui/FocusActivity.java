package com.socks.androiddemo.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;

import com.socks.androiddemo.R;
import com.socks.androiddemo.base.BaseActivity;

/**
 * Created by zhaokaiqiang on 15/5/21.
 */
public class FocusActivity extends BaseActivity {

	private EditText et;

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_focus);

		et = (EditText) findViewById(R.id.et);
		textView = (TextView) findViewById(R.id.tv);

		Resources res = getResources();
		String text = String.format(res.getString(R.string.welcome_messages), "赵凯强", 10);
		CharSequence styledText = Html.fromHtml(text);

		textView.setText(styledText);

	}

}
