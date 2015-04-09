package com.thk.thkvisitor.activity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.thk.thkvisitor.R;
import com.thk.thkvisitor.view.DrawerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 来访管理界面
 * 
 * @author simon
 * 
 */
public class VisitorManagerActivity extends BaseActivity implements OnClickListener {

	private LinearLayout mIvHeadBack;

	private TextView mTvHeadTitle;

	private LinearLayout mLlVisitorManagerAppointment;

	private LinearLayout mLlVisitorManagerRemind;

	private LinearLayout mLlVisitorManagerQuery;

	protected SlidingMenu side_drawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visitor_manager);
		initSlidingMenu();
		initView();
	}

	protected void initSlidingMenu() {
		side_drawer = new DrawerView(this).initSlidingMenu();
	}

	private void initView() {
		mIvHeadBack = (LinearLayout) findViewById(R.id.iv_head_back);
		mIvHeadBack.setOnClickListener(this);

		mTvHeadTitle = (TextView) findViewById(R.id.tv_head_title);
		mTvHeadTitle.setText("来访管理");
		findViewById(R.id.order_img).setOnClickListener(this);
		mLlVisitorManagerAppointment = (LinearLayout) findViewById(R.id.ll_visitor_manager_appointment);
		mLlVisitorManagerRemind = (LinearLayout) findViewById(R.id.ll_visitor_manager_remind);
		mLlVisitorManagerQuery = (LinearLayout) findViewById(R.id.ll_visitor_manager_query);
		mLlVisitorManagerAppointment.setOnClickListener(this);
		mLlVisitorManagerRemind.setOnClickListener(this);
		mLlVisitorManagerQuery.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.order_img:
			if (side_drawer.isMenuShowing()) {
				side_drawer.showContent();
			} else {
				side_drawer.showMenu();
			}
			break;
		case R.id.iv_head_back:
			this.finish();
			break;
		case R.id.ll_visitor_manager_appointment:
			startActivity(new Intent(VisitorManagerActivity.this,
					VisitorAppointmentActivity.class));
			break;
		case R.id.ll_visitor_manager_remind:
			Toast.makeText(VisitorManagerActivity.this, "来访提醒",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.ll_visitor_manager_query:
			startActivity(new Intent(VisitorManagerActivity.this,
					VisitorQueryActivity.class));
			break;
		default:
			break;
		}
	}
}
