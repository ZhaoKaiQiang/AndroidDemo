package com.socks.androiddemo.ui;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.socks.androiddemo.R;
import com.socks.androiddemo.base.BaseActivity;

/**
 * Created by zhaokaiqiang on 15/8/24.
 */
public class Camera3DActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

	private static final String TAG = "Camera3DActivity";

	private SeekBar sb_rotate_x;
	private TextView tv_rotate_x;
	private ImageView img_rorate_x;

	private SeekBar sb_rotate_y;
	private TextView tv_rotate_y;
	private ImageView img_rorate_y;

	private SeekBar sb_rotate_z;
	private TextView tv_rotate_z;
	private ImageView img_rorate_z;

	private ImageView img_rotate_xyz;

	private SeekBar sb_translate_x;
	private TextView tv_translate_x;
	private ImageView img_translate_x;

	private SeekBar sb_translate_y;
	private TextView tv_translate_y;
	private ImageView img_translate_y;

	private SeekBar sb_translate_z;
	private TextView tv_translate_z;
	private ImageView img_translate_z;

	private ImageView img_translate_xyz;

	private Camera mCamera;
	private Matrix mMatrix;

	private static Bitmap mBitmap;

	private Bitmap temBitmap;

	private int rotateX;
	private int rotateY;
	private int rotateZ;

	private int width;
	private int height;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_3d);

		sb_rotate_x = (SeekBar) findViewById(R.id.sb_rotate_x);
		img_rorate_x = (ImageView) findViewById(R.id.img_rorate_x);
		tv_rotate_x = (TextView) findViewById(R.id.tv_rotate_x);

		sb_rotate_y = (SeekBar) findViewById(R.id.sb_rotate_y);
		img_rorate_y = (ImageView) findViewById(R.id.img_rorate_y);
		tv_rotate_y = (TextView) findViewById(R.id.tv_rotate_y);

		sb_rotate_z = (SeekBar) findViewById(R.id.sb_rotate_z);
		img_rorate_z = (ImageView) findViewById(R.id.img_rorate_z);
		tv_rotate_z = (TextView) findViewById(R.id.tv_rotate_z);

		img_rotate_xyz = (ImageView) findViewById(R.id.img_rorate_xyz);

		sb_translate_x = (SeekBar) findViewById(R.id.sb_translate_x);
		tv_translate_x = (TextView) findViewById(R.id.tv_translate_x);
		img_translate_x = (ImageView) findViewById(R.id.img_translate_x);

		sb_translate_y = (SeekBar) findViewById(R.id.sb_translate_y);
		tv_translate_y = (TextView) findViewById(R.id.tv_translate_y);
		img_translate_y = (ImageView) findViewById(R.id.img_translate_y);

		sb_translate_z = (SeekBar) findViewById(R.id.sb_translate_z);
		tv_translate_z = (TextView) findViewById(R.id.tv_translate_z);
		img_translate_z = (ImageView) findViewById(R.id.img_translate_z);

		sb_rotate_x.setOnSeekBarChangeListener(this);
		sb_rotate_y.setOnSeekBarChangeListener(this);
		sb_rotate_z.setOnSeekBarChangeListener(this);
		sb_translate_x.setOnSeekBarChangeListener(this);
		sb_translate_y.setOnSeekBarChangeListener(this);
		sb_translate_z.setOnSeekBarChangeListener(this);

		init();
	}

	private void init() {
		mCamera = new Camera();
		mMatrix = new Matrix();
		mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
		width = mBitmap.getWidth();
		height = mBitmap.getHeight();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

		if (seekBar == sb_rotate_x) {
			rotate_x(progress);
			tv_rotate_x.setText(Integer.toString(progress));
		} else if (seekBar == sb_rotate_y) {
			rotate_y(progress);
			tv_rotate_y.setText(Integer.toString(progress));
		} else if (seekBar == sb_rotate_z) {
			rotate_z(progress);
			tv_rotate_z.setText(Integer.toString(progress));
		} else if (seekBar == sb_translate_x) {
			tv_translate_x.setText("" + (progress - 500));
			translateX(progress - 500);
//			locationX(progress - 500);
		} else if (seekBar == sb_translate_z) {
			tv_translate_z.setText("" + (progress - 200));
			translateZ(progress - 200);
		} else if (seekBar == sb_translate_y) {
			tv_translate_y.setText("" + (progress - 100));
			translateY(progress - 100);
		}

	}

	private void rotate_x(int progress) {

		rotateX = progress;
		rotateXYZ();

		mCamera.save();
		mCamera.rotateX(progress);
		mCamera.getMatrix(mMatrix);

		mMatrix.preTranslate(-(img_rorate_x.getWidth() / 2), -(img_rorate_x.getHeight() / 2));
		mMatrix.postTranslate((img_rorate_x.getWidth() / 2), (img_rorate_x.getHeight() / 2));

		try {
			temBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, mMatrix, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mCamera.restore();
		mMatrix.reset();

		if (temBitmap != null) {
			img_rorate_x.setImageBitmap(temBitmap);
		}

	}

	private void rotate_y(int progress) {

		rotateY = progress;
		rotateXYZ();

		mCamera.save();
		mCamera.rotateY(progress);
		mCamera.getMatrix(mMatrix);

		try {
			temBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, mMatrix, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mCamera.restore();
		mMatrix.reset();

		if (temBitmap != null) {
			img_rorate_y.setImageBitmap(temBitmap);
		}

	}

	private void rotate_z(int progress) {

		rotateZ = progress;
		rotateXYZ();

		mCamera.save();
		mCamera.rotateZ(progress);
		mCamera.getMatrix(mMatrix);

		try {
			temBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, mMatrix, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mCamera.restore();
		mMatrix.reset();

		if (temBitmap != null) {
			img_rorate_z.setImageBitmap(temBitmap);
		}

	}

	private void rotateXYZ() {

		mCamera.save();
		mCamera.rotateX(rotateX);
		mCamera.rotateY(rotateY);
		mCamera.rotateZ(rotateZ);
		mCamera.getMatrix(mMatrix);

		try {
			temBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, mMatrix, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mCamera.restore();
		mMatrix.reset();

		if (temBitmap != null) {
			img_rotate_xyz.setImageBitmap(temBitmap);
		}

	}

	@TargetApi(16)
	private void translateX(float progress) {
		Log.d(TAG, "x = " + mCamera.getLocationX() + " y = " + mCamera.getLocationY() + " z = " + mCamera.getLocationZ());
		mCamera.save();
		mCamera.translate(progress, 0, 0);
		mCamera.getMatrix(mMatrix);
		try {
			temBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, mMatrix, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d(TAG, "x = " + mCamera.getLocationX() + " y = " + mCamera.getLocationY() + " z = " + mCamera.getLocationZ());
		mCamera.restore();
		mMatrix.reset();

		if (temBitmap != null) {
			img_translate_x.setImageBitmap(temBitmap);
		}
	}

	private void translateY(float progress) {
		mCamera.save();
		mCamera.translate(0, progress, 0);
		Log.d(TAG, "translateY--" + progress);
		mCamera.getMatrix(mMatrix);
		try {
			temBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, mMatrix, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mCamera.restore();
		mMatrix.reset();

		if (temBitmap != null) {
			img_translate_y.setImageBitmap(temBitmap);
		}
	}

	@TargetApi(16)
	private void translateZ(float progress) {

		mCamera.save();
		mCamera.translate(progress, progress, progress);
		mCamera.getMatrix(mMatrix);
		try {
			temBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, mMatrix, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mCamera.restore();
		mMatrix.reset();

		if (temBitmap != null) {
			img_translate_z.setImageBitmap(temBitmap);

		}
	}

	@TargetApi(16)
	private void locationX(float progress) {

		mCamera.save();
		mCamera.translate(0, 0, progress);
//		mCamera.setLocation(progress,0,0);
		mCamera.getMatrix(mMatrix);
		try {
			temBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, mMatrix, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d(TAG, "x = " + mCamera.getLocationX() + " y = " + mCamera.getLocationY() + " z = " + mCamera.getLocationZ());
		mCamera.restore();
		mMatrix.reset();

		if (temBitmap != null) {
			img_translate_x.setImageBitmap(temBitmap);

		}
	}


	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}
}
