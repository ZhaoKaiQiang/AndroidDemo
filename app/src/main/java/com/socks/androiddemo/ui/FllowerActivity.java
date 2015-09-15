package com.socks.androiddemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.socks.androiddemo.R;
import com.socks.androiddemo.view.FllowerView;

public class FllowerActivity extends AppCompatActivity {

    private FllowerView fllower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fllower);

        fllower = (FllowerView) findViewById(R.id.fllower);
    }

    public void show(View view) {
        fllower.startAnimation();
    }

}
