package com.http.hbxa;

import org.json.JSONException;
import org.json.JSONObject;

import com.tcp.xabq.XabqUtil;
import com.vo.EquipmentData;

public class HbxaUtil {
	public static String getJsonInfo(EquipmentData v) throws JSONException {
		JSONObject object = new JSONObject();
		object.put("token", "65c705cccba6e8b6ebf282eb26b68219");
		object.put("data", XabqUtil.getAirString(v));
		return object.toString();
	}
}
