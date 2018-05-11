package com.tcp.hncs;

import com.vo.EquipmentData;

public class HnUtil {
	public static String getDataString(EquipmentData v) {
		StringBuilder sb = new StringBuilder();
		sb.append("MD,");
		sb.append(v.getV_equipment_name());
		sb.append(",");
		sb.append("AZ,");
		sb.append("0,");
		sb.append(v.getP002());
		sb.append(",");
		sb.append(v.getP003());
		sb.append(",");
		sb.append(",");
		sb.append(v.getP008());
		sb.append(",");
		sb.append(v.getP006());
		sb.append(",");
		sb.append(v.getP007());
		sb.append(",");
		sb.append(v.getP004());
		sb.append(",");
		sb.append(v.getP005());
		sb.append(",");
		sb.append(",,,,,,");
		sb.append(v.getP014());
		sb.append(",");
		sb.append(v.getP015());
		sb.append("#");
		return sb.toString();
	}
}
