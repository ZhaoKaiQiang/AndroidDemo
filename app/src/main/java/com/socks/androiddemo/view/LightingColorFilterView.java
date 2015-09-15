package com.socks.androiddemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.socks.androiddemo.R;

/**
 * Created by zhaokaiqiang on 15/9/14.
 */
public class LightingColorFilterView extends ImageView {

    private Paint mPaint;// 画笔
    private Bitmap bitmap;// 位图

    public LightingColorFilterView(Context context) {
        this(context, null);
    }

    public LightingColorFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0x00FF00FF));
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }
}
