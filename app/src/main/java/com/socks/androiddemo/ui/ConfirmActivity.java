package com.socks.androiddemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.socks.androiddemo.R;
import com.socks.androiddemo.view.ConfirmView;

public class ConfirmActivity extends AppCompatActivity {

    private ConfirmView confirmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        confirmView = (ConfirmView) findViewById(R.id.confirm_view);
    }

    public void progressing(View view) {
        confirmView.animatedWithState(ConfirmView.State.Progressing);
    }

    public void fail(View view) {
        confirmView.animatedWithState(ConfirmView.State.Fail);
    }

    public void success(View view) {
        confirmView.animatedWithState(ConfirmView.State.Success);
    }

}
