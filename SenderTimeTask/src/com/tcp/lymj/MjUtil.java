package com.tcp.lymj;

import com.vo.EquipmentData;

public class MjUtil {
	public static String getInfoString(EquipmentData v){
		StringBuilder sb = new StringBuilder("$01|AZ_2H8D|h|01|N|00|00|00|00|00|00|");
		sb.append((int)v.getP004());
		sb.append("|00|00|");
		sb.append((int)v.getP003());
		sb.append("|");
		sb.append((int)v.getP002());
		sb.append("|");
		sb.append((int)v.getP006());
		sb.append("|");
		sb.append((int)v.getP007());
		sb.append("|");
		sb.append((int)v.getP008());
		sb.append("|00|00|");
		sb.append(v.getV_equipment_name());
		sb.append("|");
		String info = sb.toString();
		return info+info.length()+"$";
	}
}
