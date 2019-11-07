package com.tcp.gdsz2;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.gdsz.SzUtil;
import com.utils.CommonUtil;
import com.vo.EquipmentData;
/**
 * 广东深圳对接接口2,协议与1上面接口相同，地址不同
 * ip+端口：14.21.68.148:7099
 * @author pactera
 */
@Component
public class Gdsz2Service extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = SzUtil.getAirString2(v);
//		SocketUtil.init2(SystemEnum.GD_SZ_SYSTEM.toString());
//		SocketUtil.sendDataBySocket(SystemEnum.GD_SZ_SYSTEM.toString(), 1, info, log);
		CommonUtil.sendDataToRemote2(this.systemEnum.toString(), info, log);
	}
	
	public static void main(String[] args) {
		Gdsz2Service service = new Gdsz2Service();
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setP002(1000);
		e.setP003(1000);
		e.setP009(1000);
		e.setV_equipment_name("0E5A424C5759434A43363231");
		service.sendEquipmentData(e);
	}
}
