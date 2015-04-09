package com.thk.thkvisitor.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;

import com.loopj.android.http.RequestParams;
import com.thk.thkvisitor.bean.UserToken;

/**
 * @author rongfangzhi
 * @version 2013-7-4 下午2:52:33
 */
public class XDUserInfo {

	private static XDUserInfo instance;
	private Context context;

	public static XDUserInfo getInstance(Context context) {
		if (instance == null) {
			synchronized (XDUserInfo.class) {
				instance = new XDUserInfo(context);
				return instance;
			}
		}
		return instance;
	}

	protected XDUserInfo(Context context) {
		this.context = context;
	}

	protected XDUserInfo() {

	}

	public void setUserInfo(UserToken userToken) {
		Editor editor = CommonUtil.getSharedPreferences(context).edit();
		editor.putString(Constans.KEY_USERNAME, userToken.getUserName());
		editor.putString(Constans.KEY_PASSWORD, userToken.getUserPassword());
		editor.commit();
	}

	public void Logout() {
//		Editor editor = CommonUtil.getSharedPreferences(context).edit();
//		editor.putString(Constans.KEY_USERNAME, "");
//		editor.putString(Constans.KEY_PASSWORD, "");
//		editor.commit();
		Editor editor = CommonUtil.getSharedPreferences(context).edit();
		editor.putString("loginstate", "");
		editor.commit();
	}

	public boolean isLogin() {
		// String userName = CommonUtil.getSharedPreferences(context).getString(
		// Constans.KEY_USERNAME, "");
		// String userPassword = CommonUtil.getSharedPreferences(context)
		// .getString(Constans.KEY_PASSWORD, "");
		String loginstate = CommonUtil.getSharedPreferences(context).getString(
				"loginstate", "");
		if (loginstate.length() > 0) {
			return true;
		}
		return false;
	}

	public UserToken getUserToken() {
		UserToken userToken = new UserToken();
		String userName = CommonUtil.getSharedPreferences(context).getString(
				Constans.KEY_USERNAME, "");
		String userPassword = CommonUtil.getSharedPreferences(context)
				.getString(Constans.KEY_PASSWORD, "");
		userToken.setUserName(userName);
		userToken.setUserPassword(userPassword);
		return userToken;
	}

	public void setLoginState(String s) {
		Editor editor = CommonUtil.getSharedPreferences(context).edit();
		editor.putString("loginstate", s);
		editor.commit();
	}

	public String getLoginState() {
		String loginstate = CommonUtil.getSharedPreferences(context).getString(
				"loginstate", "");
		return loginstate;
	}

}
