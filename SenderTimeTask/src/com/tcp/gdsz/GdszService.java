package com.tcp.gdsz;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
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
		String info = XabqUtil.getAirString(v);
		
		SocketUtil.init2(SystemEnum.GD_SZ_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.GD_SZ_SYSTEM.toString(), 1, info, log);
	}
	
	public static void main(String[] args) {
		GdszService service = new GdszService();
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("ZBLW00000675");
		service.sendEquipmentData(e);
	}
}
