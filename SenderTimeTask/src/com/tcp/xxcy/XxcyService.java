package com.tcp.xxcy;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 新乡长垣平台
 * @author 27438
 *
 */
@Component
public class XxcyService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = XabqUtil.getAirString(v);
		SocketUtil.init2(SystemEnum.XX_CY_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.XX_CY_SYSTEM.toString(), 1,info, log);
	}
	
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("Y039300ZB00293");
		String info = XabqUtil.getAirString(v);
		SocketUtil.init2(SystemEnum.XX_CY_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.XX_CY_SYSTEM.toString(), 1,info, log);
	}

}
