package com.utils;

import com.vo.EquipmentData;
/**
 * �ӱ�ʯ��ׯ���ݴ�������
 * @author Administrator
 *
 */
public class HbUtil {

	public static byte S0H = 0x01;
	public static byte STX = 0x02;
	public static byte ETX = 0x03;
	public static byte E0T = 0x04;
	public static byte ORDER = 0x12;// 12 ��ʱ�ϱ�

	public static byte[] TARGET = { 0x00, 0x01 };

	private EquipmentData e;

	public EquipmentData getE() {
		return e;
	}

	public void setE(EquipmentData e) {
		this.e = e;
	}
	
	public HbUtil(EquipmentData e){
		this.e = e;
	}
	
	/**
	 * ���豸���ת��Ϊ����Ϊ2��byte����
	 * 
	 * @param equipmentId
	 * @return
	 */
	public byte[] getEquipmentBytes(int equipmentId) {
		return shortToByteArray((short)equipmentId);
	}
	
	/**
	 * ��ȡ��������byte����
	 * @return
	 */
	public byte[] getAllInfoBytes(){
		byte[] content = this.getContentBytes();
		//�������ݹ�13�ֽ�
		byte[] info = new byte[13+content.length];
		info[0] = S0H;//0
		System.arraycopy(TARGET, 0, info, 1, 2);//1,2
		byte[] equipment = this.getEquipmentBytes(e.getI_equipment_id());
		System.arraycopy(equipment, 0, info, 3, 2); // 3,4
		info[5] = ORDER;//�����֣�5
		byte[] length = shortToByteArray((short)content.length);
		System.arraycopy(length, 0, info, 6, 2); //6,7
		
		info[8] = STX;//8
		
		//��content���Ƶ�info����
		System.arraycopy(content, 0, info, 9, content.length);
		
		info[content.length+9] = ETX;//9
		/**
		 * һ���ֽ�������Ѫ��������һʱ�����У���ַ���������ETX����ֽڣ����»���2��˶��㷨���������ͷ��Ӭһ����ײ��
		 * ���ǽ��ǣ�
		 * ��������һ��Ҫ���о������ã�
		 */
		//��info�е�һ�ֽڵ�crcǰ������ת����tmp�����Լ���CRC
		byte[] tmp = new byte[content.length+10];
		System.arraycopy(info, 0, tmp, 0, content.length+10);
		byte[] crc =  shortToByteArray((short)CRC.GetCRC4(tmp));
		
		System.arraycopy(crc, 0, info, content.length+10, 2);//10,11
		info[12+content.length] = E0T;//12
		System.out.println("���͵�������"+ByteUtil.bytesToHexString(info));
		return info;
	}
	
	/**
	 * ��ȡ���������ֽ�����
	 * @param e
	 * @return
	 */
	public byte[] getContentBytes() {
		byte[] sys = this.getSystemInfoBytes();
		byte[] temp = this.getTempInfoBytes();
		byte[] hum = this.getHumInfoBytes();
		byte[] pressure = this.getPressureInfoBytes();
		byte[] pm25 = this.getPm25InfoBytes();
		byte[] voice = this.getVoiceInfoBytes();
		byte[] pm10 = this.getPm10InfoBytes();
		byte[] speed = this.getSpeedInfoBytes();
		byte[] direct = this.getDirectInfoBytes();

		int length = sys.length + temp.length + hum.length + pressure.length + pm25.length + voice.length + pm10.length
				+ speed.length + direct.length;

		byte[] info = new byte[length+1];//ͷ��������ͨ����1���ֽ�
		info[0] = 0x09;
		System.arraycopy(sys, 0, info, 1, sys.length);
		int index = sys.length;
		System.arraycopy(temp, 0, info, index+1, temp.length);
		index += temp.length;
		System.arraycopy(hum, 0, info, index+1, hum.length);
		index += hum.length;
		System.arraycopy(pressure, 0, info, index+1, pressure.length);
		index += pressure.length;
		System.arraycopy(pm10, 0, info, index+1, pm10.length);
		index += pm10.length;
		System.arraycopy(pm25, 0, info, index+1, pm25.length);
		index += pm25.length;
		System.arraycopy(voice, 0, info, index+1, voice.length);
		index += voice.length;
		System.arraycopy(speed, 0, info, index+1, speed.length);
		index += speed.length;
		System.arraycopy(direct, 0, info, index+1, direct.length);
		return info;
	}
	/**
	 * ����ʱ��ͨ��
	 * @return
	 */
	public byte[] getSystemInfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x00;
		head[1] = 0x03;
		head[2] = 0x10;
		byte[] data = HbUtil.str2Bcd(CRC.currentTimeString2());
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * �¶�ͨ��
	 * 
	 * @return
	 */
	public byte[] getTempInfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x01;
		head[1] = 0x01;
		head[2] = 0x03;
		byte[] data = shortToByteArray((short)Math.abs((e.getP006() * 10)));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * ʪ��ͨ��
	 * 
	 * @return
	 */
	public byte[] getHumInfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x02;
		head[1] = 0x01;
		head[2] = 0x03;
		byte[] data = shortToByteArray((short) (e.getP007() * 10));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * ��ѹͨ��
	 * 
	 * @return
	 */
	public byte[] getPressureInfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x03;
		head[1] = 0x01;
		head[2] = 0x03;
		byte[] data = shortToByteArray((short) (e.getP010() * 10));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * pm2.5ͨ��
	 * 
	 * @return
	 */
	public byte[] getPm25InfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x70;
		head[1] = 0x01;
		head[2] = 0x03;
		byte[] data = shortToByteArray((short) (e.getP002() * 10));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * ����ͨ��
	 * 
	 * @return
	 */
	public byte[] getVoiceInfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x71;
		head[1] = 0x01;
		head[2] = 0x03;
		byte[] data = shortToByteArray((short) (e.getP008() * 10));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * pm10ͨ��
	 * 
	 * @return
	 */
	public byte[] getPm10InfoBytes() {
		byte[] head = new byte[3];
		head[0] = 0x69;
		head[1] = 0x01;
		head[2] = 0x03;
		byte[] data = shortToByteArray((short) (e.getP003() * 10));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * ����ͨ��
	 * 
	 * @return
	 */
	public byte[] getSpeedInfoBytes() {
		byte[] head = new byte[3];
		head[0] = (byte)0x82;
		head[1] = 0x01;
		head[2] = 0x03;
		byte[] data = shortToByteArray((short) (e.getP004() * 10));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * ����ͨ��
	 * 
	 * @return
	 */
	public byte[] getDirectInfoBytes() {
		byte[] head = new byte[3];
		head[0] = (byte)0x83;
		head[1] = 0x01;
		head[2] = 0x03;
		byte[] data = shortToByteArray((short)Integer.parseInt(CommonUtil.getWindString(e.getP005())));
		byte[] info = new byte[3 + data.length];
		System.arraycopy(head, 0, info, 0, 3);
		System.arraycopy(data, 0, info, 3, data.length);
		return info;
	}

	/**
	 * 10���ƴ�תΪBCD��
	 * 
	 * @param asc
	 * @return
	 */
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	/**
	 * ��short����תΪ����Ϊ2��byte����
	 * 
	 * @param a
	 * @return
	 */
	public static byte[] shortToByteArray(short a) {
		return new byte[] { (byte) ((a >> 8) & 0xFF), (byte) (a & 0xFF) };
	}
}
