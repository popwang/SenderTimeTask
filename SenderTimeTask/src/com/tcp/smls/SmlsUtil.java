package com.tcp.smls;

import com.vo.EquipmentData;

public class SmlsUtil {
	
	public static String getInfoString(EquipmentData v) {
		//SEND,DATAS,00000929,0,34,38,0.0,7,20.2,58.4,30.3,0,0,0,0,0,34.822203,109.938807,END
		StringBuilder sb  = new StringBuilder("SEND,DATAS,");
		sb.append(v.getV_equipment_name());
		sb.append(",");
		sb.append(v.getP001());
		sb.append(",");
		sb.append(v.getP002());
		sb.append(",");
		sb.append(v.getP003());
		sb.append(",");
		sb.append(v.getP004());
		sb.append(",");
		sb.append(v.getP005());
		sb.append(",");
		sb.append(v.getP006());
		sb.append(",");
		sb.append(v.getP007());
		sb.append(",");
		sb.append(v.getP008());
		sb.append(",");
		sb.append(v.getP009());
		sb.append(",");
		sb.append(v.getP010());
		sb.append(",");
		sb.append(v.getP011());
		sb.append(",");
		sb.append(v.getP012());
		sb.append(",");
		sb.append(v.getP013());
		sb.append(",");
		sb.append(v.getP014());
		sb.append(",");
		sb.append(v.getP015());
		sb.append(",END");
		return sb.toString();
	}
}
