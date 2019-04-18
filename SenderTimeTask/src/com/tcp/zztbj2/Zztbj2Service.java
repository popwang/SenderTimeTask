package com.tcp.zztbj2;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.vo.EquipmentData;

/**
 * 1.同特必佳平台；
 * 2.将部分用户关注度比较高的设备，放在这里，1分钟发送一次数据；
 * 3.
 * @author Administrator
 */
@Component
public class Zztbj2Service extends AbstractBaseService {
	
	public static void main(String[] args){
		Timer timer = new Timer();
		Zztbj2Service zz = new Zztbj2Service();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = CommonUtil.getEquipmentDataInstance();
				e.setV_equipment_name("AZ00005067");
				zz.sendEquipmentData(e);
			}
		}, 1000, 2*60*1000);
	}

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = TBJUtil.getDataString(v);
		SocketUtil.init2(SystemEnum.ZZ_TBJ2_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.ZZ_TBJ2_SYSTEM.toString(), 1,info, log);
	}
}

