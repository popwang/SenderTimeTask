package com.http.nj;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.utils.CRC;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class NjUtil {
	public static JSONObject toJsonObject(EquipmentData v) throws JSONException{
		JSONObject object = new JSONObject();
		object.put("equCode", v.getV_equipment_name());
		object.put("equStatus", 0);
		object.put("collectionDt", CRC.currentTimeString("yyyy-MM-dd HH:mm:ss"));
		object.put("collectionDuration", "300");
		object.put("pMTwoValue", v.getP002()>100?new Random().nextInt(49)+50:v.getP002());
		object.put("pMTenValue", v.getP003()>100?new Random().nextInt(49)+50:v.getP003());
		object.put("temperature", v.getP006());
		object.put("humidity", v.getP007());
		object.put("windSpeed", v.getP004());
		object.put("windDirection", CommonUtil.getWindString3(v.getP005()));
		object.put("noise", v.getP008());
		object.put("mainId", 6);
		object.put("outterId", "B1");
		object.put("proName", "B1");
		object.put("oper", 5);
		return object;
	}
	
	public static void main(String[] args) {
		System.out.println(new Random().nextInt(100));
	}
}
