package com.thk.thkvisitor.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
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
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thk.thkvisitor.R;
import com.thk.thkvisitor.utils.CommonUtil;
import com.thk.thkvisitor.utils.Constans;
import com.thk.thkvisitor.utils.ThkhttpClient;
import com.thk.thkvisitor.utils.XDUserInfo;
import com.thk.thkvisitor.view.CustomProgressDialog;
import com.thk.thkvisitor.view.DrawerView;

public class LoginActivity extends BaseActivity implements OnClickListener {

	EditText phone_number, password_number;
	TextView forget_password_txt, register_naw_txt;
	protected AQuery mAq;
	ImageView commit_btn;
	protected SlidingMenu side_drawer;
	private TextView mTvHeadTitle;
	private LinearLayout mIvHeadBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
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
		mTvHeadTitle.setText("登录");

		findViewById(R.id.order_img).setOnClickListener(this);
		phone_number = (EditText) findViewById(R.id.phone_number);
		password_number = (EditText) findViewById(R.id.password_number);
		forget_password_txt = (TextView) findViewById(R.id.forget_password_txt);
		register_naw_txt = (TextView) findViewById(R.id.register_naw_txt);
		commit_btn = (ImageView) findViewById(R.id.commit_btn);

		forget_password_txt.setOnClickListener(this);

		register_naw_txt.setOnClickListener(this);
		commit_btn.setOnClickListener(this);
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
		case R.id.forget_password_txt:
			startActivity(new Intent(LoginActivity.this,
					ReSetPaswordActivity.class));
			break;
		case R.id.register_naw_txt:
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

			break;
		case R.id.commit_btn:
			if (CommonUtil.checkNetState(mContext)) {

				mDialog.show();

				RequestParams params = new RequestParams();
				params.put("username", phone_number.getText().toString());
				params.put("password", password_number.getText().toString());

				ThkhttpClient.post(Constans.LOGIN_URL, params,
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
								try {
									JSONObject jsonObject = new JSONObject(arg1);
									String code = jsonObject.getString("code");
									XDUserInfo.getInstance(LoginActivity.this)
											.setLoginState(code);
									System.out.println("state====>"
											+ XDUserInfo.getInstance(
													LoginActivity.this)
													.getLoginState());
									startActivity(new Intent(
											LoginActivity.this,
											MainActivity.class));
									Toast.makeText(LoginActivity.this, "登录成功",
											Toast.LENGTH_SHORT).show();

									finish();
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// UserToken userToken = new UserToken();
								// userToken.setUserName(userName);
								// userToken.setUserPassword(userPassword);
								//
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
