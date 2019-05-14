package com.tcp.xawy;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * ����δ�����ӿڶԽ�
 * ��ַ��116.62.41.41 :8080
 * ����֧�֣�15709248662   �⹤
 * @author 27438
 *
 */
@Component
public class XawyService  extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = XawyUtil.getDataStr(v);
		CommonUtil.sendDataToRemote2(SystemEnum.XA_WY_SYSTEM.toString(), info, log);
	}
	
	
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("180418118");
		String info = XawyUtil.getDataStr(v);
		CommonUtil.sendDataToRemote("116.62.41.41", 8080, info, log);
	}
}
