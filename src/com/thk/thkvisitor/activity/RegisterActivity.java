package com.thk.thkvisitor.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thk.thkvisitor.R;
import com.thk.thkvisitor.utils.CommonUtil;
import com.thk.thkvisitor.utils.Constans;
import com.thk.thkvisitor.utils.ThkhttpClient;
import com.thk.thkvisitor.view.DrawerView;

public class RegisterActivity extends BaseActivity implements OnClickListener {

	protected SlidingMenu side_drawer;
	private TextView mTvHeadTitle;
	private LinearLayout mIvHeadBack;
	EditText register_name_edit, register_unit_edit, register_phone_edit,
			register_code_edit;
	ImageView get_phone_code_btn, register_commit_btn;
	protected AQuery mAq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		mAq = new AQuery(this);
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
		mTvHeadTitle.setText("注册");

		findViewById(R.id.order_img).setOnClickListener(this);
		register_name_edit = (EditText) findViewById(R.id.register_name_edit);
		register_unit_edit = (EditText) findViewById(R.id.register_unit_edit);
		register_phone_edit = (EditText) findViewById(R.id.register_phone_edit);
		register_code_edit = (EditText) findViewById(R.id.register_code_edit);

		get_phone_code_btn = (ImageView) findViewById(R.id.get_phone_code_btn);
		register_commit_btn = (ImageView) findViewById(R.id.register_commit_btn);

		get_phone_code_btn.setOnClickListener(this);
		register_commit_btn.setOnClickListener(this);
	}

	@SuppressLint("NewApi")
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
		case R.id.get_phone_code_btn:
			System.out.println("获取手机验证码");

			break;
		case R.id.register_commit_btn:
			
			if (register_name_edit.getText().toString().isEmpty()) {
				Toast.makeText(mContext, "用户名不能为空",
						Toast.LENGTH_LONG).show();
				break;
			} else if (register_unit_edit.getText().toString().isEmpty()) {
				Toast.makeText(mContext, "密码不能为空",
						Toast.LENGTH_LONG).show();
				break;
			} else if (register_phone_edit.getText().toString().isEmpty()) {
				Toast.makeText(mContext, "手机号不能为空",
						Toast.LENGTH_LONG).show();
				break;
			} else if (register_code_edit.getText().toString().isEmpty()) {
				Toast.makeText(mContext, "邮箱不能为空",
						Toast.LENGTH_LONG).show();
				break;
			}
			
			System.out.println("提交");
			if (CommonUtil.checkNetState(mContext)) {
				mDialog.show();
				RequestParams params = new RequestParams();
				params.put("username", register_name_edit.getText().toString());
				params.put("password", register_unit_edit.getText().toString());
				params.put("mobile", register_phone_edit.getText().toString());
				params.put("email", register_code_edit.getText().toString());

				ThkhttpClient.post(Constans.REGISTER_URL, params,
						new AsyncHttpResponseHandler() {
							@Override
							public void onFailure(Throwable arg0, String arg1) {
								hideDialog();
								System.out.println("onFailure--" + arg1);
								super.onFailure(arg0, arg1);
							}

							@Override
							public void onSuccess(int arg0, String arg1) {
								hideDialog();
								System.out.println("onSuccess--" + arg1);
								finish();
								super.onSuccess(arg0, arg1);
							}
						});
			}

			break;

		default:
			break;
		}

	}

}