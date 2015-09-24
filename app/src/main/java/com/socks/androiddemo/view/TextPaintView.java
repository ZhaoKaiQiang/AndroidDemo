package com.socks.androiddemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhaokaiqiang on 15/9/21.
 */
public class TextPaintView extends View{

    private TextPaint mPaint;

    public TextPaintView(Context context) {
        super(context);
    }

    public TextPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextPaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
