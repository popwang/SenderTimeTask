package com.tcp.zz6;

import com.utils.ByteUtil;
import com.utils.CRC;
import com.utils.HbUtil;
import com.vo.EquipmentData;

public class Zz6Util {
	private EquipmentData e;
	
	public Zz6Util(EquipmentData e){
		this.e = e;
	}
	
	/**
	 * 获取数据报文
	 * 2+3+1+2+36+2+2
	 * @return
	 */
	public byte[] getEquipmentInfo(){
		byte[] info = new byte[48];
		System.arraycopy(getSyn(), 0, info, 0, 2);
		System.arraycopy(getVer(), 0, info, 2, 3);
		System.arraycopy(new byte[]{0x01}, 0, info, 5, 1);
		System.arraycopy(getLength(), 0, info, 6, 2);
		
		System.arraycopy(getDataArray(), 0, info, 8, 36);
		System.arraycopy(getCrc(getDataArray()), 0, info, 44, 2);
		System.arraycopy(getEtx(), 0, info, 46, 2);
		return info;
	}
	
	/**
	 * 获取心跳报文
	 * 2+3+1+2+14+2+2=26
	 * @return
	 */
	public byte[] getHeartBeatInfo(){
		byte[] info = new byte[26];
		System.arraycopy(getSyn(), 0, info, 0, 2);
		System.arraycopy(getVer(), 0, info, 2, 3);
		System.arraycopy(new byte[]{0x00}, 0, info, 5, 1);
		System.arraycopy(ByteUtil.shortToByteArray2((short)14), 0, info, 6, 2);
		System.arraycopy(getHeartBeatData(), 0, info, 8, 14);
		System.arraycopy(getCrc(getHeartBeatData()), 0, info, 22, 2);
		System.arraycopy(getEtx(), 0, info, 24, 2);
		return info;
	}
	
	public byte[] getHeartBeatData(){
		byte[] heart = new byte[14];
		System.arraycopy(get1EquipmentName(e.getV_equipment_name()), 0, heart, 0, 8);
		System.arraycopy(get2Rtc(), 0, heart, 8, 6);
		return heart;
	}
	
	/**
	 * 0.起始符
	 * @return
	 */
	public byte[] getSyn(){
		return new byte[]{0x7A,0X7A};
	}
	/**
	 * 1.版本号
	 * @return
	 */
	public byte[] getVer(){
		return new byte[]{0x01,0x02,0x04};
	}
	/**
	 * 2.命令符
	 * @return
	 */
	public byte[] getCmd(){
		return new byte[]{0x01};
	}
	/**
	 * 3.数据域长度
	 * @return
	 */
	public byte[] getLength(){
		return ByteUtil.shortToByteArray2((short)36);
	}
	/**
	 * 4.数据域，长度为36字节 
	 * @return
	 */
	public byte[] getDataArray(){
		byte[] data = new byte[36];
		System.arraycopy(get1EquipmentName(e.getV_equipment_name()), 0, data, 0, 8);
		System.arraycopy(get2Rtc(), 0, data, 8, 6);
		System.arraycopy(get3PM25(e.getP002()), 0, data, 14, 2);
		System.arraycopy(get410(e.getP003()), 0, data, 16, 2);
		System.arraycopy(get5Voice(e.getP008()), 0, data, 18, 2);
		System.arraycopy(get6Temp(e.getP006()), 0, data, 20, 2);
		System.arraycopy(get7Hum(e.getP007()), 0, data, 22, 2);
		System.arraycopy(get8Speed(e.getP004()), 0, data, 24, 2);
		System.arraycopy(get9Direct(e.getP005()), 0, data, 26, 2);
		System.arraycopy(get10Gprs(), 0, data, 28, 1);
		System.arraycopy(get11Auto(), 0, data, 29, 1);
		System.arraycopy(get12Man(), 0, data, 30, 1);
		System.arraycopy(get13Air(e.getP010()), 0, data, 31, 2);
		System.arraycopy(get14Tsp(e.getP009()), 0, data, 33, 2);
		System.arraycopy(get15Alarm(), 0, data, 35, 1);
		return data;
	}
	/**
	 * 4.1设备编号
	 * @return
	 */
	public static byte[] get1EquipmentName(String name){
		byte[] arr = new byte[8];
		for(int i=0;i<8;i++){
			arr[i] = (byte)name.charAt(i);
		}
		return arr;
	}
	/**
	 * 4.2RTC当前时间的BCD码
	 * @return
	 */
	public static byte[] get2Rtc(){
		String now = CRC.currentTimeString("yyMMddHHmmss");
		return HbUtil.str2Bcd(now);
	}
	
	/**
	 * 4.3pm2.5
	 * @return
	 */
	public static byte[] get3PM25(double d){
		short i = (short)d;
		return ByteUtil.shortToByteArray2(i);
	}
	/**
	 * 4.4pm10
	 * @return
	 */
	public static byte[] get410(double d){
		short i = (short)d;
		return ByteUtil.shortToByteArray2(i);
	}
	/**
	 * 4.5噪声
	 * @return
	 */
	public static byte[] get5Voice(double d){
		short i = (short)(d*10);
		return ByteUtil.shortToByteArray2(i);
	}
	/**
	 * 4.6温度
	 * @return
	 */
	public static byte[] get6Temp(double d){
		short i = (short)(d*10);
		return ByteUtil.shortToByteArray2(i);
	}
	/**
	 * 4.7湿度
	 * @return
	 */
	public static byte[] get7Hum(double d){
		short i = (short)(d*10);
		return ByteUtil.shortToByteArray2(i);
	}
	/**
	 * 4.8风速
	 * @return
	 */
	public static byte[] get8Speed(double d){
		short i = (short)(d*10);
		return ByteUtil.shortToByteArray2(i);
	}
	/**
	 * 4.9风向
	 * @return
	 */
	public static byte[] get9Direct(double d){
		short i = (short)d;
		return ByteUtil.shortToByteArray2(i);
	}
	/**
	 * 4.10gprs信号质量
	 * @return
	 */
	public static byte[] get10Gprs(){
		return new byte[]{0x00};
	}
	/**
	 * 4.11自动模式
	 * @return
	 */
	public static byte[] get11Auto(){
		return new byte[]{0x01};
	}/**
	 * 4.12手动模式
	 * @return
	 */
	public static byte[] get12Man(){
		return new byte[]{0x00};
	}/**
	 * 4.13气压
	 * @return
	 */
	public static byte[] get13Air(double d){
		short i = (short)(d*10);
		return ByteUtil.shortToByteArray2(i);
	}
	/**
	 * 4.14TSP
	 * @return
	 */
	public static byte[] get14Tsp(double d){
		short i = (short)d;
		return ByteUtil.shortToByteArray2(i);
	}
	/**
	 * 4.15警报
	 * @return
	 */
	public static byte[] get15Alarm(){
		return new byte[]{0x00};
	}
	
	public static void main(String[] args) {
		byte[] b = new byte[]{
				 0x4C ,0x41, 0x30 ,0x30, 0x30, 0x30, 0x30, 0x38, 0x16, 0x08, 0x08 ,0x12 ,0x24, 0x56
		};
		System.out.println(ByteUtil.bytesToHexString(get2Rtc()));
		System.out.println(ByteUtil.bytesToHexString(ByteUtil.shortToByteArray2((short)14)));
		System.out.println(ByteUtil.bytesToHexString(ByteUtil.shortToByteArray2((short)CRC.toCrc(b))));
		
	
	}
	
	
	/**
	 * 5.CRC16校验码 
	 * @param data
	 * @return
	 */
	public byte[] getCrc(byte[] data){
		return ByteUtil.shortToByteArray2((short)CRC.toCrc(data));
	}
	/**
	 * 6.结束符
	 * @return
	 */
	public byte[] getEtx(){
		return new byte[]{0x7B,0x7B};
	}
	
	
	
}
