package com.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vo.EquipmentData;

/**
 * CRC加密工具类
 * 
 * @author Administrator
 */
public class CRC {
	
	/**
	 * 当前时间字符串format
	 * @return
	 */
	public static String currentTimeString(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
	
	/**
	 * 当前时间字符串yyyyMMddHHmmss
	 * @return
	 */
	public static String currentTimeString() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	/**
	 * 当前时间字符串yyMMddHHmmss
	 * @return
	 */
	public static String currentTimeString2() {
		return new SimpleDateFormat("yyMMddHHmmss").format(new Date());
	}
	
	/**
	 * 当前时间字符串yyMMddHHmmss
	 * @return
	 */
	public static String currentTimeString3() {
		return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
	}
	
	/**
	 * 当前时间戳字符串yyyyMMddHHmmssSSS
	 * @return
	 */
	public static String currentTimeStampString() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	
	/**
	 * 当前时间戳字符串 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String currentTimeStampString2() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	/**
	 * 不足4位的数字高位补0
	 * @param number
	 * @return
	 */
	public static String patternNumber(int number) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("0000");
		return df.format(number);
	}
	
	public static String pre = "##";
	/**
	 * 用于根据对象生成山东济宁CRC校验字符串
	 * @param e
	 * @return
	 */
	public static String getDataString(EquipmentData e) {
		StringBuffer sb = new StringBuffer("QN=");
		sb.append(CRC.currentTimeStampString());
		sb.append(";ST=22;CN=2011;PW=123456;MN=ZBLW");
		sb.append(e.getV_equipment_name());
		sb.append(";CP=&&DataTime=");
		sb.append(CRC.currentTimeString());
		sb.append(";");
		sb.append("40-Rtd=");
		sb.append(e.getP003());
		sb.append(",40-Flag=N;&&");
		String crc = sb.toString();
		return CRC.pre + CRC.patternNumber(crc.length()) + crc + CRC.GetCRC(crc) + "\r\n";
	}
	
	
	/**
	 * 用于根据对象生成郑州交委CRC校验字符串
	 * @param e
	 * @return
	 */
	public static String getDataString2(EquipmentData e){
		StringBuffer sb = new StringBuffer("QN=");
		sb.append(CRC.currentTimeStampString());
		sb.append(";ST=22;CN=2011;PW=123456;MN=ZBLW");
		sb.append("160201");
		sb.append(toHexString(e.getV_equipment_name()));
		sb.append(";CP=&&DataTime=");
		sb.append(CRC.currentTimeString());
		sb.append(";");
		sb.append("WIND_DIRECT_Rtd=");
		sb.append(e.getP005());
		sb.append(",WIND_DIRECT_Flag=N;");
		sb.append("WIND_SPEED_Rtd=");
		sb.append(e.getP004());
		sb.append(",WIND_SPEED_Flag=N;");
		sb.append("TEMP_Rtd=");
		sb.append(e.getP006());
		sb.append(",TEMP_Flag=N;");
		sb.append("HUMID_Rtd=");
		sb.append(e.getP007());
		sb.append(",HUMID_Flag=N;");
		sb.append("NOISE=");
		sb.append(e.getP008());
		sb.append(",NOISE_Flag=N;");
		sb.append("NOISE_PEAK_Rtd=");
		sb.append(e.getP008());
		sb.append(",NOISE_PEAK_Flag=N;");
		sb.append("LATIT_Rtd=");
		sb.append(e.getP015());
		sb.append(",LATIT_Flag=N;");
		sb.append("LONGT_Rtd=");
		sb.append(e.getP014());
		sb.append(",LONGT_Flag=N;");
		sb.append("DUST_Rtd=");
		sb.append("-1");
		sb.append(",DUST_Flag=N;");
		sb.append("PM10-Rtd=");
		sb.append(e.getP003());
		sb.append(",PM10-Flag=N;");
		sb.append("TSP-Rtd=-1");
		sb.append(",TSP-Flag=N;&&");
		String crc = sb.toString();
		return CRC.pre+CRC.patternNumber(crc.length())+crc+CRC.GetCRC(crc)+"\r\n";
	}
	
	/**
	 * 用于根据对象生成周口CRC校验字符串
	 * @param e
	 * @return
	 */
	public static String getDataString3(EquipmentData e) {
		StringBuffer sb = new StringBuffer("");
		sb.append("ST=52;CN=2011;PW=123456;MN=Y0394");
		sb.append(e.getV_equipment_name());
		sb.append(";CP=&&DataTime=");
		sb.append(CRC.currentTimeString());
		sb.append(";");
		
		sb.append("PM10-Avg=");
		sb.append(e.getP003());
		sb.append(",PM10-Flag=N;");
		
		sb.append("PM25-Avg=");
		sb.append(e.getP002());
		sb.append(",PM25-Flag=N;");
		
		sb.append("T01-Rtd=");
		sb.append(e.getP006());
		sb.append(",T01-Flag=N;");
		
		sb.append("B03-Rtd=");
		sb.append(e.getP008());
		sb.append(",B03-Flag=N;");
		
		sb.append("H01-Rtd=");
		sb.append(e.getP007());
		sb.append(",H01-Flag=N;");
		
		sb.append("W01-Rtd=");
		sb.append(e.getP005());
		sb.append(",W01-Flag=N;");
		
		sb.append("W02-Rtd=");
		sb.append(e.getP004());
		sb.append(",W02-Flag=N;");
		
		sb.append("R01-Rtd=0");
		sb.append(",R01-Flag=N;");
		
		sb.append("P01-Rtd=");
		sb.append(e.getP010());
		sb.append(",P01-Flag=N;&&");
		String crc = sb.toString();
		return CRC.pre+CRC.patternNumber(crc.length())+crc+CRC.GetCRC(crc)+"\r\n";
	}
	
	
	/**
	 * 用于根据对象生成西安CRC校验字符串
	 * @param e
	 * @return
	 */
	public static String getDataString4(EquipmentData e) {
		StringBuffer sb = new StringBuffer("");
		sb.append("ST=52;CN=2011;PW=123456;MN=");
		sb.append(e.getV_equipment_name());
		sb.append(";CP=&&DataTime=");
		sb.append(CRC.currentTimeString());//yyyyMMddHHmmss
//		sb.append("20171101171400");
		sb.append(";");
		
		sb.append("PM25-Rtd=");
		sb.append(e.getP002());
		sb.append(",PM25-Flag=N;");
		
		sb.append("PM10-Rtd=");
		sb.append(e.getP003());
		sb.append(",PM10-Flag=N;");
		
		sb.append("B03-Rtd=");
		sb.append(e.getP008());
		sb.append(",B03-Flag=N;");
		
		sb.append("W02-Rtd=");
		sb.append(e.getP004());
		sb.append(",W02-Flag=N;");
		
		sb.append("W01-Rtd=");
		sb.append(e.getP005());
		sb.append(",W01-Flag=N;");
		
		sb.append("T01-Rtd=");
		sb.append(e.getP006());
		sb.append(",T01-Flag=N;");
		
		sb.append("H01-Rtd=");
		sb.append(e.getP007());
		sb.append(",H01-Flag=N&&");
		
		String crc = sb.toString();
		return CRC.pre+CRC.patternNumber(crc.length())+crc+CRC.GetCRC(crc)+"<CR><LF>";
	}
	
	/**
	 * 生成山东济南CRC校验字符串
	 * @param e
	 * @return
	 */
	public static String getDataString5(EquipmentData e) {
		StringBuffer sb = new StringBuffer("");
		sb.append("QN=");
		sb.append(CRC.currentTimeStampString());//yyyyMMddHHmmssSSS
		sb.append(";ST=A1;CN=2051;PW=123456;MN=");
		sb.append(e.getV_equipment_name());
		sb.append(";Flag=5;CP=&&DataTime=");
		sb.append(CRC.currentTimeString());//yyyyMMddHHmmss
		sb.append(";");
		
		sb.append("a34004-Rtd=");
		sb.append(e.getP002());
		sb.append(",a34004-Flag=N;");
		
		sb.append("a34002-Rtd=");
		sb.append(e.getP003());
		sb.append(",a34002-Flag=N;");
		
		sb.append("a34001-Rtd=");
		sb.append(e.getP009());
		sb.append(",a34001-Flag=N;");
		
		sb.append("LA-Rtd=");
		sb.append(e.getP008());
		sb.append(",LA-Flag=N;");
		
		sb.append("a01007-Rtd=");
		sb.append(e.getP004());
		sb.append(",a01007-Flag=N;");
		
		sb.append("a01008-Rtd=");
		sb.append(CommonUtil.getWindString(e.getP005()));//转换为度数
		sb.append(",a01008-Flag=N;");
		
		sb.append("a01001-Rtd=");
		sb.append(e.getP006());
		sb.append(",a01001-Flag=N;");
		
		sb.append("a01002-Rtd=");
		sb.append(e.getP007());
		sb.append(",a01002-Flag=N&&");
		
		String crc = sb.toString();
		return CRC.pre+CRC.patternNumber(crc.length())+crc+CRC.GetCRC2(crc)+"\r\n";
	}
	
	/**
	 * 用于根据对象生成韩城CRC校验字符串
	 * @param e
	 * @return
	 */
	public static String getDataString6(EquipmentData e) {
		StringBuffer sb = new StringBuffer("");
		sb.append("ST=52;CN=2011;PW=123456;MN=");
		sb.append(e.getV_equipment_name());
		sb.append(";CP=&&DataTime=");
		sb.append(CRC.currentTimeString());
		sb.append(";");
		
		sb.append("PM10-Avg=");
		sb.append(e.getP003());
		sb.append(",PM10-Flag=N;");
		
		sb.append("PM25-Avg=");
		sb.append(e.getP002());
		sb.append(",PM25-Flag=N;");
		
		sb.append("T01-Rtd=");
		sb.append(e.getP006());
		sb.append(",T01-Flag=N;");
		
		sb.append("B03-Rtd=");
		sb.append(e.getP008());
		sb.append(",B03-Flag=N;");
		
		sb.append("H01-Rtd=");
		sb.append(e.getP007());
		sb.append(",H01-Flag=N;");
		
		sb.append("W01-Rtd=");
		sb.append(e.getP005());
		sb.append(",W01-Flag=N;");
		
		sb.append("W02-Rtd=");
		sb.append(e.getP004());
		sb.append(",W02-Flag=N;");
		
		sb.append("R01-Rtd=0");
		sb.append(",R01-Flag=N;");
		
		sb.append("P01-Rtd=");
		sb.append(e.getP010());
		sb.append(",P01-Flag=N;&&");
		String crc = sb.toString();
		return CRC.pre+CRC.patternNumber(crc.length())+crc+CRC.GetCRC(crc)+"\r\n";
	}
	
	/**
	 * 用于根据对象生成西安秦都区CRC校验字符串
	 * @param e
	 * @return
	 */
	public static String getDataString7(EquipmentData e) {
		StringBuffer sb = new StringBuffer("");
		sb.append("ST=52;CN=2011;PW=123456;MN=");
		sb.append(e.getV_equipment_name());
		sb.append(";CP=&&DataTime=");
		sb.append(CRC.currentTimeString());
		sb.append(";");
		
		sb.append("PM10-Avg=");
		sb.append(e.getP003());
		sb.append(",PM10-Flag=N;");
		
		sb.append("PM25-Avg=");
		sb.append(e.getP002());
		sb.append(",PM25-Flag=N;");
		
		sb.append("T01-Rtd=");
		sb.append(e.getP006());
		sb.append(",T01-Flag=N;");
		
		sb.append("B03-Rtd=");
		sb.append(e.getP008());
		sb.append(",B03-Flag=N;");
		
		sb.append("H01-Rtd=");
		sb.append(e.getP007());
		sb.append(",H01-Flag=N;");
		
		sb.append("W01-Rtd=");
		sb.append(e.getP005());
		sb.append(",W01-Flag=N;");
		
		sb.append("W02-Rtd=");
		sb.append(e.getP004());
		sb.append(",W02-Flag=N;");
		
		sb.append("R01-Rtd=0");
		sb.append(",R01-Flag=N;");
		
		sb.append("P01-Rtd=");
		sb.append(e.getP010());
		sb.append(",P01-Flag=N;&&");
		String crc = sb.toString();
		return CRC.pre+CRC.patternNumber(crc.length())+crc+CRC.GetCRC(crc)+"\r\n";
	}
	
	/**
	 * 生成山东济南CRC校验字符串
	 * @param e
	 * @return
	 */
	public static String getHeartBit(String v_equipment_name) {
		StringBuffer sb = new StringBuffer("");
		sb.append("QN=");
		sb.append(CRC.currentTimeStampString());//yyyyMMddHHmmssSSS
		sb.append(";ST=A1;CN=1010;PW=123456;MN=");
		sb.append(v_equipment_name);
		sb.append(";Flag=5;CP=&&DataTime=");
		sb.append(CRC.currentTimeString());//yyyyMMddHHmmss
		sb.append(";h00000-Hb=30000&&");
		
		String crc = sb.toString();
		return CRC.pre+CRC.patternNumber(crc.length())+crc+CRC.GetCRC2(crc)+"\r\n";
	}
	
	public static void main(String[] args) {  
//      int crc = CRC.GetCRC4(new byte[] { 0x01,0x00,0x01,0x00,(byte)0xC9,0x12,0x00,0x32,0x02,0x09,0x00,0x03,0x10,0x17,0x05,0x05,0x09,0x54,0x42,0x01,0x01,0x03,0x01,0x04,0x02,0x01,0x03,0x00,(byte)0xD2,0x03,0x01,0x03,0x27,0x1F,0x69,0x01,0x03,0x04,0x38,0x70,0x01,0x03,0x03,0x16,0x71,0x01,0x03,0x01,(byte)0xB3,(byte)0x82,0x01,0x03,0x00,0x00,(byte)0x83,0x01,0x03,0x00,(byte)0xA6,0x03});  
//      System.out.println(ByteUtil.bytesToHexString(ByteUtil.intToByteArray(crc)));
//      System.out.println(String.format("0x%04x", crc));  
		String s = "QN=20180424105612214;ST=22;CN=2011;PW=123456;MN=0E5A424C5759434A43363735;Flag=5;CP=&&DataTime=20180424105612;a34004-Rtd=103.0,a34004-Flag=N;a34002-Rtd=83.0,a34002-Flag=N;a34001-Rtd=0.0,a34001-Flag=N;LA-Rtd=52.1,LA-Flag=N;a01007-Rtd=0.5,a01007-Flag=N;a01008-Rtd=90,a01008-Flag=N;a01001-Rtd=32.0,a01001-Flag=N;a01002-Rtd=45.3,a01002-Flag=N&&";
		System.out.println(CRC.GetCRC2(s));
  }
	
	/**
	 * 将设备号转成4位16进制数
	 * @param v
	 * @return
	 */
	public static String toHexString(String v){
		int num = Integer.parseInt(v);
		String hex = Integer.toHexString(num);
		if(hex.length()==1){
			return "000"+hex.toUpperCase();
		}else if(hex.length()==2){
			return "00"+hex.toUpperCase();
		}else if(hex.length()==3){
			return "0"+hex.toUpperCase();
		}else{
			return hex.toUpperCase();
		}
	}
	
	/**
	 * 深圳公司给的掉毛算法，傻逼但是很有用
	 * 山东济宁，郑州交委，周口都使用该校验算法
	 * @param data212
	 * @return
	 */
	public static String GetCRC(String data212) {
		int CRC = 0xFFFF;
		int Num = 0xA001;
		int inum = 0;
		for (int j = 0; j < data212.length(); j++) {
			inum = data212.toCharArray()[j];
			CRC = (CRC >> 8) & 0x00FF;
			CRC ^= inum;

			for (int k = 0; k < 8; k++) {
				int flag = CRC % 2;
				CRC = CRC >> 1;

				if (flag == 1) {
					CRC = CRC ^ Num;
				}
			}
		}
		return Integer.toHexString(CRC).toUpperCase();
	}
	/**
	 * 山东济南CRC校验算法
	 * @param data212
	 * @return
	 */
	public static String GetCRC2(String data212) {
		int CRC = 0xFFFF;
		int Num = 0xA001;
		int inum = 0;
		for (int j = 0; j < data212.length(); j++) {
			inum = data212.toCharArray()[j];
			CRC = (CRC >> 8)^inum;
			for (int k = 0; k < 8; k++) {
				int flag = CRC & 0x0001;
				CRC = CRC >> 1;
				if (flag == 0x0001) {
					CRC = CRC ^ Num;
				}
			}
		}
		return Integer.toHexString(CRC).toUpperCase();
	}
	
	/**
	 * 广东深圳CRC校验算法
	 * @param data212
	 * @return
	 */
	public static String GetCRC3(String data212) {
		int CRC = 0xFFFF;
		int Num = 0xA001;
		int inum = 0;
		for (int j = 0; j < data212.length(); j++) {
			inum = data212.toCharArray()[j];
			CRC = (CRC >> 8)^inum;
			for (int k = 0; k < 8; k++) {
				int flag = CRC & 0x0001;
				CRC = CRC >> 1;
				if (flag == 0x0001) {
					CRC = CRC ^ Num;
				}
			}
		}
		return Integer.toHexString(CRC).toUpperCase();
	}
	
	/**
	 * 河北CRC16校验算法
	 * @param data
	 * @return
	 */
	public static int GetCRC4(byte[] data) {
		int CRC = 0x000000000000FFFF;
		int Num = 0x0000000000008408;
		int Cab = 0x0000000000000000;
		int inum = 0;
		for (int j = 0; j < data.length; j++) {
			inum = data[j] & 0xFF;
			for (int k = 0; k < 8; k++) {
				int flag = (CRC & 0x0000000000000001)^(inum & 0x0000000000000001);
				CRC = CRC >>> 1;
				if (flag == 1) {
					CRC = CRC ^ Num;
				}else{
					CRC = CRC ^ Cab;
				}
				inum = inum>>>1;
			}
		}
		return CRC;
	}
	

	
	/**
	 * 网络CRC算法
	 * 
	 * @param bytes
	 * @return
	 */
	public static int getCRC(byte[] bytes) {
		int CRC = 0x0000ffff;
		int POLYNOMIAL = 0x00008408;
		int i, j;
		for (i = 0; i < bytes.length; i++) {
			CRC ^= ((int) bytes[i] & 0x000000ff);
			for (j = 0; j < 8; j++) {
				if ((CRC & 0x00000001) != 0) {
					CRC >>= 1;
					CRC ^= POLYNOMIAL;
				} else {
					CRC >>= 1;
				}
			}
		}
		return CRC;
	}
}
