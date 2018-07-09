package com.tcp.sxwn;

import java.util.Timer;
import java.util.TimerTask;

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
		if(v.getV_equipment_name().contains("ZB")){
			info = info.replaceAll("AZ", "ZB");
		}
		SocketUtil.init2(SystemEnum.SX_WN_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.SX_WN_SYSTEM.toString(), 1,info, log);
	}

	public static void main(String[] args) {
		SxwnService sxwn = new SxwnService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("ZB000552");
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				sxwn.sendEquipmentData(v);
			}
		}, 1000, 1*10*1000);
	}
}
