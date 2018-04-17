package com.tcp.sxwn;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.hncs.HnUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 陕西渭南对接，与湖南长沙同一家公司，协议相同
 * @author pactera
 *
 */
@Component
public class SxwnService extends AbstractBaseService{

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = HnUtil.getDataString(v);
		info = info.replaceAll("AZ", "ZB");
//		info = "MD,18000552,ZB,0,65.5,100.5,,40.3,35.5,16.6,12.4, 90,34.726462,113.639278#";
		SocketUtil.init2(SystemEnum.SX_WN_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.SX_WN_SYSTEM.toString(), 1,info, log);
//		SocketUtil.init2(SystemEnum.HN_CS_SYSTEM.toString());
//		SocketUtil.sendDataBySocket(SystemEnum.HN_CS_SYSTEM.toString(), 1,info, log);
	}

	public static void main(String[] args) {
		SxwnService sxwn = new SxwnService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("18000552");
		sxwn.sendEquipmentData(v);
	}
}
