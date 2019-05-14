package com.tcp.zkhy;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;

/**
 * 周口淮阳平台，协议212
 * 地址：39.100.81.104:6022
 * 5272
 * @author 27438
 *
 */
@Component
public class ZkhyService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = CRC.getDataString3(v);
		CommonUtil.sendDataToRemote2(SystemEnum.ZK_HY_SYSTEM.toString(),info,log);
	}
	
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("HYYC-100100165272");
		String info = CRC.getDataString3(v);
		CommonUtil.sendDataToRemote2(SystemEnum.ZK_HY_SYSTEM.toString(),info,log);
	}

}
