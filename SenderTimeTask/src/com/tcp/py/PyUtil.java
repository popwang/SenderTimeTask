package com.tcp.py;

import com.utils.ByteUtil;
import com.utils.CRC;
import com.vo.EquipmentData;

public class PyUtil {
	private EquipmentData e;
	private byte HEAD_01 = 0x01;
	private byte HEAD_02 = 0x03;
	private byte HEAD_03 = 0x60;
	private byte[] NIL = {0x00,0x00,0x00,0x00};
	
	public PyUtil(EquipmentData e){
		this.e = e;
	}
	
	
	public byte[] getInfos(){
		byte[] infos = new byte[101];
		infos[0] = HEAD_01;
		infos[1] = HEAD_02;
		infos[2] = HEAD_03;
		byte[] dates  = getDataBytes();
		System.arraycopy(dates, 0, infos, 3, 76);
		byte[] ids = getIdBytes();
		System.arraycopy(ids, 0, infos, 79, 20);
		byte[] crc = getCRCBytes(infos);
		System.arraycopy(crc, 0, infos, 99, 2);
		return infos;
	}
	
	public static void main(String[] args) {
		float voice = 0.548096f;
		byte[] voice_b = ByteUtil.intToByteArray(Float.floatToIntBits(voice));
		System.out.println(ByteUtil.bytesToHexString(voice_b));
	}
	
	/**
	 * 获取数据段字节数组
	 * @return
	 */
	public byte[] getDataBytes(){
		byte[] datas = new byte[76];
		//噪声
		byte[] voice = floatToBytes((float)e.getP008()/100);//噪声先除以10，服务端解析后会再乘10
		System.arraycopy(voice, 0, datas, 0, 4);
		//pm10
		byte[] pm10 = floatToBytes((float)e.getP003());
		System.arraycopy(pm10, 0, datas, 4, 4);
		//风速
		byte[] ws = floatToBytes((float)e.getP004());
		System.arraycopy(ws, 0, datas, 8, 4);
		//风向
		byte[] wd = floatToBytes((float)e.getP005());
		System.arraycopy(wd, 0, datas, 12, 4);
		//温度
		byte[] tmp = floatToBytes((float)e.getP006());
		System.arraycopy(tmp, 0, datas, 16, 4);
		//湿度
		byte[] hum = floatToBytes((float)e.getP007());
		System.arraycopy(hum, 0, datas, 20, 4);
		//气压
		byte[] pres = floatToBytes((float)e.getP010());
		System.arraycopy(pres, 0, datas, 24, 4);
		//电压
		System.arraycopy(NIL, 0, datas, 28, 4);
		//pm25
		byte[] pm25 = floatToBytes((float)e.getP002());
		System.arraycopy(pm25, 0, datas, 32, 4);
		//经度
		System.arraycopy(NIL, 0, datas, 36, 4);
		//纬度
		System.arraycopy(NIL, 0, datas, 40, 4);
		//板载温度
		System.arraycopy(NIL, 0, datas, 44, 4);
		//板载湿度
		System.arraycopy(NIL, 0, datas, 48, 4);
		//TSP
		byte[] tsp = floatToBytes((float)e.getP009());
		System.arraycopy(tsp, 0, datas, 52, 4);
		//3个1*4预留字节
		System.arraycopy(NIL, 0, datas, 56, 4);
		System.arraycopy(NIL, 0, datas, 60, 4);
		System.arraycopy(NIL, 0, datas, 64, 4);
		//1个字节填充，1个字节的错误码
		System.arraycopy(NIL, 0, datas, 68, 2);
		//剩余6个字节，yyMMdd HHmmss
		String now = CRC.currentTimeString("yyMMddHHmmss");
		datas[70] = (byte)Integer.parseInt(now.substring(0,2));
		datas[71] = (byte)Integer.parseInt(now.substring(2,4));
		datas[72] = (byte)Integer.parseInt(now.substring(4,6));
		datas[73] = (byte)Integer.parseInt(now.substring(6,8));
		datas[74] = (byte)Integer.parseInt(now.substring(8,10));
		datas[75] = (byte)Integer.parseInt(now.substring(10));
		return datas;
	}
	
	/**
	 * 将float转成大端序的字节数组
	 * 先将float转成int，再将int转成字节数组
	 * @param f
	 * @return
	 */
	public byte[] floatToBytes(float f){
		byte[] bytes = ByteUtil.intToByteArray(Float.floatToIntBits(f));
		return bytes;
	}
	
	/**
	 * 获取id段字节数组
	 * @return
	 */
	public byte[] getIdBytes(){
		byte[] ids = new byte[20];
		System.arraycopy(e.getV_equipment_name().getBytes(), 0, ids, 0, 19);
		ids[19] = 0x00;
		return ids;
	}
	/**
	 * 获取crc校验字节数组
	 * @return
	 */
	public byte[] getCRCBytes(byte[] infos){
		byte[] tmp = new byte[99];
		System.arraycopy(infos, 0, tmp, 0, 99);
		int v = CRC.GetCRC4(tmp);
		return ByteUtil.shortToByteArray((short)v);
	}
}
