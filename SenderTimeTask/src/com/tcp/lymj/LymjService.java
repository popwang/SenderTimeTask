package com.tcp.lymj;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 洛阳孟津县住建局平台，对接微信联系人
 * SIM:18490436445,站号：925100020
 * @author 27438
 *
 */
@Component
public class LymjService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = MjUtil.getInfoString(v);
		CommonUtil.sendDataToRemote2(SystemEnum.LY_MJ_SYSTEM.toString(), info, log);
	}
	
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("925100020");
		String info = MjUtil.getInfoString(v);
		System.out.println(info);
		CommonUtil.sendDataToRemote2(SystemEnum.LY_MJ_SYSTEM.toString(), info, log);
	}

}
