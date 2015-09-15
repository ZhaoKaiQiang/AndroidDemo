package com.socks.androiddemo.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.socks.androiddemo.base.BaseActivity;

import java.io.File;

/**
 * Created by zhaokaiqiang on 15/8/26.
 */
public class ApkFileInfoActivity extends BaseActivity {

    private static final String TAG = "ApkFileInfoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File apkFile = new File(file, "提分初中.apk");
        PackageManager packageManager = getPackageManager();
        PackageInfo info = packageManager.getPackageArchiveInfo(apkFile.getPath(), PackageManager.GET_ACTIVITIES);

        if (info != null) {
            Log.d(TAG, "提分初中.apk packageName =" + info.packageName);
        }

        File otherFile = new File(file, "巴朵游戏.apk");
        PackageInfo otherInfo = packageManager.getPackageArchiveInfo(otherFile.getPath(), PackageManager.GET_ACTIVITIES);

        if (otherInfo != null) {
            Log.d(TAG, "巴朵游戏.apk packageName =" + otherInfo.packageName);
            Log.d(TAG, "巴朵游戏.apk versionCode =" + otherInfo.versionCode);
            Log.d(TAG, "巴朵游戏.apk versionName =" + otherInfo.versionName);

        }
    }

}
