package com.socks.androiddemo.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.socks.androiddemo.R;
import com.socks.androiddemo.base.BaseActivity;

public class SpannableActivity extends BaseActivity {

	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spannable);
		initView();
		initDate();
	}

	@Override
	public void initView() {
		tv = (TextView) findViewById(R.id.tv);
	}

	@Override
	public void initDate() {
		String sp = "12345";
		SpannableString spannableString = new SpannableString(sp);
		StyleSpan styleSpan = new StyleSpan(Typeface.BOLD_ITALIC);
		spannableString.setSpan(styleSpan, 0, sp.length(),
				Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		tv.setText(spannableString);
		tv.setMovementMethod(LinkMovementMethod.getInstance());
	}

}
