package com.tcp.wnhc;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;

/**
 * 渭南韩城对接，协议与周口类似，已上线
 * 219.145.160.12:10000
 * @author pactera
 */
@Component
public class WnhcService extends AbstractBaseService{

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = CRC.getDataString6(v);
//		SocketUtil.init2(SystemEnum.WN_HC_SYSTEM.toString());
//		SocketUtil.sendDataBySocket(SystemEnum.WN_HC_SYSTEM.toString(), 3,info, log);
		CommonUtil.sendDataToRemote2(SystemEnum.WN_HC_SYSTEM.toString(),info,log);
	}
	
	public static void main(String[] args) {
		WnhcService ws = new WnhcService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("Y0913001600684");
		ws.sendEquipmentData(v);
	}
}
