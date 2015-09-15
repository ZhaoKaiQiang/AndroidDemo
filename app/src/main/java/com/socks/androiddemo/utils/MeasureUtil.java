package com.socks.androiddemo.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by zhaokaiqiang on 15/9/14.
 */
public class MeasureUtil {
    public static int[] getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int[] screenSize = new int[]{metrics.widthPixels, metrics.heightPixels};
        return screenSize;
    }
}
