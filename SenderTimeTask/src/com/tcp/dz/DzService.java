package com.tcp.dz;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.vo.EquipmentData;
/**
 * 河南邓州平台，协议同特必佳
 * 田工：180 0381 3803
 * @author pactera
 *
 */
@Component
public class DzService extends AbstractBaseService {
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = TBJUtil.getDataString2(v);
		SocketUtil.init2(SystemEnum.HA_DZ_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.HA_DZ_SYSTEM.toString(), 1,info, log);
	}
	
	public static void main(String[] args) {
		DzService dz = new DzService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("AZ00000888");
		dz.sendEquipmentData(v);
	}
	
}
