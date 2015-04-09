package com.thk.thkvisitor.activity;

import com.thk.thkvisitor.view.CustomProgressDialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;

/**
 * @author rongfangzhi
 * @version 2013-7-2
 */

public class BaseActivity extends Activity implements OnCancelListener {

	protected Context mContext;
	protected CustomProgressDialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mDialog = new CustomProgressDialog(mContext);

		mDialog.setCancelable(true);
		mDialog.setCanceledOnTouchOutside(false);

	}

	@Override
	public void onCancel(DialogInterface arg0) {
		// TODO Auto-generated method stub

	}

	public void hideDialog() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}

}
