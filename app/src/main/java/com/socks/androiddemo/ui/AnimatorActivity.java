package com.socks.androiddemo.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.socks.androiddemo.R;

/**
 * Created by zhaokaiqiang on 15/8/16.
 */
public class AnimatorActivity extends Activity {


	private Button btn_one;
	private Button btn_two;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animator);

		btn_one = (Button) findViewById(R.id.btn_one);
		btn_two = (Button) findViewById(R.id.btn_two);

	}

	public void startValue(View view) {
		ValueAnimator animator = ValueAnimator.ofFloat(0, 50);
		animator.setDuration(2000);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {

				float values = (float) animation.getAnimatedValue();
				Log.d("AnimatorActivity", "values = " + values);
				btn_one.setTextSize(values);
			}
		});
		animator.start();
	}

	public void startObject(View view) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(btn_two, "textSize", 0f, 50f);
		animator.setDuration(2000);
		animator.start();
	}

}
