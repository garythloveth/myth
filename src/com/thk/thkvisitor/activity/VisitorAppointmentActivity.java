package com.thk.thkvisitor.activity;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thk.thkvisitor.R;
import com.thk.thkvisitor.utils.CommonUtil;
import com.thk.thkvisitor.utils.Constans;
import com.thk.thkvisitor.utils.ThkhttpClient;
import com.thk.thkvisitor.view.CustomProgressDialog;
import com.thk.thkvisitor.view.DrawerView;
import com.thk.thkvisitor.view.NumPickerDialog;

/**
 * 来访预约界面
 * 
 * @author simon
 * 
 */
public class VisitorAppointmentActivity extends BaseActivity implements
		OnClickListener {

	private LinearLayout mIvHeadBack;

	private TextView mTvHeadTitle;

	/** 来访时间 */
	private TextView mEtVisitorTime;// et_visitor_time grdName
	/** 来访事由 */
	private TextView mEtVisitorToDo;// et_visitor_todo visitToDoName
	/** 来访人数 */
	private TextView mEtVisitorNum;// et_visitor_num visitorNum

	/** 被访人姓名 */
	private TextView mEtVisitoredName;// et_visitored_name; empName （非空）
	/** 被访人电话 */
	private TextView mEtVisitoredPhonesNo;// et_visitored_phones_no; mobileNo
	/** 被访人单位 */
	private TextView mEtVisitoredCompany;// et_visitored_company; titName
	/** 被访人部门 */
	private TextView mEtVisitoredDpt;// et_visitored_dpt; dptName（非空）
	/** 被访人房号 */
	private TextView mEtVisitoredRoomNo;// et_visitored_room_no; officeRoom
	/** 被访人编号--内部员工编号 */
	private TextView mEtVisitoredNo;// et_visitored_no; empNo（非空）

	/** 访客姓名 */
	private TextView mEtVisitorsName;// et_visitors_name; visitorName(非空)
	/** 访客电话 */
	private TextView mEtVisitorsPhonesNo;// et_visitors_phones_no; visitorTelNo
	/** 访客证件号码 */
	private TextView mEtVisitorsCardNo;// et_visitors_card_no; visitorIDNo(非空)
	/** 访客单位 */
	private TextView mEtVisitorsCompany;// et_visitors_company; companyName
	/** 访客车牌号码 */
	private TextView mEtVisitorsCarNo;// et_visitors_car_no; vehicleNo

	View visitor_time_ly, visitor_todo_ly, visitor_num_ly;

	View visitored_name_ly, visitored_phones_no_ly, visitored_company_ly,
			visitored_dpt_ly, visitored_room_no_ly, visitored_no_ly;

	View visitors_name_ly, visitors_phones_no_ly, visitors_card_no_ly,
			visitors_company_ly, visitors_car_no_ly;

	private Button mBtnVisitorSubmit;

	private CustomProgressDialog mDialog;
	protected SlidingMenu side_drawer;
	int i = 0;// 访问人数

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visitor_appointment);
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
		mTvHeadTitle.setText("来访预约");

		visitor_time_ly = findViewById(R.id.visitor_time_ly);
		visitor_todo_ly = findViewById(R.id.visitor_todo_ly);
		visitor_num_ly = findViewById(R.id.visitor_num_ly);
		mEtVisitorTime = (TextView) findViewById(R.id.et_visitor_time);
		mEtVisitorToDo = (TextView) findViewById(R.id.et_visitor_todo);
		mEtVisitorNum = (TextView) findViewById(R.id.et_visitor_num);
		visitor_time_ly.setOnClickListener(this);
		visitor_todo_ly.setOnClickListener(this);
		visitor_num_ly.setOnClickListener(this);

		visitored_name_ly = findViewById(R.id.visitored_name_ly);
		visitored_phones_no_ly = findViewById(R.id.visitored_phones_no_ly);
		visitored_company_ly = findViewById(R.id.visitored_company_ly);
		visitored_dpt_ly = findViewById(R.id.visitored_dpt_ly);
		visitored_room_no_ly = findViewById(R.id.visitored_room_no_ly);
		visitored_no_ly = findViewById(R.id.visitored_no_ly);
		mEtVisitoredName = (TextView) findViewById(R.id.et_visitored_name);
		mEtVisitoredPhonesNo = (TextView) findViewById(R.id.et_visitored_phones_no);
		mEtVisitoredCompany = (TextView) findViewById(R.id.et_visitored_company);
		mEtVisitoredDpt = (TextView) findViewById(R.id.et_visitored_dpt);
		mEtVisitoredRoomNo = (TextView) findViewById(R.id.et_visitored_room_no);
		mEtVisitoredNo = (TextView) findViewById(R.id.et_visitored_no);
		visitored_name_ly.setOnClickListener(this);
		visitored_phones_no_ly.setOnClickListener(this);
		visitored_company_ly.setOnClickListener(this);
		visitored_dpt_ly.setOnClickListener(this);
		visitored_room_no_ly.setOnClickListener(this);
		visitored_no_ly.setOnClickListener(this);

		visitors_name_ly = findViewById(R.id.visitors_name_ly);
		visitors_phones_no_ly = findViewById(R.id.visitors_phones_no_ly);
		visitors_card_no_ly = findViewById(R.id.visitors_card_no_ly);
		visitors_company_ly = findViewById(R.id.visitors_company_ly);
		visitors_car_no_ly = findViewById(R.id.visitors_car_no_ly);
		mEtVisitorsName = (TextView) findViewById(R.id.et_visitors_name);
		mEtVisitorsPhonesNo = (TextView) findViewById(R.id.et_visitors_phones_no);
		mEtVisitorsCardNo = (TextView) findViewById(R.id.et_visitors_card_no);
		mEtVisitorsCompany = (TextView) findViewById(R.id.et_visitors_company);
		mEtVisitorsCarNo = (TextView) findViewById(R.id.et_visitors_car_no);
		visitors_name_ly.setOnClickListener(this);
		visitors_phones_no_ly.setOnClickListener(this);
		visitors_card_no_ly.setOnClickListener(this);
		visitors_company_ly.setOnClickListener(this);
		visitors_car_no_ly.setOnClickListener(this);

		mBtnVisitorSubmit = (Button) findViewById(R.id.btn_viistor_submit);
		mBtnVisitorSubmit.setOnClickListener(this);
	}

	@SuppressLint("NewApi")
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

		case R.id.visitor_time_ly:

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			View view = View.inflate(this, R.layout.date_time_dialog, null);
			final DatePicker datePicker = (DatePicker) view
					.findViewById(R.id.date_picker);
			final TimePicker timePicker = (android.widget.TimePicker) view
					.findViewById(R.id.time_picker);
			builder.setView(view);

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					cal.get(Calendar.DAY_OF_MONTH), null);

			timePicker.setIs24HourView(true);
			timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
			timePicker.setCurrentMinute(Calendar.MINUTE);

			// final int inType = mEtVisitorTime.getInputType();
			// mEtVisitorTime.setInputType(InputType.TYPE_NULL);
			// mEtVisitorTime.setInputType(inType);
			// mEtVisitorTime.setSelection(mEtVisitorTime.getText().length());
			builder.setTitle("设定预约时间");
			builder.setPositiveButton("确  定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							StringBuffer sb = new StringBuffer();
							sb.append(String.format("%d-%02d-%02d",
									datePicker.getYear(),
									datePicker.getMonth() + 1,
									datePicker.getDayOfMonth()));
							sb.append(" ");
							sb.append(timePicker.getCurrentHour()).append(":")
									.append(timePicker.getCurrentMinute());
							System.out.println("时间" + sb);
							mEtVisitorTime.setText(sb);
							dialog.cancel();
						}
					});
			Dialog dialog = builder.create();
			dialog.show();

			break;
		case R.id.visitor_todo_ly:
			AlertDialog.Builder builder_todo = new AlertDialog.Builder(this);
			builder_todo.setTitle("来访事由");
			final String[] todo = new String[] { "考察", "洽谈", "娱乐", "赴约" };
			builder_todo.setItems(todo, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface arg0, int arg1) {
					mEtVisitorToDo.setText(todo[arg1]);
				}
			});
			builder_todo.create().show();
			break;
		case R.id.visitor_num_ly:
			final NumPickerDialog numDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "访问人数");
			// // 设置它的ContentView
			numDialog.setContentView(R.layout.num_picker_dialog);
			numDialog.show();
			final EditText editText = (EditText) numDialog
					.findViewById(R.id.numberedit);

			Button numBtn = (Button) numDialog.findViewById(R.id.dialog_sure);
			numBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					try {
						i = Integer.parseInt(editText.getText().toString());
						mEtVisitorNum.setText("" + i);
					} catch (Exception e) {
						Toast.makeText(VisitorAppointmentActivity.this,
								"来访人数格式异常,请重新填写", Toast.LENGTH_LONG).show();
					}

					numDialog.dismiss();
				}
			});
			break;

		case R.id.visitored_name_ly:
			final NumPickerDialog visitoredNameDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "被访人姓名");
			// // 设置它的ContentView
			visitoredNameDialog.setContentView(R.layout.num_picker_dialog);
			visitoredNameDialog.show();
			final EditText editvVisitoredName = (EditText) visitoredNameDialog
					.findViewById(R.id.numberedit);

			Button VisitoredNamBtn = (Button) visitoredNameDialog
					.findViewById(R.id.dialog_sure);
			VisitoredNamBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mEtVisitoredName.setText(editvVisitoredName.getText()
							.toString());
					visitoredNameDialog.dismiss();
				}
			});
			break;
		case R.id.visitored_phones_no_ly:
			final NumPickerDialog visitored_phonesDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "被访人电话");
			// // 设置它的ContentView
			visitored_phonesDialog.setContentView(R.layout.num_picker_dialog);
			visitored_phonesDialog.show();
			final EditText visitored_phonesName = (EditText) visitored_phonesDialog
					.findViewById(R.id.numberedit);

			Button visitored_phonesBtn = (Button) visitored_phonesDialog
					.findViewById(R.id.dialog_sure);
			visitored_phonesBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mEtVisitoredPhonesNo.setText(visitored_phonesName.getText()
							.toString());
					visitored_phonesDialog.dismiss();
				}
			});
			break;

		case R.id.visitored_company_ly:
			final NumPickerDialog visitored_companyDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "被访人单位");
			// // 设置它的ContentView
			visitored_companyDialog.setContentView(R.layout.num_picker_dialog);
			visitored_companyDialog.show();
			final EditText visitored_companyName = (EditText) visitored_companyDialog
					.findViewById(R.id.numberedit);

			Button visitored_companyBtn = (Button) visitored_companyDialog
					.findViewById(R.id.dialog_sure);
			visitored_companyBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					mEtVisitoredCompany.setText(visitored_companyName.getText()
							.toString());
					visitored_companyDialog.dismiss();
				}
			});

			break;
		case R.id.visitored_dpt_ly:
			final NumPickerDialog visitored_dptDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "被访人部门");
			// // 设置它的ContentView
			visitored_dptDialog.setContentView(R.layout.num_picker_dialog);
			visitored_dptDialog.show();
			final EditText visitored_dptName = (EditText) visitored_dptDialog
					.findViewById(R.id.numberedit);

			Button visitored_dptBtn = (Button) visitored_dptDialog
					.findViewById(R.id.dialog_sure);
			visitored_dptBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mEtVisitoredDpt.setText(visitored_dptName.getText()
							.toString());
					visitored_dptDialog.dismiss();
				}
			});
			break;
		case R.id.visitored_room_no_ly:
			final NumPickerDialog visitored_room_noDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "被访人房号");
			// // 设置它的ContentView
			visitored_room_noDialog.setContentView(R.layout.num_picker_dialog);
			visitored_room_noDialog.show();
			final EditText visitored_room_noName = (EditText) visitored_room_noDialog
					.findViewById(R.id.numberedit);

			Button visitored_room_noBtn = (Button) visitored_room_noDialog
					.findViewById(R.id.dialog_sure);
			visitored_room_noBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mEtVisitoredRoomNo.setText(visitored_room_noName.getText()
							.toString());
					visitored_room_noDialog.dismiss();
				}
			});
			break;
		case R.id.visitored_no_ly:
			final NumPickerDialog visitored_noDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "被访人编号");
			// // 设置它的ContentView
			visitored_noDialog.setContentView(R.layout.num_picker_dialog);
			visitored_noDialog.show();
			final EditText visitored_noName = (EditText) visitored_noDialog
					.findViewById(R.id.numberedit);

			Button visitored_noBtn = (Button) visitored_noDialog
					.findViewById(R.id.dialog_sure);
			visitored_noBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mEtVisitoredNo.setText(visitored_noName.getText()
							.toString());
					visitored_noDialog.dismiss();
				}
			});
			break;

		case R.id.visitors_name_ly:
			final NumPickerDialog visitors_nameDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "访客姓名");
			// // 设置它的ContentView
			visitors_nameDialog.setContentView(R.layout.num_picker_dialog);
			visitors_nameDialog.show();
			final EditText visitors_nameName = (EditText) visitors_nameDialog
					.findViewById(R.id.numberedit);

			Button visitors_nameBtn = (Button) visitors_nameDialog
					.findViewById(R.id.dialog_sure);
			visitors_nameBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mEtVisitorsName.setText(visitors_nameName.getText()
							.toString());
					visitors_nameDialog.dismiss();
				}
			});
			break;
		case R.id.visitors_phones_no_ly:
			final NumPickerDialog visitors_phones_noDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "访客电话");
			// // 设置它的ContentView
			visitors_phones_noDialog.setContentView(R.layout.num_picker_dialog);
			visitors_phones_noDialog.show();
			final EditText visitors_phones_noName = (EditText) visitors_phones_noDialog
					.findViewById(R.id.numberedit);

			Button visitors_phones_noBtn = (Button) visitors_phones_noDialog
					.findViewById(R.id.dialog_sure);
			visitors_phones_noBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mEtVisitorsPhonesNo.setText(visitors_phones_noName
							.getText().toString());
					visitors_phones_noDialog.dismiss();
				}
			});
			break;
		case R.id.visitors_card_no_ly:
			final NumPickerDialog visitors_card_noDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "访客证件号码");
			// // 设置它的ContentView
			visitors_card_noDialog.setContentView(R.layout.num_picker_dialog);
			visitors_card_noDialog.show();
			final EditText visitors_card_noName = (EditText) visitors_card_noDialog
					.findViewById(R.id.numberedit);

			Button visitors_card_noBtn = (Button) visitors_card_noDialog
					.findViewById(R.id.dialog_sure);
			visitors_card_noBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mEtVisitorsCardNo.setText(visitors_card_noName.getText()
							.toString());
					visitors_card_noDialog.dismiss();
				}
			});
			break;
		case R.id.visitors_company_ly:
			final NumPickerDialog visitors_companyDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "访客所属单位");
			// // 设置它的ContentView
			visitors_companyDialog.setContentView(R.layout.num_picker_dialog);
			visitors_companyDialog.show();
			final EditText visitors_companyName = (EditText) visitors_companyDialog
					.findViewById(R.id.numberedit);

			Button visitors_companyBtn = (Button) visitors_companyDialog
					.findViewById(R.id.dialog_sure);
			visitors_companyBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mEtVisitorsCompany.setText(visitors_companyName.getText()
							.toString());
					visitors_companyDialog.dismiss();
				}
			});
			break;
		case R.id.visitors_car_no_ly:
			final NumPickerDialog visitors_car_noDialog = new NumPickerDialog(
					VisitorAppointmentActivity.this, R.style.dialog, "访客车牌号");
			// // 设置它的ContentView
			visitors_car_noDialog.setContentView(R.layout.num_picker_dialog);
			visitors_car_noDialog.show();
			final EditText visitors_car_noName = (EditText) visitors_car_noDialog
					.findViewById(R.id.numberedit);

			Button visitors_car_noBtn = (Button) visitors_car_noDialog
					.findViewById(R.id.dialog_sure);
			visitors_car_noBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mEtVisitorsCarNo.setText(visitors_car_noName.getText()
							.toString());
					visitors_car_noDialog.dismiss();
				}
			});
			break;

		case R.id.btn_viistor_submit:

			if (mEtVisitorTime.getText().toString().isEmpty()) {
				Toast.makeText(VisitorAppointmentActivity.this, "来访时间不能为空",
						Toast.LENGTH_LONG).show();
				break;
			} else if (mEtVisitorToDo.getText().toString().isEmpty()) {
				Toast.makeText(VisitorAppointmentActivity.this, "来访事由不能为空",
						Toast.LENGTH_LONG).show();
				break;
			} else if (mEtVisitorNum.getText().toString().isEmpty()) {
				Toast.makeText(VisitorAppointmentActivity.this, "来访人数不能为空",
						Toast.LENGTH_LONG).show();
				break;
			} else if (mEtVisitoredName.getText().toString().isEmpty()) {
				Toast.makeText(VisitorAppointmentActivity.this, "被访人姓名不能为空",
						Toast.LENGTH_LONG).show();
				break;
			} else if (mEtVisitoredPhonesNo.getText().toString().isEmpty()) {
				Toast.makeText(VisitorAppointmentActivity.this, "被访人电话不能为空",
						Toast.LENGTH_LONG).show();
				break;
			} else if (mEtVisitorsName.getText().toString().isEmpty()) {
				Toast.makeText(VisitorAppointmentActivity.this, "访客姓名不能为空",
						Toast.LENGTH_LONG).show();
				break;
			} else if (mEtVisitorsCardNo.getText().toString().isEmpty()) {
				Toast.makeText(VisitorAppointmentActivity.this, "访客证件号码不能为空",
						Toast.LENGTH_LONG).show();
				break;
			} else if (mEtVisitoredNo.getText().toString().isEmpty()) {
				Toast.makeText(VisitorAppointmentActivity.this,
						"被访人编号--内部员工编号不能为空", Toast.LENGTH_LONG).show();
				break;
			} else if (mEtVisitoredDpt.getText().toString().isEmpty()) {
				Toast.makeText(VisitorAppointmentActivity.this, "被访人部门不能为空",
						Toast.LENGTH_LONG).show();
				break;
			} else {
				int visitorNum = 1;
				try {
					visitorNum = Integer.parseInt(mEtVisitorNum.getText()
							.toString());
				} catch (Exception e) {
					Toast.makeText(VisitorAppointmentActivity.this,
							"来访人数格式异常,请重新填写", Toast.LENGTH_LONG).show();
					break;
				}
			}
			if (CommonUtil.checkNetState(mContext)) {
				mDialog.show();
				RequestParams params = new RequestParams();

				params.put("visitorTime", mEtVisitorTime.getText().toString());
				params.put("visitToDoName", mEtVisitorToDo.getText().toString());
				params.put("visitorNum", "3");

				params.put("empName", mEtVisitoredName.getText().toString());
				params.put("empTelNo", "");
				params.put("mobileNo", mEtVisitoredPhonesNo.getText()
						.toString());
				params.put("dptName", mEtVisitoredCompany.getText().toString());
				params.put("officeRoom", mEtVisitoredRoomNo.getText()
						.toString());
				params.put("empNo", mEtVisitoredNo.getText().toString());

				params.put("visitorName", mEtVisitorsName.getText().toString());
				params.put("visitorTelNo", mEtVisitorsPhonesNo.getText()
						.toString());
				params.put("visitorIDNo", mEtVisitorsCardNo.getText()
						.toString());
				params.put("companyName", mEtVisitorsCompany.getText()
						.toString());
				params.put("vehicleNo", mEtVisitorsCarNo.getText().toString());

				params.put("visitorSex", "");
				params.put("titName", "");
				params.put("grdName", "");

				ThkhttpClient.post(Constans.VISITOR_SUBMIT_URL, params,
						new AsyncHttpResponseHandler() {
							@Override
							public void onFailure(Throwable arg0, String arg1) {
								hideDialog();
								super.onFailure(arg0, arg1);
							}

							@Override
							public void onSuccess(int arg0, String arg1) {
								hideDialog();
								System.out.println(arg1);
								finish();
								super.onSuccess(arg0, arg1);
							}
						});
			} else {
				Toast.makeText(VisitorAppointmentActivity.this,
						"网络不给力,请重试....", Toast.LENGTH_LONG).show();
			}

			break;
		default:
			break;
		}
	}

}
