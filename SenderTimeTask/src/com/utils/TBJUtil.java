package com.utils;

import com.vo.EquipmentData;
/**
 * 特必佳工具类
 * @author pactera
 *
 */
public class TBJUtil {
	/**
	 * 用于生成特必佳协议数据串，西安高新区协议
	 * @param v
	 * @return
	 */
	public static String getDataString(EquipmentData v) {
		StringBuffer sb = new StringBuffer();
		sb.append("01"); // 消息类型，默认01
		sb.append("|");
		sb.append(v.getV_equipment_name().substring(0, 2)); // 公司代码，AZ
		sb.append("|");
		sb.append("V1.0"); // 消息版本，默认V1.0
		sb.append("|");
		sb.append(v.getV_equipment_name());// 终端序列号，设备编号：AZ+equipmentName
		sb.append("|");
		sb.append(v.getP006()); // 温度
		sb.append("|");
		sb.append(v.getP007()); // 湿度
		sb.append("|");
		sb.append(v.getP002()); // pm2.5
		sb.append("|");
		sb.append(v.getP003()); // pm10
		sb.append("|");
		sb.append("0"); // 雨量
		sb.append("|");
		sb.append(v.getP008()); // 噪声
		sb.append("|");
		sb.append(v.getP004()); // 风速值
		sb.append("|");
		sb.append(CommonUtil.getWindString2(v.getP005())); // 风向
		sb.append("|");
		sb.append(v.getV_equipment_name());// 设备唯一编号，可以和终端序列号相同
		String str = sb.toString();
		int length = str.getBytes().length;
		String len = str.getBytes().length + "";
		if (length < 100) {
			len = "0" + len;
		}
		return "STX" + len + str + "ETX\r\n";
	}
	
	
	/**
	 * 洛阳伊川字符串
	 * @param v
	 * @return
	 */
	public static String getDataString2(EquipmentData v) {
		StringBuffer sb = new StringBuffer();
		sb.append(v.getV_equipment_name());// 终端序列号，设备编号：equipmentName
		sb.append("|");
		sb.append(v.getP006()); // 温度
		sb.append("|");
		sb.append(v.getP007()); // 湿度
		sb.append("|");
		sb.append(v.getP002()); // pm2.5
		sb.append("|");
		sb.append(v.getP003()); // pm10
		sb.append("|");
		sb.append(v.getP009()); // pm100
		sb.append("|");
		sb.append(v.getP008()); // 噪声
		sb.append("|");
		sb.append(v.getP004()); // 风速值
		sb.append("|");
		sb.append(CommonUtil.getWindString2(v.getP005())); // 风向
		String str = sb.toString();
		
		return "STX|" + str + "|ETX\r\n";
	}
}
