package com.thk.thkvisitor.model;

import java.util.HashMap;
import com.thk.thkvisitor.parser.BaseParser;
import android.content.Context;

/**
 * 
 * @author simon
 *
 */
public class RequestModel {

	public String requestUrl;

	public Context context;

	public HashMap<String, Object> params;

	public BaseParser<?> baseParser;

	/**
	 * @param requestUrl
	 * @param context
	 * @param params
	 * @param baseParser
	 */
	public RequestModel(String requestUrl, Context context, HashMap<String, Object> params,
			BaseParser<?> baseParser) {
		super();
		this.requestUrl = requestUrl;
		this.context = context;
		this.params = params;
		this.baseParser = baseParser;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public HashMap<String, Object> getParams() {
		return params;
	}

	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}

	public BaseParser<?> getBaseParser() {
		return baseParser;
	}

	public void setBaseParser(BaseParser<?> baseParser) {
		this.baseParser = baseParser;
	}
}
