package com.socks.androiddemo.ui;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;

import com.socks.androiddemo.R;
import com.socks.androiddemo.ui.base.BaseActivity;
import com.socks.androiddemo.utils.logger.Logger;

/**
 * Notifycation的简单实用
 * 
 * @ClassName: NotifycationActivity
 * @author ZhaoKaiQiang
 * @date 2015-3-27 上午11:27:38
 */
public class NotifycationActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifycation);
	}

	public void click(View view) {

		switch (view.getId()) {
		case R.id.btn_one:
			Intent resultIntent = new Intent(context, OtherActivity.class);
			TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
			stackBuilder.addParentStack(OtherActivity.class);
			stackBuilder.addNextIntent(resultIntent);
			PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
					0, PendingIntent.FLAG_UPDATE_CURRENT);
			break;
		case R.id.btn_two: {
			Intent notifyIntent = new Intent(context, OtherActivity.class);
			notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_CLEAR_TASK);
			PendingIntent notifyPendingIntent = PendingIntent
					.getActivity(context, 0, notifyIntent,
							PendingIntent.FLAG_UPDATE_CURRENT);
		}
			break;
		case R.id.btn_three: {
			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
					context).setSmallIcon(R.mipmap.ic_launcher)
					.setContentTitle("SimpleNotification")
					.setContentText("This is the first notification.")
					.setAutoCancel(true);

			Intent notifyIntent = new Intent(context, OtherActivity.class);
			notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			PendingIntent notifyPendingIntent = PendingIntent
					.getActivity(context, 0, notifyIntent,
							PendingIntent.FLAG_UPDATE_CURRENT);
			mBuilder.setContentIntent(notifyPendingIntent);
			NotificationManager mNotifyMgr = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			mNotifyMgr.notify(0, mBuilder.build());
			Process.myPid();

			Logger.d("NotifycationActivity---------Process.myPid() = " + Process.myPid());
		}
			break;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Logger.d("onDestroy");
	}
}
