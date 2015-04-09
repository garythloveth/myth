package com.thk.thkvisitor.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.thk.thkvisitor.R;
import com.thk.thkvisitor.view.DrawerView;

public class ReSetPaswordActivity extends BaseActivity implements OnClickListener {
	protected SlidingMenu side_drawer;
	private TextView mTvHeadTitle;
	private LinearLayout mIvHeadBack;
	EditText reset_phone_number, reset_password_number,
			reset_new_password_number;
	TextView second_time_txt;
	ImageView reset_get_code_btn, reset_commit_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_password_layout);
		initviews();
		initSlidingMenu();
	}

	protected void initSlidingMenu() {
		side_drawer = new DrawerView(this).initSlidingMenu();
	}

	void initviews() {
		mIvHeadBack = (LinearLayout) findViewById(R.id.iv_head_back);
		mIvHeadBack.setOnClickListener(this);
		mTvHeadTitle = (TextView) findViewById(R.id.tv_head_title);
		mTvHeadTitle.setText("登录");

		findViewById(R.id.order_img).setOnClickListener(this);
		reset_phone_number = (EditText) findViewById(R.id.reset_phone_number);
		reset_password_number = (EditText) findViewById(R.id.reset_password_number);
		reset_new_password_number = (EditText) findViewById(R.id.reset_new_password_number);
		second_time_txt = (TextView) findViewById(R.id.second_time_txt);
		reset_commit_btn = (ImageView) findViewById(R.id.reset_commit_btn);
		reset_get_code_btn = (ImageView) findViewById(R.id.reset_get_code_btn);

		reset_commit_btn.setOnClickListener(this);
		reset_get_code_btn.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.iv_head_back:
			this.finish();
			break;
		case R.id.order_img:
			if (side_drawer.isMenuShowing()) {
				side_drawer.showContent();
			} else {
				side_drawer.showMenu();
			}
			break;
		case R.id.reset_get_code_btn:

			break;
		case R.id.reset_commit_btn:

			break;

		default:
			break;
		}

	}
}
