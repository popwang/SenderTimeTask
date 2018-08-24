package com.utils;

import com.vo.EquipmentData;
/**
 * �رؼѹ�����
 * @author pactera
 *
 */
public class TBJUtil {
	/**
	 * ���������رؼ�Э�����ݴ�������������Э��
	 * @param v
	 * @return
	 */
	public static String getDataString(EquipmentData v) {
		StringBuffer sb = new StringBuffer();
		sb.append("01"); // ��Ϣ���ͣ�Ĭ��01
		sb.append("|");
		sb.append(v.getV_equipment_name().substring(0, 2)); // ��˾���룬AZ
		sb.append("|");
		sb.append("V1.0"); // ��Ϣ�汾��Ĭ��V1.0
		sb.append("|");
		sb.append(v.getV_equipment_name());// �ն����кţ��豸��ţ�AZ+equipmentName
		sb.append("|");
		sb.append(v.getP006()); // �¶�
		sb.append("|");
		sb.append(v.getP007()); // ʪ��
		sb.append("|");
		sb.append(v.getP002()); // pm2.5
		sb.append("|");
		sb.append(v.getP003()); // pm10
		sb.append("|");
		sb.append("0"); // ����
		sb.append("|");
		sb.append(v.getP008()); // ����
		sb.append("|");
		sb.append(v.getP004()); // ����ֵ
		sb.append("|");
		sb.append(CommonUtil.getWindString2(v.getP005())); // ����
		sb.append("|");
		sb.append(v.getV_equipment_name());// �豸Ψһ��ţ����Ժ��ն����к���ͬ
		String str = sb.toString();
		int length = str.getBytes().length;
		String len = str.getBytes().length + "";
		if (length < 100) {
			len = "0" + len;
		}
		return "STX" + len + str + "ETX\r\n";
	}
	
	
	/**
	 * ���������ַ���
	 * @param v
	 * @return
	 */
	public static String getDataString2(EquipmentData v) {
		StringBuffer sb = new StringBuffer();
		sb.append(v.getV_equipment_name());// �ն����кţ��豸��ţ�equipmentName
		sb.append("|");
		sb.append(v.getP006()); // �¶�
		sb.append("|");
		sb.append(v.getP007()); // ʪ��
		sb.append("|");
		sb.append(v.getP002()); // pm2.5
		sb.append("|");
		sb.append(v.getP003()); // pm10
		sb.append("|");
		sb.append(v.getP009()); // pm100
		sb.append("|");
		sb.append(v.getP008()); // ����
		sb.append("|");
		sb.append(v.getP004()); // ����ֵ
		sb.append("|");
		sb.append(CommonUtil.getWindString2(v.getP005())); // ����
		String str = sb.toString();
		
		return "STX|" + str + "|ETX\r\n";
	}
}
