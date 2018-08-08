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
     * ��ȡ��ǰʱ���ַ���yyyyMMddHHmm
     * @return
     */
    public static String getDateString(){
    	return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }
    
    /**
     * �������ݶ�����װ�����ַ���
     * @param v
     * @return
     */
    public static String getParamsString(EquipmentData v){
		StringBuffer sb = new StringBuffer();
		sb.append((int)v.getP002());//PM2.5
		sb.append(",");
		sb.append((int)v.getP003());//PM10
		sb.append(",");
		sb.append(v.getP006());//�¶�
		sb.append(",");
		sb.append((int)v.getP007());//ʪ��
		sb.append(",");
		sb.append(v.getP008());//����
		sb.append(",");
		sb.append(v.getP004());//����
		sb.append(",");
		sb.append(CommonUtil.getWindString(v.getP005()));//����
		sb.append(",");
		sb.append("0");//��ѹ
		return sb.toString();
	}
}
