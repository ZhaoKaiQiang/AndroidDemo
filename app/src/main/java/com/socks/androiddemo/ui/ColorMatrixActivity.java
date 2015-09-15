package com.socks.androiddemo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import com.socks.androiddemo.R;
import com.socks.androiddemo.base.BaseActivity;

/**
 * Created by zhaokaiqiang on 15/9/7.
 */
public class ColorMatrixActivity extends BaseActivity {

    private ImageView img_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        img_new = (ImageView) findViewById(R.id.img_new);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sophie);
        img_new.setImageBitmap(getGreyBitmap(bitmap));
    }

    private Bitmap getGreyBitmap(Bitmap bitmap) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        ColorMatrix matrix = new ColorMatrix();
        //饱和度设置为0则为灰度图
        matrix.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bmp;
    }


}
