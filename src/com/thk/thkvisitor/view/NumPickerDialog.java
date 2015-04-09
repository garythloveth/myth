package com.thk.thkvisitor.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.thk.thkvisitor.R;

public class NumPickerDialog extends Dialog {
	String s;
	Context context;

	public NumPickerDialog(Context context, String s) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.s = s;
	}

	public NumPickerDialog(Context context, int theme,String s) {
		super(context, theme);
		this.context = context;
		this.s = s;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.num_picker_dialog);
		TextView tv = (TextView) findViewById(R.id.dialog_title);
		tv.setText(s);

	}
}
