package com.socks.androiddemo.ui;

import android.os.Bundle;

import com.socks.androiddemo.R;
import com.socks.androiddemo.base.BaseActivity;
import com.socks.androiddemo.view.BitmapShaderView;
import com.socks.androiddemo.view.Titanic;

/**
 * Created by zhaokaiqiang on 15/9/7.
 */
public class BitmapShaderActivity extends BaseActivity {

    private BitmapShaderView shaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_shader);
        shaderView = (BitmapShaderView) findViewById(R.id.shader);
        new Titanic().start(shaderView);

    }

}
