package com.tcp.xawy;

import com.vo.EquipmentData;

public class XawyUtil {
	
	public static String getDataStr(EquipmentData v) {
		StringBuilder sb = new StringBuilder("$01|00|h|01|");
		sb.append("00|00|00|00|00|00|00|");
		sb.append(v.getP004());
		sb.append("|");
		sb.append("00|00|");
		sb.append(v.getP002());
		sb.append("|");
		sb.append(v.getP003());
		sb.append("|");
		sb.append(v.getP006());
		sb.append("|");
		sb.append(v.getP007());
		sb.append("|");
		sb.append(v.getP008());
		sb.append("|");
		sb.append("00|00|");
		sb.append(v.getV_equipment_name());
		sb.append("|");
		String tmp = sb.toString();
		return tmp+tmp.length()+"$";
	}
}
