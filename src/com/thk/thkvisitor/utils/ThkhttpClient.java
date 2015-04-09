package com.thk.thkvisitor.utils;

// Static wrapper library around AsyncHttpClient

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thk.thkvisitor.AppApplication;

public class ThkhttpClient {
	private static final String BASE_URL = Constans.BASE_IP_URL;

	private static AsyncHttpClient client = new AsyncHttpClient();
	private static Context context = AppApplication.getApplication();

	public static void get(String api, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {

		String absoluteUrl = getAbsoluteUrl(api);

		client.get(absoluteUrl, params, responseHandler);
	}

	public static void post(String api, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {

		client.post(getAbsoluteUrl(api), params, responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

}