package com.tcp.anhx;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class AyHxUtil {
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("811170211");
		v.setV_project_name("天骄学府");
		getDataString(v);
	}
	
	/**
	 * 转化字符串
	 * @param v
	 * @return
	 */
	public static String getDataString(EquipmentData v){
		ObjectMapper objectMapper = new ObjectMapper();
		HxVo vo = new HxVo();
		vo.setData(switchEquipmentToData(v));
		String json = "";
		try{
	        json = objectMapper.writeValueAsString(vo);  
	        System.out.println(json);  
	    }  
	    catch (Exception e){  
	        e.printStackTrace();  
	    } 
		return json;
	}
	/**
	 * 数据对象转为jsonvo
	 * @param v
	 * @return
	 */
	public static Data switchEquipmentToData(EquipmentData v){
		Data data = new Data();
		data.setHUM(v.getP007()+"");
		data.setPM10(v.getP003()+"");
		data.setPM2_5(v.getP002()+"");
		data.setTEMP(v.getP006()+"");
		data.setSITEID(v.getV_equipment_name());
		data.setSITECODE(v.getV_equipment_name());
		data.setSITENAME(v.getV_project_name());
		data.setMONITORTIME(getMonitortimeString());
		return data;
	}
	/**
	 * 转换当前时间分钟数为5的倍数
	 * @return
	 */
	public static String getMonitortimeString(){
		Calendar now = Calendar.getInstance();
		int minute = now.get(Calendar.MINUTE);

		minute = Math.round(minute/5*5);//计算10的整数分钟

		now.set(Calendar.MINUTE, minute);
		now.set(Calendar.SECOND, 0);
		String time =new SimpleDateFormat("yyyy-MM-dd HH:mm:00").format(now.getTime());
		return time;
	}
	
}
