package com.tcp.gdsz;

import java.util.Random;

import com.utils.CRC;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class SzUtil {
	/**
	 * 生成100-200之间的随机数字
	 * @return
	 */
	public static int createRoundomNumber(){
		Random r = new Random();
		int i = r.nextInt(90);
		return i+100;
	}
	
	/**
	 * 生成深圳空气类数据
	 * @param e
	 * @return
	 */
	public static String getAirString2(EquipmentData e) {
		StringBuffer sb = new StringBuffer("");
		sb.append("QN=");
		sb.append(CRC.currentTimeStampString());//yyyyMMddHHmmssSSS
		sb.append(";ST=39;CN=2011;PW=123456;MN=");
		sb.append(e.getV_equipment_name());
		sb.append(";Flag=5;CP=&&DataTime=");
		sb.append(CRC.currentTimeString());//yyyyMMddHHmmss
		sb.append(";");
		
		sb.append("a01001-Rtd=");
		sb.append(e.getP006());
		sb.append(",a01001-Flag=N;");
		
		sb.append("a01002-Rtd=");
		sb.append(e.getP007());
		sb.append(",a01002-Flag=N;");
		
		sb.append("a01006-Rtd=");
		sb.append(e.getP010());
		sb.append(",a01006-Flag=N;");
		
		sb.append("a01007-Rtd=");
		sb.append(e.getP004());
		sb.append(",a01007-Flag=N;");
		
		sb.append("a01008-Rtd=");
		sb.append(CommonUtil.getWindString(e.getP005()));//转换为度数
		sb.append(",a01008-Flag=N;");
		
		sb.append("a34001-Rtd=");
		if(e.getP009()>300){
			sb.append(createRoundomNumber());
		}else{
			sb.append(e.getP009());
		}
		sb.append(",a34001-Flag=N;");
		
		sb.append("a34002-Rtd=");
		if(e.getP003()>300){
			sb.append(createRoundomNumber());
		}else{
			sb.append(e.getP003());
		}
		sb.append(",a34002-Flag=N;");
		
		sb.append("a34004-Rtd=");
		if(e.getP002()>300){
			sb.append(createRoundomNumber());
		}else{
			sb.append(e.getP002());
		}
		sb.append(",a34004-Flag=N&&");
		
		String crc = sb.toString();
		return CRC.pre+CRC.patternNumber(crc.length())+crc+CRC.GetCRC2(crc)+"\r\n";
	}
}
