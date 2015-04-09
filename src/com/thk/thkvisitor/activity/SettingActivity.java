package com.thk.thkvisitor.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.thk.thkvisitor.R;
import com.thk.thkvisitor.utils.SharedPreferencesHelper;
import com.thk.thkvisitor.view.DrawerView;

public class SettingActivity extends BaseActivity implements OnClickListener {

	private LinearLayout mIvHeadBack;

	private TextView mTvHeadTitle;

	private EditText mEtUrl;

	private Button mBtnSubmit;
	protected SlidingMenu side_drawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		initView();
		initSlidingMenu();
	}

	protected void initSlidingMenu() {
		side_drawer = new DrawerView(this).initSlidingMenu();
	}

	private void initView() {
		mIvHeadBack = (LinearLayout) findViewById(R.id.iv_head_back);
		mIvHeadBack.setOnClickListener(this);
		findViewById(R.id.order_img).setOnClickListener(this);
		mTvHeadTitle = (TextView) findViewById(R.id.tv_head_title);
		mTvHeadTitle.setText("设置");

		mEtUrl = (EditText) findViewById(R.id.et_setting);
		mEtUrl.setHint(SharedPreferencesHelper.getInstance().getURLPreferences(
				SettingActivity.this));
		mBtnSubmit = (Button) findViewById(R.id.btn_setting_submit);
		mBtnSubmit.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
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
		case R.id.btn_setting_submit:
			SharedPreferencesHelper.getInstance().setURLPreferences(
					SettingActivity.this, mEtUrl.getText().toString());
			Toast.makeText(SettingActivity.this, "设置成功", Toast.LENGTH_LONG)
					.show();
			break;

		default:
			break;
		}
	}
}
