package com.tcp.gdsz;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 广东深圳对接接口
 * ip+端口：111.230.61.165:12002
 * @author pactera
 */
@Component
public class GdszService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = SzUtil.getAirString2(v);
//		SocketUtil.init2(SystemEnum.GD_SZ_SYSTEM.toString());
//		SocketUtil.sendDataBySocket(SystemEnum.GD_SZ_SYSTEM.toString(), 1, info, log);
		CommonUtil.sendDataToRemote2(SystemEnum.GD_SZ_SYSTEM.toString(), info, log);
	}
	
	public static void main(String[] args) {
		GdszService service = new GdszService();
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setP002(1000);
		e.setP003(1000);
		e.setP009(1000);
		e.setV_equipment_name("0E5A424C5759434A43363735");
		service.sendEquipmentData(e);
	}
}
