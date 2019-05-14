package com.tcp.lymj2;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.hncs.HnUtil;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * �����Ͻ���ס����ƽ̨���Խ�΢����ϵ��
 * SIM:18490436445,վ�ţ�925100020
 * @author 27438
 *
 */
@Component
public class Lymj2Service extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = HnUtil.getDataString(v).replaceAll("AZ", "anzheng");
		CommonUtil.sendDataToRemote2(SystemEnum.LY_MJ2_SYSTEM.toString(), info, log);
	}
	
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("00000565");
		String info = HnUtil.getDataString(v).replaceAll("AZ", "anzheng");
		System.out.println(info);
		CommonUtil.sendDataToRemote2(SystemEnum.LY_MJ2_SYSTEM.toString(), info, log);
	}

}
