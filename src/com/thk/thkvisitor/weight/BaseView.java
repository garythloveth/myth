package com.thk.thkvisitor.weight;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.thk.thkvisitor.R;
import com.thk.thkvisitor.activity.LoginActivity;
import com.thk.thkvisitor.utils.CommonUtil;

public class BaseView extends FrameLayout implements View.OnClickListener {

	protected AQuery mAq;

	protected Activity mActivity;

	protected Context mContext;

	protected View mView;

	protected LayoutInflater mInflater;

	protected ScaleAnimation scaleAnimation;

	public BaseView(Context context) {
		super(context);
		init();
	}

	public BaseView(Context context, AttributeSet attrs) {

		super(context, attrs);
		init();
	}

	private void init() {
		scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		scaleAnimation.setDuration(500);
	}

	public void setContentView(int layoutId) {
		mContext = getContext();
		mAq = new AQuery(mContext);
		mActivity = (Activity) mContext;
		mInflater = LayoutInflater.from(mContext);
		mView = mInflater.inflate(layoutId, null);
		addView(mView);
	}

	public void onClick(View v) {

	}

	public View fid(int id) {
		return findViewById(id);
	}

	protected void openActivity(Class<? extends Activity> activityClazz) {
		openActivity(activityClazz, null);
	}

	protected void goToLoginActivityinVisit() {
		// Intent intent = new Intent(mActivity,LoginActivity.class);
		// mActivity.startActivity(intent);
		// UICore.getInstance().removeUserInfo();
		openActivity(LoginActivity.class);
		CommonUtil.showToast(mContext, "请先登录!");
	}

	protected void openActivity(Class<? extends Activity> activityClazz,
			Bundle bundle) {
		Intent intent = new Intent(mActivity, activityClazz);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		mActivity.startActivity(intent);
		mActivity.finish();
	}

	protected void closeDialog(ProgressDialog dialog) {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
			dialog = null;
		}
	}
}
