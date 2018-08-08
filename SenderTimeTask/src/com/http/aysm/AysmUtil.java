package com.http.aysm;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class AysmUtil {
	
	public static String getInfoString(EquipmentData vo){
		StringBuffer sb = new StringBuffer(vo.getV_url());
		sb.append("?userkey=");
		sb.append(vo.getV_equipment_name());
		sb.append("&data=");
		sb.append(getParamsString(vo));
		sb.append("&date=");
		sb.append(getDateString());
		return sb.toString();
	}
	
	/**
     * 获取当前时间字符串yyyyMMddHHmm
     * @return
     */
    public static String getDateString(){
    	return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }
    
    /**
     * 根据数据对象组装参数字符串
     * @param v
     * @return
     */
    public static String getParamsString(EquipmentData v){
		StringBuffer sb = new StringBuffer();
		sb.append((int)v.getP002());//PM2.5
		sb.append(",");
		sb.append((int)v.getP003());//PM10
		sb.append(",");
		sb.append(v.getP006());//温度
		sb.append(",");
		sb.append((int)v.getP007());//湿度
		sb.append(",");
		sb.append(v.getP008());//噪声
		sb.append(",");
		sb.append(v.getP004());//风速
		sb.append(",");
		sb.append(CommonUtil.getWindString(v.getP005()));//风向
		sb.append(",");
		sb.append("0");//气压
		return sb.toString();
	}
}
