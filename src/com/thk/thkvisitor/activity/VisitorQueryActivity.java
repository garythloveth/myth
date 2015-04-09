package com.thk.thkvisitor.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thk.thkvisitor.R;
import com.thk.thkvisitor.model.VisitorInfoData;
import com.thk.thkvisitor.utils.CommonUtil;
import com.thk.thkvisitor.utils.Constans;
import com.thk.thkvisitor.utils.ThkhttpClient;
import com.thk.thkvisitor.view.CustomProgressDialog;
import com.thk.thkvisitor.view.DrawerView;
import com.thk.thkvisitor.weight.FirstItemView;

public class VisitorQueryActivity extends BaseActivity implements
		OnClickListener {

	private LinearLayout mIvHeadBack;

	private TextView mTvHeadTitle;

	private EditText mEtVisitorQuerySearch;

	private Button mBtnVisitorQuerySearch;

	private CustomProgressDialog mDialog;

	private ListView mLvQuerySearch;

	private QuerySearchLvAdapter mQuerySearchLvAdapter;

	protected SlidingMenu side_drawer;
	ArrayList<VisitorInfoData> arrayList = new ArrayList<VisitorInfoData>();
	private int commentCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visitor_query);
		initSlidingMenu();
		initView();

	}

	private void initView() {
		mIvHeadBack = (LinearLayout) findViewById(R.id.iv_head_back);
		mIvHeadBack.setOnClickListener(this);

		mTvHeadTitle = (TextView) findViewById(R.id.tv_head_title);
		mTvHeadTitle.setText("来访查询");
		findViewById(R.id.order_img).setOnClickListener(this);
		mEtVisitorQuerySearch = (EditText) findViewById(R.id.et_visitor_query_search);

		mBtnVisitorQuerySearch = (Button) findViewById(R.id.btn_visitor_query_search);
		mBtnVisitorQuerySearch.setOnClickListener(this);

		mLvQuerySearch = (ListView) findViewById(R.id.lv_query_search);

		mQuerySearchLvAdapter = new QuerySearchLvAdapter(
				VisitorQueryActivity.this);
		mLvQuerySearch.setAdapter(mQuerySearchLvAdapter);
	}

	protected void initSlidingMenu() {
		side_drawer = new DrawerView(this).initSlidingMenu();
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				commentCount = arrayList.size();
				// mQuerySearchLvAdapter.setVisitorInfoDatas(arrayList);
				// System.out.println(arrayList.get(0).getVisitToDoName());
				mQuerySearchLvAdapter.notifyDataSetChanged();
			}
		};
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
		case R.id.btn_visitor_query_search:

			if (CommonUtil.checkNetState(mContext)) {
				mDialog = new CustomProgressDialog(mContext);
				mDialog.setCancelable(true);
				mDialog.setCanceledOnTouchOutside(false);
				mDialog.show();
				RequestParams params = new RequestParams();
				params.put("serach", mEtVisitorQuerySearch.getText().toString());
				ThkhttpClient.post(Constans.VISITOR_SEARCH_URL, params,
						new AsyncHttpResponseHandler() {
							@Override
							public void onFailure(Throwable arg0, String arg1) {
								hideDialog();
								System.out.println("onFailure--" + arg1);
								super.onFailure(arg0, arg1);
							}

							@Override
							public void onSuccess(int arg0, String arg1) {
								System.out.println("onSuccess--" + arg1);
								hideDialog();
								JSONArray visitorInfoDataArray;
								try {
									visitorInfoDataArray = new JSONArray(arg1);
									if (visitorInfoDataArray.length() > 0) {
										for (int i = 0; i < visitorInfoDataArray
												.length(); i++) {
											if (i > 1) {
												if (i == 4) {
													continue;
												}
												JSONObject jsonObject = (JSONObject) visitorInfoDataArray
														.get(i);
												VisitorInfoData visitorInfoData = new VisitorInfoData();
												visitorInfoData
														.setVisitorTime(jsonObject
																.getString("visitorTime")); // 来访时间
												visitorInfoData
														.setVisitToDoName(jsonObject
																.getString("visitToDoName"));// 来访事由
												visitorInfoData
														.setVisitorNum(jsonObject
																.getInt("visitorNum")); // 来访人数
												visitorInfoData.setEmpName(jsonObject
														.getString("empName")); // 被访人姓名
												visitorInfoData.setMobileNo(jsonObject
														.getString("mobileNo")); // 被访人联系电话
												arrayList.add(visitorInfoData);

												System.out
														.println("========================");
											}
										}
									}

								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								handler.sendEmptyMessage(1);
								super.onSuccess(arg0, arg1);
							}
						});
			}

			break;
		default:
			break;
		}
	}
	public void hideDialog() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}
	/**
	 * ListView 的Adapter
	 * 
	 * @author simon
	 * 
	 */
	class QuerySearchLvAdapter extends BaseAdapter {

		private Context mContext;
		private final int FIRST_TYPE = 0;
		private final int OTHERS_TYPE = 1;

		// private ArrayList<VisitorInfoData> mArrayList;

		public QuerySearchLvAdapter(Context context) {
			this.mContext = context;
		}

		@Override
		public int getCount() {

			return arrayList.size() + 1;

		}

		@Override
		public Object getItem(int position) {
			return arrayList.get(position);
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(int position) {
			if (position == 0) {
				return FIRST_TYPE;
			} else {
				return OTHERS_TYPE;
			}
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			int type = getItemViewType(position);
			FirstItemView firstItemView = null;
			if (convertView == null) {
				if (type == 0) {
					firstItemView = new FirstItemView(mContext);
					convertView = firstItemView;
					convertView.setTag(type);
				} else {
					viewHolder = new ViewHolder();
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.lv_query_search_item, null);

					viewHolder.mTvVisitorTime = (TextView) convertView
							.findViewById(R.id.tv_visitor_time);
					viewHolder.mTvVisitorToDo = (TextView) convertView
							.findViewById(R.id.tv_visitor_todo);
					viewHolder.mTvVisitorNum = (TextView) convertView
							.findViewById(R.id.tv_visitor_num);
					viewHolder.mTvVisitoredName = (TextView) convertView
							.findViewById(R.id.tv_visitored_name);
					viewHolder.mTvVisitoredPhonesNo = (TextView) convertView
							.findViewById(R.id.tv_visitored_phones_no);

					convertView.setTag(viewHolder);
				}
			} else {
				if (type == 0) {
					firstItemView = (FirstItemView) convertView;
				} else {
					viewHolder = (ViewHolder) convertView.getTag();
				}

			}
			if (type == 0) {
				firstItemView.setCommentCount(commentCount);
			} else {
				VisitorInfoData queryData = arrayList.get(position - 1);
				viewHolder.mTvVisitorTime.setText(queryData.getVisitorTime());
				viewHolder.mTvVisitorToDo.setText(queryData.getVisitToDoName());
				viewHolder.mTvVisitorNum.setText(queryData.getVisitorNum()
						+ "人");
				viewHolder.mTvVisitoredName.setText(queryData.getEmpName());
				viewHolder.mTvVisitoredPhonesNo
						.setText(queryData.getMobileNo());
			}
			return convertView;
		}

	}

	class ViewHolder {
		TextView mTvVisitorTime;
		TextView mTvVisitorToDo;
		TextView mTvVisitorNum;
		TextView mTvVisitoredName;
		TextView mTvVisitoredPhonesNo;
	}
}
