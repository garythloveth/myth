package com.thk.thkvisitor.parser;

import org.json.JSONException;
import org.json.JSONObject;

public class VisitorAppointmentParser extends BaseParser<JSONObject> {

	@Override
	public JSONObject parseJSON(String paramString) throws JSONException {
		JSONObject jsonObject = new JSONObject(paramString);
		return jsonObject;
	}

}
