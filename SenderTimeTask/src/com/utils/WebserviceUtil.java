package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vo.EquipmentData;
/**
 * 调用webservice接口的服务，协议相同，使用同一个工具类方法生成发送数据串
 * @author pactera
 */
public class WebserviceUtil {
	public static String getDataString(EquipmentData v){
		StringBuffer sb = new StringBuffer("DevID:|:");
	      sb.append(v.getV_equipment_name());
	      sb.append("#|#Time:|:");
	      sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	      sb.append("#|#HUMI:|:");
	      sb.append(v.getP007());
	      sb.append("#|#TEMP:|:");
	      sb.append(v.getP006());
	      sb.append("#|#PRE:|:");
	      sb.append(v.getP010());
	      sb.append("#|#WINDD:|:");
	      sb.append(CommonUtil.getWindString(v.getP005()));
	      sb.append("#|#WINDS:|:");
	      sb.append(v.getP004());
	      sb.append("#|#NOISE:|:");
	      sb.append(v.getP008());
	      sb.append("#|#PM25:|:");
	      sb.append((int)v.getP002());
	      sb.append("#|#PM10:|:");
	      sb.append((int)v.getP003());
	      sb.append("#|#TSP:|:");
	      sb.append((int)v.getP009());
	      
		return sb.toString();
	}
}
