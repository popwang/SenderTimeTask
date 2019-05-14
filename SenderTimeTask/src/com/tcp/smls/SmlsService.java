package com.tcp.smls;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
@Component
public class SmlsService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		/**
		 * 2分钟执行一次，理论上可以卡点把2分钟之内的数据上传
		 * 由于单次执行最大有设备数*2秒的延迟，可能会把卡点上传的数据给忽略掉
		 * 因此加上30秒的缓存
		 */
		if(v.getDuration()>=2*60+30) {
			return;
		}
		String info  = SmlsUtil.getInfoString(v);
		log.info(info);
		int delay = new Random().nextInt(2);
		try {
			Thread.sleep(1000*delay);
			SocketUtil.init2(SystemEnum.SM_LS_SYSTEM.toString());
			SocketUtil.sendDataBySocket(SystemEnum.SM_LS_SYSTEM.toString(), 1,info, log);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EquipmentData v  = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("00005297");
		String info  = SmlsUtil.getInfoString(v);
		System.out.println(info);
		SocketUtil.init2(SystemEnum.SM_LS_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.SM_LS_SYSTEM.toString(), 1,info, log);
	}

}
