package com.tcp.zz6;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.hncs.HnUtil;
import com.utils.CommonUtil;
import com.vo.EquipmentData;

/**
 * 郑州地铁6号线平台对接
 * @author 27438
 */
@Component
public class Zz6Service extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		
	}
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = CommonUtil.getEquipmentDataInstance();
				e.setV_equipment_name("ZB000888");
				String info = HnUtil.getDataString(e);
				if(e.getV_equipment_name().contains("ZB")){
					info = info.replaceAll("AZ", "ZB");
				}
//				Zz6Util util = new Zz6Util(e);
//				
//				String now = CRC.currentTimeString("yyyyMMddHHmm");
//				byte[] info = null;
//				String msg = null;
//				if(now.endsWith("0") || now.endsWith("5")){
//					info = util.getEquipmentInfo();
//				}else{
//					info = util.getHeartBeatInfo();
//				}
//				msg = ByteUtil.bytesToHexString(info).toUpperCase();
//				SocketUtil.init(SystemEnum.ZZ_SIX_SYSTEM.toString(), "data.ahjyrj.com", 8070);
//				SocketUtil.sendDataBySocket(SystemEnum.ZZ_SIX_SYSTEM.toString(), 1,msg, log);
				CommonUtil.sendDataToRemote("data.ahjyrj.com", 8070, info, log);
			}
		}, 1000, 5*60*1000);
	}

}
