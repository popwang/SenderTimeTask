package com.tcp.xabq;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.utils.CommonUtil;
import com.vo.EquipmentData;

public class GbUtil {
	/**
	 * ʹ�ñ�׼212Э�� ���ɿ���������
	 * @param e
	 * @return
	 */
	public static String getAirString(EquipmentData e) {
		StringBuffer sb = new StringBuffer("");
		sb.append("QN=");
		sb.append(CRC.currentTimeStampString());//yyyyMMddHHmmssSSS
		sb.append(";ST=22;CN=2011;PW=123456;MN=");
		sb.append(e.getV_equipment_name());
		sb.append(";Flag=5;CP=&&DataTime=");
		sb.append(CRC.currentTimeString());//yyyyMMddHHmmss
		sb.append(";");
		//PM2.5
		sb.append("a34004-Rtd=");
		sb.append(e.getP002());
		sb.append(",a34004-Flag=N;");
		//PM10
		sb.append("a34002-Rtd=");
		sb.append(e.getP003());
		sb.append(",a34002-Flag=N;");
		//PM100
		sb.append("a34001-Rtd=");
		sb.append(e.getP009());
		sb.append(",a34001-Flag=N;");
		//����
		sb.append("LA-Rtd=");
		sb.append(e.getP008());
		sb.append(",LA-Flag=N;");
		//����
		sb.append("a01007-Rtd=");
		sb.append(e.getP004());
		sb.append(",a01007-Flag=N;");
		//����
		sb.append("a01008-Rtd=");
		sb.append(CommonUtil.getWindString(e.getP005()));//ת��Ϊ����
		sb.append(",a01008-Flag=N;");
		//�¶�
		sb.append("a01001-Rtd=");
		sb.append(e.getP006());
		sb.append(",a01001-Flag=N;");
		//ʪ��
		sb.append("a01002-Rtd=");
		sb.append(e.getP007());
		sb.append(",a01002-Flag=N&&");
		
		String crc = sb.toString();
		return CRC.pre+CRC.patternNumber(crc.length())+crc+CRC.GetCRC2(crc)+"\r\n";
	}
	
	public static class CRC {
		public static String pre = "##";
		
		/**
		 * ��ǰʱ���ַ���yyyyMMddHHmmss
		 * @return
		 */
		public static String currentTimeString() {
			return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		}
		
		/**
		 * ��ǰʱ����ַ���yyyyMMddHHmmssSSS
		 * @return
		 */
		public static String currentTimeStampString() {
			return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		}
		/**
		 * ����4λ�����ָ�λ��0
		 * @param number
		 * @return
		 */
		public static String patternNumber(int number) {
			DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
			df.applyPattern("0000");
			return df.format(number);
		}
		
		/**
		 * CRCУ���㷨
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
	}
}
