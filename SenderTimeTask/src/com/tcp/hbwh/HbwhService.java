package com.tcp.hbwh;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 湖北武汉平台，通用212协议
 * @author pactera
 *
 */
@Component
public class HbwhService extends AbstractBaseService {
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = XabqUtil.getAirString(v);
		SocketUtil.init2(SystemEnum.HB_WH_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.HB_WH_SYSTEM.toString(), 1,info, log);
	}
	
	public static void main(String[] args) {
		HbwhService dz = new HbwhService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("AZDZ00005155");
		dz.sendEquipmentData(v);
	}
	
}
