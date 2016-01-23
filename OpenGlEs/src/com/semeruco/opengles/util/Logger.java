package com.semeruco.opengles.util;

import android.util.Log;

public class Logger {
	
	public static boolean LOG = true;
	
	public static void d(String tag, String msg)
	{
		if(LOG)
			Log.d(tag, msg);
	}
	
	public static void w(String tag, String msg){
		if(LOG)
			Log.w(tag, msg);
	}
	
	public static void e(String tag, String msg){
		if(LOG)
			Log.e(tag, msg);
	}

}
