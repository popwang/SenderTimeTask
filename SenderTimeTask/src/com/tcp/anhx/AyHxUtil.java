package com.tcp.anhx;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class AyHxUtil {
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("811170211");
		v.setV_project_name("天骄学府");
	}
	/**
	 * 字符转字节
	 * @param date
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] getBytesArray(String date){
		byte[] bytes = null;
		try {
			byte[] b1 = date.getBytes("UTF-8");
			int len  = b1.length;
			System.out.println("len:"+len);
	        byte[] len_byte = ByteUtil.intToByteArray(len);
	        bytes = new byte[len_byte.length + b1.length];
	        System.arraycopy(len_byte, 0, bytes, 0, 4);
	        System.arraycopy(b1, 0, bytes, 4, b1.length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
		return bytes;
	}
	
	/**
	 * 转化字符串
	 * @param v
	 * @return
	 */
	public static String getDataString(List<EquipmentData> datas){
		ObjectMapper objectMapper = new ObjectMapper();
		HxVo vo = new HxVo();
		List<Data> list = new ArrayList<>();
		for(EquipmentData v : datas){
			list.add(switchEquipmentToData(v));
		}
		vo.setData(list);
		String json = "";
		try{
	        json = objectMapper.writeValueAsString(vo);
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
		data.setWD(CommonUtil.getWindString(v.getP005()));
		data.setWS(v.getP004()+"");
		data.setNOISE(v.getP008()+"");
		data.setSITECODE(v.getV_equipment_name());
		data.setMONITORTIME(getMonitortimeString());
		data.setDATASOURCES("zblw");
		return data;
	}
	/**
	 * 转换当前时间分钟数为5的倍数
	 * @return
	 */
	public static String getMonitortimeString(){
		Calendar now = Calendar.getInstance();
		int minute = now.get(Calendar.MINUTE);
		minute = Math.round(minute/5*5);//计算5的整数分钟
		now.set(Calendar.MINUTE, minute);
		now.set(Calendar.SECOND, 0);
		String time =new SimpleDateFormat("yyyy-MM-dd HH:mm:00").format(now.getTime());
		return time;
	}
	
}
