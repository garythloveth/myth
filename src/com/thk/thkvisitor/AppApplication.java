package com.thk.thkvisitor;

import android.app.Application;
import android.util.Log;

import com.thk.thkvisitor.utils.Constans;

public class AppApplication extends Application {
	static AppApplication application;
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(Constans.TAG, "AppApplication onCreate!");
		application = this;
	}
	public static final AppApplication getApplication() {
		return application;
	}
}
