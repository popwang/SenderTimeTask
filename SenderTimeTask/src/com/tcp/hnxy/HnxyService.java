package com.tcp.hnxy;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;

/**
 * 信阳平台对接
 * 地址：125.45.157.10:8888
 * 密码：XYQYJC
 * 协议：国标212
 * @author 27438
 *
 */
@Component
public class HnxyService extends AbstractBaseService {
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = XabqUtil.getAirString(v);
		info = info.replaceAll("PW=123456", "PW=XYQYJC");
		SocketUtil.init2(SystemEnum.HN_XY_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.HN_XY_SYSTEM.toString(), 1,info, log);
	}
	
	public static void main(String[] args) {
		HnxyService dz = new HnxyService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("AZDZ00005155");
		dz.sendEquipmentData(v);
	}
	
}