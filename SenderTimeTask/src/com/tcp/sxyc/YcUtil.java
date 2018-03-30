package com.tcp.sxyc;

import com.utils.ByteUtil;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.vo.EquipmentData;
/**
 * 运城数据拼装工具类
 * @author pactera
 *
 */
public class YcUtil {
	public static byte[] HEADER = {(byte)0x88,(byte)0x88};
	public static byte SEND_TYPE = 0x00;
	public static byte HEART_TYPE = 0x01;
	public static byte CHECK_TYPE = 0x02;
	public static int TOKEN = 588800012;
	
	
	private EquipmentData e;
	
	public EquipmentData getE() {
		return e;
	}


	public void setE(EquipmentData e) {
		this.e = e;
	}


	public YcUtil(EquipmentData e){
		this.e = e;
	}
	
	/**
	 * 字符串转伪16进制字节数组
	 * @param s
	 * @param len
	 * @return
	 */
	public static byte[] intStringToHexString(String s,int len){
		byte[] b = new byte[len/2];
		
		if(s.indexOf(".")>0){
			String tmp[] = s.split("\\.");
			while(tmp[0].length()<3){
				tmp[0] = "0"+tmp[0];
			}
			while(tmp[1].length()<5){
				tmp[1] = tmp[1]+"0";
			}
			if(tmp[1].length()>5){
				tmp[1] = tmp[1].substring(0, 5);
			}
			s = tmp[0]+tmp[1];
		}
		
		String s0 = "";
		for(int i=0;i<len/2;i++){
			s0 = s.substring(i*2, i*2+2);
			b[i] = (byte)Integer.parseInt(s0, 16);
		}
		return b;
	}
	
	/**
	 * 将double数值*10后，转成真16进制
	 * @return
	 */
	public static byte[] intToHexString(double d){
		short i = (short)(d*10);
		return ByteUtil.shortToByteArray(i);
	}
	
	public static void main(String[] args) {
		System.out.println(ByteUtil.bytesToHexString(intToHexString(26.8)));
	}
	
	/**
	 * 返回发送数据的16进制字符串
	 * @return
	 */
	public byte[] getDataInfo(){
		byte[] datas = new byte[73];
		//头信息 2
		System.arraycopy(HEADER, 0, datas, 0, 2);
		//消息编号 4
		byte[] code = intStringToHexString(CRC.currentTimeString("ddHHmmss"),8);
		System.arraycopy(code, 0, datas, 2, 4);
		//设备编号 8
		byte[] equipment = intStringToHexString(e.getV_equipment_name(),16);
		System.arraycopy(equipment, 0, datas, 6, 8);
		//token 4
		byte[] token = ByteUtil.intToByteArray(TOKEN);
		System.arraycopy(token, 0, datas, 14, 4);
		//数据类型 1
		byte[] type = {SEND_TYPE};
		System.arraycopy(type, 0, datas, 18, 1);
		//TSP 3 暂时置为0
		byte[] tsp = {SEND_TYPE,SEND_TYPE,SEND_TYPE};
		System.arraycopy(tsp, 0, datas, 19, 3);
		//PM2.5 2
		byte[] pm25 = intToHexString(e.getP002());
		System.arraycopy(pm25, 0, datas, 22, 2);
		//PM10 2
		byte[] pm10 = intToHexString(e.getP003());
		System.arraycopy(pm10, 0, datas, 24, 2);
		//噪声 2
		byte[] voice = intToHexString(e.getP008());
		System.arraycopy(voice, 0, datas, 26, 2);
		//风向 2
		byte[] direct = intToHexString(Integer.parseInt(CommonUtil.getWindString(e.getP005())));
		System.arraycopy(direct, 0, datas, 28, 2);
		//风速 2
		byte[] speed = intToHexString(e.getP004());
		System.arraycopy(speed, 0, datas, 30, 2);
		//温度 2
		byte[] temper = intToHexString(e.getP006());
		System.arraycopy(temper, 0, datas, 32, 2);
		//湿度 2
		byte[] wet = intToHexString(e.getP007());
		System.arraycopy(wet, 0, datas, 34, 2);
		//经度 4
		byte[] lon = intStringToHexString(e.getP014()+"",8);
		System.arraycopy(lon, 0, datas, 36, 4);
		//纬度 4
		byte[] lat = intStringToHexString(e.getP015()+"",8);
		System.arraycopy(lat, 0, datas, 40, 4);
		//采集起始时间  6
		byte[] st = intStringToHexString(CRC.currentTimeString("yyyyMMddhhmm"),12);
		System.arraycopy(st, 0, datas, 44, 6);
		//采集结束时间  6
		byte[] et = intStringToHexString(CRC.currentTimeString("yyyyMMddhhmm"),12);
		System.arraycopy(et, 0, datas, 50, 6);
		//数据发送时间  6
		byte[] sendtime = intStringToHexString(CRC.currentTimeString("yyyyMMddhhmm"),12);
		System.arraycopy(sendtime, 0, datas, 56, 6);
		//备用数据 8
		byte[] bak = {SEND_TYPE,SEND_TYPE,SEND_TYPE,SEND_TYPE,SEND_TYPE,SEND_TYPE,SEND_TYPE,SEND_TYPE};
		System.arraycopy(bak, 0, datas, 62, 8);
		//校验数据 1
		datas[70] = xorByteArray(datas);
		datas[71] = 0x03;
		datas[72] = 0x04;
		return datas;
	}
	
	
	/**
	 * 返回心跳数据的16进制字符串
	 * @return
	 */
	public byte[] getHeartBeat(){
		byte[] datas = new byte[22];
		//头信息 2
		System.arraycopy(HEADER, 0, datas, 0, 2);
		//消息编号 4
		byte[] code = intStringToHexString(CRC.currentTimeString("ddHHmmss"),8);
		System.arraycopy(code, 0, datas, 2, 4);
		//设备编号 8
		byte[] equipment = intStringToHexString(e.getV_equipment_name(),16);
		System.arraycopy(equipment, 0, datas, 6, 8);
		//token 4
		byte[] token = ByteUtil.intToByteArray(TOKEN);
		System.arraycopy(token, 0, datas, 14, 4);
		//数据类型 1
		byte[] type = {HEART_TYPE};
		System.arraycopy(type, 0, datas, 18, 1);
		//校验数据 1
		datas[19] = xorByteArray(datas);
		datas[20] = 0x03;
		datas[21] = 0x04;
		return datas;
	}
	/**
	 * 返回时间校正数据的16进制字符串
	 * @return
	 */
	public byte[] getTimeCheck(){
		byte[] datas = new byte[22];
		//头信息 2
		System.arraycopy(HEADER, 0, datas, 0, 2);
		//消息编号 4
		byte[] code = intStringToHexString(CRC.currentTimeString("ddHHmmss"),8);
		System.arraycopy(code, 0, datas, 2, 4);
		//设备编号 8
		byte[] equipment = intStringToHexString(e.getV_equipment_name(),16);
		System.arraycopy(equipment, 0, datas, 6, 8);
		//token 4
		byte[] token = ByteUtil.intToByteArray(TOKEN);
		System.arraycopy(token, 0, datas, 14, 4);
		//数据类型 1
		byte[] type = {CHECK_TYPE};
		System.arraycopy(type, 0, datas, 18, 1);
		//校验数据 1
		datas[19] = xorByteArray(datas);
		datas[20] = 0x03;
		datas[21] = 0x04;
		return datas;
	}
	
	public byte xorByteArray(byte[] datas){
		byte tmp = datas[0];
		for(int i=1;i<datas.length-3;i++){
			tmp ^= datas[i];
		}
		return tmp;
	}
}
