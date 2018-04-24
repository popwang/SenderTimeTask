package com.tcp.gdsz;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.xabq.XabqUtil;
import com.utils.CommonUtil;
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
		String info = XabqUtil.getAirString2(v);
		SocketUtil.init2(SystemEnum.GD_SZ_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.GD_SZ_SYSTEM.toString(), 1, info, log);
	}
	
	public static void main(String[] args) {
//		GdszService service = new GdszService();
//		EquipmentData e = CommonUtil.getEquipmentDataInstance();
//		e.setV_equipment_name("0E5A424C5759434A43363735");
//		Timer timer = new Timer();
//		timer.scheduleAtFixedRate(new TimerTask() {
//			@Override
//			public void run() {
//				service.sendEquipmentData(e);
//			}
//		}, 1000, 1*60*1000);
	}
}
