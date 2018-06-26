package com.http.zxline;

import org.json.JSONException;
import org.json.JSONObject;

import com.utils.CRC;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class JsonUtil {
	
	public static JSONObject toJsonObject(EquipmentData v) throws JSONException{
		JSONObject object = new JSONObject();
		object.put("proname", v.getV_project_name());
		object.put("position", v.getV_project_name());
		object.put("equcode", v.getV_equipment_name());
		object.put("pmtwo", v.getP002());
		object.put("pmten", v.getP003());
		object.put("noise", v.getP008());
		object.put("winddirection", CommonUtil.getWindString3(v.getP005()));
		object.put("windspeed", v.getP004());
		object.put("temperature", v.getP006());
		object.put("depth", v.getP007());
		object.put("time", CRC.currentTimeString("yyyy-MM-dd HH:mm:ss"));
		return object;
	}
}
