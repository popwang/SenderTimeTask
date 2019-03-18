package com.tcp.sxyc2;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 山西运城2数据对接服务，协议就是我们的数据接口
 * @author pactera
 */
@Component
public class Sxyc2Service extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		Yc2Util yc = new Yc2Util(v);
		String info = yc.getDataInfo();
		log.info(info);
//		SocketUtil.init2(SystemEnum.SX_YC2_SYSTEM.toString());
//		SocketUtil.sendDataBySocket(SystemEnum.SX_YC2_SYSTEM.toString(), 10, info, log);
	}
	
	public static void main(String[] args) {
		Sxyc2Service sc = new Sxyc2Service();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = CommonUtil.getEquipmentDataInstance();
				sc.sendEquipmentData(e);
			}
		}, 1000, 1*30*1000);
		
	}
}
