package com.tcp.hbwh2;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;

/**
 * �����人2
 * @author 27438
 *
 */
@Component
public class Hbwh2Service extends AbstractBaseService {
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = XabqUtil.getAirString(v);
		SocketUtil.init2(SystemEnum.HB_WH2_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.HB_WH2_SYSTEM.toString(), 1,info, log);
	}
	
	public static void main(String[] args) {
		Hbwh2Service dz = new Hbwh2Service();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("AZDZ00005155");
		dz.sendEquipmentData(v);
	}
	
}