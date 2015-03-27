package com.socks.androiddemo.ui;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;

import com.socks.androiddemo.R;
import com.socks.androiddemo.base.BaseActivity;

public class NotifycationActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifycation);
	}

	public void click(View view) {

		switch (view.getId()) {
			case R.id.btn_one: {
				NotificationCompat.Builder mBuilder =
						new NotificationCompat.Builder(context)
								.setSmallIcon(R.mipmap.ic_launcher)
								.setContentTitle("BTN-ONE")
								.setContentText("First Notification")
								.setAutoCancel(true);

				Intent resultIntent = new Intent(context, OtherActivity.class);

				PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
				resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mBuilder.setContentIntent(resultPendingIntent);
				NotificationManager mNotifyMgr =
						(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
				mNotifyMgr.notify(0, mBuilder.build());
			}
			break;
			case R.id.btn_two: {

				NotificationCompat.Builder mBuilder =
						new NotificationCompat.Builder(this)
								.setSmallIcon(R.mipmap.ic_launcher)
								.setContentTitle("BTN-TWO")
								.setContentText("Hello World!").setAutoCancel(true)
								.setStyle(new NotificationCompat.BigPictureStyle()
										.setBigContentTitle("BigContentTitle")
										.setSummaryText("SummaryTextSummaryText")
										.bigPicture(BitmapFactory.decodeResource(getResources(),
												R.mipmap.ic_launcher)));

				Intent resultIntent = new Intent(this, OtherActivity.class);
				resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
				stackBuilder.addParentStack(OtherActivity.class);
				stackBuilder.addNextIntent(resultIntent);

				PendingIntent resultPendingIntent =
						stackBuilder.getPendingIntent(
								0, PendingIntent.FLAG_UPDATE_CURRENT
						);

				mBuilder.setContentIntent(resultPendingIntent);
				NotificationManager mNotificationManager =
						(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(1, mBuilder.build());
			}
			break;
			case R.id.btn_three: {
				NotificationCompat.Builder mBuilder =
						new NotificationCompat.Builder(this)
								.setSmallIcon(R.mipmap.ic_launcher)
								.setContentTitle("MultyLineStyle")
								.setContentText("Hello World!").setAutoCancel(true);

				NotificationCompat.InboxStyle inboxStyle =
						new NotificationCompat.InboxStyle();
				String[] events = new String[5];
				inboxStyle.setBigContentTitle("Event tracker details:");
				for (int i = 0; i < events.length; i++) {
					inboxStyle.addLine("Test It !");
				}
				mBuilder.setStyle(inboxStyle);
				Intent resultIntent = new Intent(context, OtherActivity.class);

				PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
				resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mBuilder.setContentIntent(resultPendingIntent);

				NotificationCompat.Action action = new NotificationCompat.Action.Builder( R.mipmap
						.ic_launcher, "click",resultPendingIntent).build();

				mBuilder.addAction(action);

				NotificationManager mNotificationManager =
						(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(2, mBuilder.build());
			}
			break;
		}

	}

}
