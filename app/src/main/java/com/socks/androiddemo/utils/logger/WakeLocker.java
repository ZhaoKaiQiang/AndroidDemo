package com.socks.androiddemo.utils.logger;

import android.content.Context;
import android.os.PowerManager;

public abstract class WakeLocker {
	private static PowerManager.WakeLock wakeLock;

	public static void acquire(Context ctx) {
		if (wakeLock != null)
			wakeLock.release();

		PowerManager pm = (PowerManager) ctx
				.getSystemService(Context.POWER_SERVICE);
		// wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
		// PowerManager.ACQUIRE_CAUSES_WAKEUP |
		// PowerManager.ON_AFTER_RELEASE, "TAG");
		wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TAG");
		wakeLock.acquire();
	}

	public static void release() {

		synchronized (WakeLocker.class) {
			if (wakeLock != null) {
				try {
					wakeLock.release();
				} catch (Throwable th) {
					// ignoring this exception, probably wakeLock was already
				}
			}
			wakeLock = null;
		}

	}
}
