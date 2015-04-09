package com.thk.thkvisitor.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.thk.thkvisitor.model.VisitorInfoData;

public class VisitorQueryParser extends BaseParser<ArrayList<VisitorInfoData>> {

	@Override
	public ArrayList<VisitorInfoData> parseJSON(String paramString) throws JSONException {
		
//		paramString = "[{\"visitorName\":\"新联\",\"visitorNum\":8,\"visitorIDNo\":\"新联\",\"status\":0,\"empNo\":\"新联\",\"companyName\":\"新联\",\"titName\":\"新联\",\"officeRoom\":\"新联\",\"grdName\":\"2014-08-14 14:30:30\",\"empName\":\"星河地产总裁\",\"visitorTelNo\":\"新联\",\"visitorSex\":\"男\",\"dptName\":\"新联\",\"_hashCodeCalc\":\"false\",\"visitToDoName\":\"洽谈星河丹堤项目\",\"visitorId\":55,\"empTelNo\":\"null\",\"mobileNo\":\"18888888888\",\"vehicleNo\":\"新联\"},{\"visitorName\":\"新联\",\"visitorNum\":8,\"visitorIDNo\":\"新联\",\"status\":0,\"empNo\":\"新联\",\"companyName\":\"新联\",\"titName\":\"新联\",\"officeRoom\":\"新联\",\"grdName\":\"2014-08-15 14:30:30\",\"empName\":\"祈福地产华南区总经理\",\"visitorTelNo\":\"新联\",\"visitorSex\":\"男\",\"dptName\":\"新联\",\"_hashCodeCalc\":\"false\",\"visitToDoName\":\"参观新联访客系统\",\"visitorId\":55,\"empTelNo\":\"null\",\"mobileNo\":\"18999999999\",\"vehicleNo\":\"新联\"},{\"visitorName\":\"新联\",\"visitorNum\":8,\"visitorIDNo\":\"新联\",\"status\":0,\"empNo\":\"新联\",\"companyName\":\"新联\",\"titName\":\"新联\",\"officeRoom\":\"新联\",\"grdName\":\"2014-08-15 14:30:30\",\"empName\":\"祈福地产华南区总经理\",\"visitorTelNo\":\"新联\",\"visitorSex\":\"男\",\"dptName\":\"新联\",\"_hashCodeCalc\":\"false\",\"visitToDoName\":\"参观新联访客系统\",\"visitorId\":55,\"empTelNo\":\"null\",\"mobileNo\":\"18999999999\",\"vehicleNo\":\"新联\"},{\"visitorName\":\"新联\",\"visitorNum\":8,\"visitorIDNo\":\"新联\",\"status\":0,\"empNo\":\"新联\",\"companyName\":\"新联\",\"titName\":\"新联\",\"officeRoom\":\"新联\",\"grdName\":\"2014-08-15 14:30:30\",\"empName\":\"祈福地产华南区总经理\",\"visitorTelNo\":\"新联\",\"visitorSex\":\"男\",\"dptName\":\"新联\",\"_hashCodeCalc\":\"false\",\"visitToDoName\":\"参观新联访客系统\",\"visitorId\":55,\"empTelNo\":\"null\",\"mobileNo\":\"18999999999\",\"vehicleNo\":\"新联\"}]";

//		paramString = "[]";
		
		ArrayList<VisitorInfoData> arrayList = new ArrayList<VisitorInfoData>();
		JSONArray visitorInfoDataArray = new JSONArray(paramString);
		if (visitorInfoDataArray.length() > 0) {
			for (int i = 0; i < visitorInfoDataArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) visitorInfoDataArray.get(i);
				VisitorInfoData visitorInfoData = new VisitorInfoData();
				visitorInfoData.setGrdName(jsonObject.getString("grdName"));			//来访时间
				visitorInfoData.setVisitToDoName(jsonObject.getString("visitToDoName"));//来访事由
				visitorInfoData.setVisitorNum(jsonObject.getInt("visitorNum"));			//来访人数
				visitorInfoData.setEmpName(jsonObject.getString("empName"));			//被访人姓名
				visitorInfoData.setMobileNo(jsonObject.getString("mobileNo"));			//被访人联系电话
				arrayList.add(visitorInfoData);
			}
		}
		return arrayList;
	}

}
