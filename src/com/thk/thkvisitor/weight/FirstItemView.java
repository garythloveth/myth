package com.thk.thkvisitor.weight;

import android.content.Context;
import android.widget.TextView;

import com.thk.thkvisitor.R;

public class FirstItemView extends BaseView {
	private TextView textView;

	public FirstItemView(Context context) {
		super(context);
		setupViews();
	}

	private void setupViews() {
		setContentView(R.layout.query_count_layout);
		textView = (TextView) findViewById(R.id.query_count);
	}

	public void setCommentCount(int commentCount) {

		textView.setText(commentCount+"条");
	}


}
