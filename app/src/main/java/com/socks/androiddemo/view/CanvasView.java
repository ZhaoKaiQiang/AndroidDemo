package com.socks.androiddemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhaokaiqiang on 15/9/21.
 */
public class CanvasView extends View {

    private static final float TOTAL_SQUARE_COUNT = 15;
    private Paint mPaint;
    private float mWidth;
    private float mHeight;
    private RectF mRect;
    private float mScale = 1;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        mScale = 1;
//        while (mScale >= 0.1) {
//            mScale *= 0.9;
//            canvas.save();
//            canvas.scale(mScale, mScale, mWidth / 2, mHeight / 2);
//            canvas.drawRect(mRect, mPaint);
//            canvas.restore();
//        }

        for (int i = 0; i < TOTAL_SQUARE_COUNT; i++) {
            // 保存画布
            canvas.save();
            float fraction = (float) i / TOTAL_SQUARE_COUNT;
            // 将画布以正方形中心进行缩放
            canvas.scale(fraction, fraction, mWidth / 2, mWidth / 2);
            canvas.drawRect(mRect, mPaint);
            // 画布回滚
            canvas.restore();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = h > w ? w : h;
        mHeight = h > w ? h : w;
        mRect = new RectF(0, 0, mWidth, mWidth);
    }
}
