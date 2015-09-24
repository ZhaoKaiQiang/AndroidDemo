package com.socks.androiddemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhaokaiqiang on 15/9/17.
 */
public class ShaderView extends View {

    private float mWidth;
    private float mHeight;

    private Paint mPaint;

    LinearGradient linearGradient;

    public ShaderView(Context context) {
        super(context);
        init();
    }

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        linearGradient = new LinearGradient(0, 0, 0, mHeight / 2,
                new int[]{Color.RED, Color.YELLOW},
                new float[]{0.2f, 0.8f}, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setShader(linearGradient);
        canvas.drawRect(0, 0, mWidth / 2, mHeight / 2, mPaint);
    }
}
