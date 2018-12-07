package com.tcp.ly;

import com.utils.CRC;
import com.vo.EquipmentData;

public class LYUtil {
	public static String getRegisterInfo(EquipmentData e){
		StringBuffer sb = new StringBuffer("");
		sb.append("{\"DeviceId\":\"");
		sb.append(e.getV_equipment_name());
		sb.append("\"}");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static String getVoiceInfo(EquipmentData e){
		StringBuffer sb = new StringBuffer("");
		sb.append("{\"DataType\":\"Min\",");
		sb.append("\"ProjectId\":\"");
		sb.append(e.getV_equipment_name());
		sb.append("\",");
		sb.append("\"DeviceId\":\"");
		sb.append(e.getV_equipment_name());
		sb.append("\",");
		sb.append("\"DataFlag\":\"\",");
		sb.append("\"DB\":\"");
		sb.append(e.getP008());
		sb.append("\",");
		sb.append("\"LEQ\":\"\",");
		sb.append("\"LMax\":\"\",");
		sb.append("\"LMin\":\"\",");
		sb.append("\"L5\":\"\",");
		sb.append("\"L10\":\"\",");
		sb.append("\"L50\":\"\",");
		sb.append("\"L90\":\"\",");
		sb.append("\"L95\":\"\",");
		sb.append("\"SD\":\"\",");
		sb.append("\"LE\":\"\",");
		sb.append("\"LPeak\":\"\",");
		sb.append("\"RecDate\":\"");
		sb.append(CRC.currentTimeStampString2());
		sb.append("\"}");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static  String getPmInfo(EquipmentData e){
		StringBuffer sb = new StringBuffer("");
		sb.append("{\"DataType\":\"Min\",");
		sb.append("\"ProjectId\":\"");
		sb.append(e.getV_equipment_name());
		sb.append("\",");
		sb.append("\"DeviceId\":\"");
		sb.append(e.getV_equipment_name());
		sb.append("\",");
		sb.append("\"AQI\":\"\",");
		sb.append("\"Dust\":\"");
		sb.append(e.getP009());
		sb.append("\",");
		sb.append("\"PM10\":\"");
		sb.append(e.getP003());
		sb.append("\",");
		
		sb.append("\"PM10State\":\"");
		sb.append("1");
		sb.append("\",");
		
		sb.append("\"PM2.5\":\"");
		sb.append(e.getP002());
		sb.append("\",");
		
		sb.append("\"PM2.5State\":\"");
		sb.append("1");
		sb.append("\",");
		
		sb.append("\"AirPressure\":\"");
		sb.append(e.getP010());
		sb.append("\",");
		
		sb.append("\"Temperature\":\"");
		sb.append(e.getP006());
		sb.append("\",");
		
		sb.append("\"Humidity\":\"");
		sb.append(e.getP007());
		sb.append("\",");
		
		sb.append("\"WindDirection\":\"");
		sb.append(e.getP005());
		sb.append("\",");
		
		sb.append("\"WindSpeed\":\"");
		sb.append(e.getP004());
		sb.append("\",");
		
		sb.append("\"RecDate\":\"");
		sb.append(CRC.currentTimeStampString2());
		sb.append("\",");
		
		sb.append("\"Location\":\"\",");
		sb.append("\"GPS\":\"\",");
		sb.append("\"SO2\":\"\",");
		sb.append("\"NO2\":\"\",");
		sb.append("\"NOX\":\"\",");
		sb.append("\"CO\":\"\",");
		sb.append("\"O3\":\"\",");
		sb.append("\"CO2\":\"\",");
		sb.append("\"PictureType\":\"\"");
		sb.append("}");
		System.out.println(sb.toString());
		return sb.toString();
	}
}
