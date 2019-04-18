package com.tcp.aynew;

import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.vo.EquipmentData;
/**
 * 安阳新平台工具类
 * 组装发送字节
 * @author pactera
 */
public class AynewUtil {
	
	public AynewUtil(EquipmentData e){
		this.e = e;
	}
	
	private EquipmentData e;

	public EquipmentData getE() {
		return e;
	}

	public void setE(EquipmentData e) {
		this.e = e;
	}
	
	/**
	 * 头字节
	 */
	public static byte HEAD = 0x68;
	
	/**
	 * 获取最终数据数组
	 * @return
	 */
	public byte[] getContentBytes(){
		byte[] header = this.getHeaderBytes();
		byte[] data = this.getDataBytes();
		byte[] mac = getMacCode(e.getV_equipment_name());
		byte[] data_len = ByteUtil.shortToByteArray2((short)data.length);
		int len = data.length+16;
		byte[] content = new byte[len];
		
//		System.out.println(len);
//		System.arraycopy(HEAD, 0, content, 0, 1);
		content[0] = HEAD;
		int index = 1;
		System.arraycopy(mac, 0, content, index, mac.length);
		index += mac.length;
		System.arraycopy(header, 0, content, index, header.length);
		index += header.length;
		System.arraycopy(data_len, 0, content, index, data_len.length);
		index += data_len.length;
		System.arraycopy(data, 0, content, index, data.length);
		index += data.length;
		content[len-1] = getCheckCode(content);
		return content;
	}
	
	/**
	 * 数据域
	 * @return
	 */
	public byte[] getDataBytes() {
		byte[] pm25 = this.getPm25InfoBytes();
		byte[] pm10 = this.getPm10InfoBytes();
		byte[] temp = this.getTempInfoBytes();
		byte[] hum = this.getHumInfoBytes();
		byte[] voice = this.getVoiceInfoBytes();
		byte[] speed = this.getSpeedInfoBytes();
		byte[] direct = this.getDirectInfoBytes();
		byte[] pressure = this.getPressureInfoBytes();

		int length = temp.length + hum.length + pressure.length + pm25.length + voice.length + pm10.length
				+ speed.length + direct.length;
		
//		System.out.println("data:"+length);

		byte[] info = new byte[length];//头部增加总通道数1个字节
		System.arraycopy(pm25, 0, info, 0, pm25.length);
		int index = pm25.length;
		System.arraycopy(pm10, 0, info, index, pm10.length);
		index += pm10.length;
		System.arraycopy(temp, 0, info, index, temp.length);
		index += temp.length;
		System.arraycopy(hum, 0, info, index, hum.length);
		index += hum.length;
		System.arraycopy(voice, 0, info, index, voice.length);
		index += voice.length;
		System.arraycopy(speed, 0, info, index, speed.length);
		index += speed.length;
		System.arraycopy(direct, 0, info, index, direct.length);
		index += direct.length;
		System.arraycopy(pressure, 0, info, index, pressure.length);
		return info;
	}
	
	/**
	 * 计算和模校验码
	 * @param s
	 * @return
	 */
	public byte getCheckCode(byte[] s) {
		int sum = 0;
		for(int i=1;i<s.length-1;i++) {
			sum+=(s[i]%256);
		}
		return (byte)sum;
	}
	
	public byte[] getHeaderBytes(){
		byte[] bytes = new byte[6];
		bytes[0] = 0x03;
		bytes[1] = 0x01;
		bytes[2] = 0x01;
		bytes[3] = 0x01;
		bytes[4] = 0x00;
		bytes[5] = 0x00;
		return bytes;
	}
	
	
	public static void main(String[] args) {
		System.out.println(ByteUtil.bytesToHexString(getMacCode("00000057")));
	}
	
	/**
	 * 传入4位设备号
	 * @param equipmentcode
	 * @return
	 */
	public static byte[] getMacCode(String equipmentcode){
		byte[] bytes = new byte[6];
		bytes[0] = 0x0E;
		System.arraycopy(("Z"+equipmentcode).getBytes(), 0, bytes, 1, 5);
		String epc = ByteUtil.bytesToHexString(bytes).toUpperCase();
		if(epc.substring(4, 6).equals("31")){
			epc = epc.replaceFirst("31", "F0");
		}else{
			epc = epc.replaceFirst("30", "D1");
		}
		
		System.out.println(epc);
		int len = epc.length();
		String s0 = "";
		for(int i=0;i<len/2;i++){
			s0 = epc.substring(i*2, i*2+2);
			bytes[len/2-1-i] = (byte)Integer.parseInt(s0, 16);
		}
		return bytes;
	}
	
	/**
	 * pm2.5通道
	 * 
	 * @return
	 */
	public byte[] getPm25InfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x01;
		head[1] = 0x01;
		head[2] = 0x02;
		byte[] data = ByteUtil.shortToByteArray2((short) (e.getP002()));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}
	
	/**
	 * pm10通道
	 * 
	 * @return
	 */
	public byte[] getPm10InfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x02;
		head[1] = 0x01;
		head[2] = 0x02;
		byte[] data = ByteUtil.shortToByteArray2((short) (e.getP003()));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}
	
	/**
	 * 温度通道
	 * 
	 * @return
	 */
	public byte[] getTempInfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x03;
		head[1] = 0x01;
		head[2] = 0x02;
//		byte[] data = ByteUtil.shortToByteArray2((short)(e.getP006() * 100));
		byte[] data = longToByte8(Double.doubleToLongBits(e.getP006()));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * 湿度通道
	 * 
	 * @return
	 */
	public byte[] getHumInfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x04;
		head[1] = 0x01;
		head[2] = 0x02;
//		byte[] data = ByteUtil.shortToByteArray2((short) (e.getP007() * 100));
		byte[] data = longToByte8(Double.doubleToLongBits(e.getP007()));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}
	
	/**
	 * 噪声通道
	 * 
	 * @return
	 */
	public byte[] getVoiceInfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x05;
		head[1] = 0x01;
		head[2] = 0x02;
//		byte[] data = ByteUtil.shortToByteArray2((short) (e.getP008()*10));
		byte[] data = longToByte8(Double.doubleToLongBits(e.getP008()));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}
	
	/**
	 * 风速通道
	 * 
	 * @return
	 */
	public byte[] getSpeedInfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x06;
		head[1] = 0x01;
		head[2] = 0x02;
//		byte[] data = ByteUtil.shortToByteArray2((short) (e.getP004() * 10));
		byte[] data = longToByte8(Double.doubleToLongBits(e.getP004()));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * 风向通道
	 * 
	 * @return
	 */
	public byte[] getDirectInfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x07;
		head[1] = 0x01;
		head[2] = 0x02;
		byte[] data = ByteUtil.shortToByteArray2((short)Integer.parseInt(CommonUtil.getWindString(e.getP005())));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * 气压通道
	 * 
	 * @return
	 */
	public byte[] getPressureInfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x08;
		head[1] = 0x01;
		head[2] = 0x02;
		byte[] data = ByteUtil.shortToByteArray2((short) (e.getP010()));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}
	
	
	public static byte[] longToByte8(long sum) {
        byte[] arr = new byte[8];
        arr[7] = (byte) (sum >> 56);
        arr[6] = (byte) (sum >> 48);
        arr[5] = (byte) (sum >> 40);
        arr[4] = (byte) (sum >> 32);
        arr[3] = (byte) (sum >> 24);
        arr[2] = (byte) (sum >> 16);
        arr[1] = (byte) (sum >> 8);
        arr[0] = (byte) (sum & 0xff);
        return arr;
    }
}
