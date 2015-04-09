package com.thk.thkvisitor.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thk.thkvisitor.R;
import com.thk.thkvisitor.utils.Constans;
import com.thk.thkvisitor.utils.SharedPreferencesHelper;
import com.thk.thkvisitor.utils.XDUserInfo;
import com.thk.thkvisitor.view.DrawerView;

/**
 * 主页面
 * 
 * @author simon
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	private long mExitTime;

	protected SlidingMenu side_drawer;

	private LinearLayout mIvHeadBack;
	ImageView titleRight, order_menu;

	private TextView mTvHeadTitle;

	private RelativeLayout mRlMainVisitor;

	private RelativeLayout mRlMainMe;

	private RelativeLayout mRlMainApp;

	private RelativeLayout mRlMainSetting;

	// http://115.28.152.191:8080/demo

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d(Constans.TAG, "MainActivity onCreate!");

		if (SharedPreferencesHelper.getInstance()
				.getURLPreferences(MainActivity.this).equals("")) {
			SharedPreferencesHelper.getInstance().setURLPreferences(
					MainActivity.this, "http://192.168.0.107:8080/demo");
		}

		initView();

		initSlidingMenu();
	}

	private void initView() {
		mIvHeadBack = (LinearLayout) findViewById(R.id.iv_head_back);
		titleRight = (ImageView) mIvHeadBack.findViewById(R.id.head_left_img);
		titleRight.setBackgroundResource(R.drawable.duanxin);
		// mIvHeadBack.setOnClickListener(this);
		order_menu = (ImageView) findViewById(R.id.order_img);
		order_menu.setOnClickListener(this);
		mTvHeadTitle = (TextView) findViewById(R.id.tv_head_title);
		mTvHeadTitle.setText("访客系统");

		mRlMainVisitor = (RelativeLayout) findViewById(R.id.rl_main_visitor);
		mRlMainVisitor.setOnClickListener(this);
		mRlMainMe = (RelativeLayout) findViewById(R.id.rl_main_me);
		mRlMainMe.setOnClickListener(this);
		mRlMainApp = (RelativeLayout) findViewById(R.id.rl_main_app);
		mRlMainApp.setOnClickListener(this);
		mRlMainSetting = (RelativeLayout) findViewById(R.id.rl_main_setting);
		mRlMainSetting.setOnClickListener(this);

	}

	protected void initSlidingMenu() {
		side_drawer = new DrawerView(this).initSlidingMenu();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_head_back:

			break;
		case R.id.order_img:
			if (side_drawer.isMenuShowing()) {
				side_drawer.showContent();
			} else {
				side_drawer.showMenu();
			}
			break;
		case R.id.rl_main_visitor:
			if (XDUserInfo.getInstance(MainActivity.this).isLogin()) {
				startActivity(new Intent(MainActivity.this,
						VisitorManagerActivity.class));
			} else {
				startActivity(new Intent(MainActivity.this, LoginActivity.class));
			}

			break;
		case R.id.rl_main_me:
			Toast.makeText(MainActivity.this, "我的", Toast.LENGTH_SHORT).show();

			break;
		case R.id.rl_main_app:
			Toast.makeText(MainActivity.this, "应用", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_main_setting:
			startActivity(new Intent(MainActivity.this, SettingActivity.class));
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (side_drawer.isMenuShowing()
					|| side_drawer.isSecondaryMenuShowing()) {
				side_drawer.showContent();
			} else {
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "在按一次退出", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
					finish();
				}
			}
			return true;
		}
		// 拦截MENU按钮点击事件，让他无任何操作
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
