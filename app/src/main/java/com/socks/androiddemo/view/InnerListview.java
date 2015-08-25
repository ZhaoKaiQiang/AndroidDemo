package com.socks.androiddemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by zhaokaiqiang on 15/8/21.
 */
public class InnerListview extends ListView{
	public InnerListview(Context context) {
		super(context);
	}

	public InnerListview(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public InnerListview(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public InnerListview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		getParent().requestDisallowInterceptTouchEvent(true);

		switch (ev.getAction()){
			case MotionEvent.ACTION_UP:
				getParent().requestDisallowInterceptTouchEvent(false);
				break;
		}

		return super.onTouchEvent(ev);
	}
}
