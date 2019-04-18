package com.tcp.sxty;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 山西太原对接，国标212协议
 * ip:skt.heziz.com
 * port:28011
 * @author 27438
 */
@Component
public class SxtyService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = XabqUtil.getAirString(v);
		SocketUtil.init2(SystemEnum.SX_TY_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.SX_TY_SYSTEM.toString(), 1,info, log);
	}
	
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("AZ00005271");
		String info = XabqUtil.getAirString(v);
		SocketUtil.init(SystemEnum.SX_TY_SYSTEM.toString(),"skt.heziz.com",28011);
		SocketUtil.sendDataBySocket(SystemEnum.SX_TY_SYSTEM.toString(), 1,info, log);
	}
}
