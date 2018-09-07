package com.tcp.xabq;


import org.json.JSONException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcp.xabq2.BQ2;
import com.tcp.xabq2.BQ2Vo;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class XabqUtil {
	
	/**
	 * 生成空气类数据
	 * @param e
	 * @return
	 */
	public static String getAirString(EquipmentData e) {
		StringBuffer sb = new StringBuffer("");
		sb.append("QN=");
		sb.append(CRC.currentTimeStampString());//yyyyMMddHHmmssSSS
		sb.append(";ST=22;CN=2011;PW=123456;MN=");
		sb.append(e.getV_equipment_name());
		sb.append(";Flag=5;CP=&&DataTime=");
		sb.append(CRC.currentTimeString());//yyyyMMddHHmmss
		sb.append(";");
		//PM2.5
		sb.append("a34004-Rtd=");
		sb.append(e.getP002());
		sb.append(",a34004-Flag=N;");
		//PM10
		sb.append("a34002-Rtd=");
		sb.append(e.getP003());
		sb.append(",a34002-Flag=N;");
		//PM100
		sb.append("a34001-Rtd=");
		sb.append(e.getP009());
		sb.append(",a34001-Flag=N;");
		//噪音
		sb.append("LA-Rtd=");
		sb.append(e.getP008());
		sb.append(",LA-Flag=N;");
		//风速
		sb.append("a01007-Rtd=");
		sb.append(e.getP004());
		sb.append(",a01007-Flag=N;");
		//风向
		sb.append("a01008-Rtd=");
		sb.append(CommonUtil.getWindString(e.getP005()));//转换为度数
		sb.append(",a01008-Flag=N;");
		//温度
		sb.append("a01001-Rtd=");
		sb.append(e.getP006());
		sb.append(",a01001-Flag=N;");
		//湿度
		sb.append("a01002-Rtd=");
		sb.append(e.getP007());
		sb.append(",a01002-Flag=N&&");
		
		String crc = sb.toString();
		return CRC.pre+CRC.patternNumber(crc.length())+crc+CRC.GetCRC2(crc)+"\r\n";
	}
	
	public static String toJsonObject(EquipmentData v) throws JSONException{
		ObjectMapper objectMapper = new ObjectMapper();
		BQ2Vo vo = new BQ2Vo();
		vo.setId(v.getV_equipment_name());
		vo.setAuth("AZ888888");
		vo.setVer("100");
		vo.setWarn(0);
		vo.setData(switchEquipmentToData(v));
		String json = "";
		try{
	        json = objectMapper.writeValueAsString(vo);
	    }  
	    catch (Exception e){  
	        e.printStackTrace();  
	    } 
		return json;
	}
	
	public static BQ2 switchEquipmentToData(EquipmentData v){
		BQ2 data = new BQ2();
		data.setP1(v.getP002());
		data.setP2(v.getP003());
		data.setNo(v.getP008());
		data.setWs(v.getP004());
		data.setWd(Double.parseDouble(CommonUtil.getWindString(v.getP005())));
		data.setTp(v.getP006());
		data.setHm(v.getP007());
		return data;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(toJsonObject(CommonUtil.getEquipmentDataInstance()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}
