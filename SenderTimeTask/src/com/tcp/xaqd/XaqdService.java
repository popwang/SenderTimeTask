package com.tcp.xaqd;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;

/**
 * 西安秦都区,协议与灞桥区类似
 * 120.27.43.188:10085
 * @author pactera
 */
@Component
public class XaqdService extends AbstractBaseService{

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = XaqdUtil.getAirString(v);
		SocketUtil.init2(SystemEnum.XA_QD_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.XA_QD_SYSTEM.toString(), 1,info, log);
	}

	public static void main(String[] args) {
		XaqdService service = new XaqdService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("Y002900AZ00933");
		service.sendEquipmentData(v);
	}
}
