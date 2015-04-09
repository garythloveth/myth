package com.thk.thkvisitor.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.thk.thkvisitor.R;
import com.thk.thkvisitor.activity.LoginActivity;
import com.thk.thkvisitor.activity.MainActivity;
import com.thk.thkvisitor.activity.VisitorManagerActivity;
import com.thk.thkvisitor.utils.XDUserInfo;

/**
 * 自定义SlidingMenu 测拉菜单类
 * */
public class DrawerView implements OnClickListener {

	private final Activity activity;
	SlidingMenu localSlidingMenu;
	ImageView orderMenuBack;
	/**
	 * 园区黄页
	 */
	private RelativeLayout mRlYellowPages;
	/**
	 * 园区通知
	 */
	private RelativeLayout mRlNotice;
	/**
	 * 常用电话
	 */
	private RelativeLayout mRlPhone;
	/**
	 * 周边
	 */
	private RelativeLayout mRlSurrounding;
	/**
	 * 来访预约
	 */
	private RelativeLayout mRlVisitAppointments;
	/**
	 * 来访查询
	 */
	private RelativeLayout mRlVisitQuery;
	/**
	 * 接访提醒
	 */
	private RelativeLayout mRlReceiversReminder;
	/**
	 * 媒体控制
	 */
	private RelativeLayout mRlMediaControl;
	/**
	 * 登陆\退出
	 */
	private RelativeLayout mRlLoginLogout;
	/**
	 * 保修
	 */
	private RelativeLayout mRlWarranty;
	/**
	 * 反馈
	 */
	private RelativeLayout mRlFeedback;

	public DrawerView(Activity activity) {
		this.activity = activity;
	}

	public SlidingMenu initSlidingMenu() {
		localSlidingMenu = new SlidingMenu(activity);
		localSlidingMenu.setMode(SlidingMenu.RIGHT);// 设置左右滑菜单
		localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);// 设置要使菜单滑动，触碰屏幕的范围
		// localSlidingMenu.setTouchModeBehind(SlidingMenu.RIGHT);
		// localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片的宽度
		// localSlidingMenu.setShadowDrawable(R.drawable.shadow);//设置阴影图片
		localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		localSlidingMenu.setFadeDegree(0.35F);// SlidingMenu滑动时的渐变程度
		localSlidingMenu.attachToActivity(activity, SlidingMenu.LEFT);// 使SlidingMenu附加在Activity右边
		// localSlidingMenu.setBehindWidthRes(R.dimen.left_drawer_avatar_size);//设置SlidingMenu菜单的宽度
		localSlidingMenu.setMenu(R.layout.left_drawer_fragment);// 设置menu的布局文件
		// localSlidingMenu.toggle();//动态判断自动关闭或开启SlidingMenu

		// localSlidingMenu.setSecondaryMenu(R.layout.profile_drawer_right);
		// localSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
		// localSlidingMenu.setOnOpenedListener(new
		// SlidingMenu.OnOpenedListener() {
		// public void onOpened() {
		//
		// }
		// });

		initView();
		return localSlidingMenu;
	}

	private void initView() {
		orderMenuBack = (ImageView) localSlidingMenu
				.findViewById(R.id.order_title_img);
		mRlYellowPages = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.rl_yellow_pages);
		mRlNotice = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.rl_notice);
		mRlPhone = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.rl_phone);
		mRlSurrounding = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.rl_surrounding);
		mRlVisitAppointments = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.rl_visit_appointments);
		mRlVisitQuery = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.rl_visit_query);
		mRlReceiversReminder = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.rl_receivers_reminder);
		mRlMediaControl = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.rl_media_control);
		mRlLoginLogout = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.rl_login_logout);
		mRlWarranty = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.rl_warranty);
		// mRlFeedback = (RelativeLayout)
		// localSlidingMenu.findViewById(R.id.rl_feedback);
		orderMenuBack.setOnClickListener(this);
		mRlYellowPages.setOnClickListener(this);
		mRlNotice.setOnClickListener(this);
		mRlPhone.setOnClickListener(this);
		mRlSurrounding.setOnClickListener(this);
		mRlVisitAppointments.setOnClickListener(this);
		mRlVisitQuery.setOnClickListener(this);
		mRlReceiversReminder.setOnClickListener(this);
		mRlMediaControl.setOnClickListener(this);
		mRlLoginLogout.setOnClickListener(this);
		mRlWarranty.setOnClickListener(this);
		// mRlFeedback.setOnClickListener(this);

		// night_mode_btn =
		// (SwitchButton)localSlidingMenu.findViewById(R.id.night_mode_btn);
		// night_mode_text =
		// (TextView)localSlidingMenu.findViewById(R.id.night_mode_text);
		// night_mode_btn.setOnCheckedChangeListener(new
		// OnCheckedChangeListener() {
		//
		// @Override
		// public void onCheckedChanged(CompoundButton buttonView, boolean
		// isChecked) {
		// // TODO Auto-generated method stub
		// if(isChecked){
		// night_mode_text.setText(activity.getResources().getString(R.string.action_night_mode));
		// }else{
		// night_mode_text.setText(activity.getResources().getString(R.string.action_day_mode));
		// }
		// }
		// });
		// night_mode_btn.setChecked(false);
		// if(night_mode_btn.isChecked()){
		// night_mode_text.setText(activity.getResources().getString(R.string.action_night_mode));
		// }else{
		// night_mode_text.setText(activity.getResources().getString(R.string.action_day_mode));
		// }

		// setting_btn
		// =(RelativeLayout)localSlidingMenu.findViewById(R.id.setting_btn);
		// setting_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.order_title_img:
			localSlidingMenu.showContent();
			break;
		case R.id.rl_yellow_pages:
			Toast.makeText(activity, "园区黄页", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_notice:
			Toast.makeText(activity, "园区通知", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_phone:
			Toast.makeText(activity, "常用电话", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_surrounding:
			Toast.makeText(activity, "周边", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_visit_appointments:
			Toast.makeText(activity, "来访预约", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_visit_query:
			Toast.makeText(activity, "来访查询", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_receivers_reminder:
			Toast.makeText(activity, "接访提醒", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_media_control:
			Toast.makeText(activity, "媒体控制", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rl_login_logout:
			if (XDUserInfo.getInstance(activity).isLogin()) {
				XDUserInfo.getInstance(activity).Logout();

				Toast.makeText(activity, "注销登录", Toast.LENGTH_SHORT).show();
			} else {
				activity.startActivity(new Intent(activity, LoginActivity.class));
			}
			localSlidingMenu.showContent();
			break;
		case R.id.rl_warranty:
			Toast.makeText(activity, "保修", Toast.LENGTH_SHORT).show();
			break;
		// case R.id.rl_feedback:
		// Toast.makeText(activity, "反馈", Toast.LENGTH_SHORT).show();
		// break;
		default:
			break;
		}
	}
}
