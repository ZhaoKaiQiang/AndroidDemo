package com.socks.androiddemo.test;

import android.test.AndroidTestCase;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * Created by zhaokaiqiang on 15/3/29.
 */
public class TestClass extends AndroidTestCase {

	public void testCompress() throws IOException {
		Log.d("TAG", compress("123456789123"));
	}

	public static String compress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes());
		gzip.close();
		return out.toString("UTF-8");
	}


}

