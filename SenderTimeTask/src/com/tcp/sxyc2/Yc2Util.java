package com.tcp.sxyc2;

import com.vo.EquipmentData;
/**
 * 运城2数据拼装工具类
 * @author pactera
 */
public class Yc2Util {
	private EquipmentData e;
	public Yc2Util(EquipmentData e){
		this.e = e;
	}
	
	public String getDataInfo(){
		StringBuilder sb = new StringBuilder("SEND,DATAS,");
		sb.append(e.getV_equipment_name());
		sb.append(",");
		sb.append((int)e.getP001());
		sb.append(",");
		sb.append(e.getP002());
		sb.append(",");
		sb.append(e.getP003());
		sb.append(",");
		sb.append(e.getP004());
		sb.append(",");
		sb.append(e.getP005());
		sb.append(",");
		sb.append(e.getP006());
		sb.append(",");
		sb.append(e.getP007());
		sb.append(",");
		sb.append(e.getP008());
		sb.append(",");
		sb.append(e.getP009());
		sb.append(",");
		sb.append(e.getP010());
		sb.append(",");
		sb.append(e.getP011());
		sb.append(",");
		sb.append(e.getP012());
		sb.append(",");
		sb.append(e.getP013());
		sb.append(",");
		sb.append(e.getP015());
		sb.append(",");
		sb.append(e.getP014());
		sb.append(",END");
		return sb.toString();
	}
}
