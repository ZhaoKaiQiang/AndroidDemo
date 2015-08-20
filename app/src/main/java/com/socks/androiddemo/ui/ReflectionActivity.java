package com.socks.androiddemo.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.socks.androiddemo.base.BaseActivity;

public class ReflectionActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final FrameLayout root = new FrameLayout(this);
		setContentView(root);

		final TextView textView = new TextView(this);
		textView.setText("test");
		textView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
		textView.setTextColor(Color.WHITE);
		textView.setTextSize(40);
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		textView.setBackgroundColor(Color.RED);
		textView.setPadding(100, 20, 100, 20);

		final int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		textView.measure(measureSpec, measureSpec);
		textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
		final Bitmap b = Bitmap.createBitmap(textView.getWidth(), textView.getHeight(), Bitmap.Config.ARGB_8888);
		final Canvas c = new Canvas(b);
		textView.draw(c);

		final ImageView imageView = new ImageView(this);
		imageView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
		imageView.setImageBitmap(createReflection(b, 1, 10));
		root.addView(imageView);
	}

	private Bitmap createReflection(Bitmap original, float percentage, int gap) {

		final int reflectionHeight = (int) (original.getHeight() * percentage);
		Bitmap bitmapWithReflection = Bitmap.createBitmap(original.getWidth(), (original.getHeight() + reflectionHeight + gap), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapWithReflection);

		// original image
		canvas.drawBitmap(original, 0, 0, null);
		// gap drawing
		final Paint transparentPaint = new Paint();
		transparentPaint.setARGB(0, 255, 255, 255);
		canvas.drawRect(0, original.getHeight(), original.getWidth(), original.getHeight() + gap, transparentPaint);
		// reflection
		final Matrix matrix = new Matrix();
		matrix.preScale(1, -1);
		canvas.drawBitmap(Bitmap.createBitmap(original, 0, original.getHeight() - reflectionHeight, original.getWidth(), reflectionHeight, matrix, false), 0, original.getHeight() + gap, null);
		// reflection shading
		final Paint fadePaint = new Paint();
		fadePaint.setShader(new LinearGradient(0, original.getHeight(), 0, original.getHeight() + reflectionHeight + gap, 0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP));
		fadePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		canvas.drawRect(0, original.getHeight(), original.getWidth(), bitmapWithReflection.getHeight() + gap, fadePaint);

		original.recycle();
		return bitmapWithReflection;
	}

}