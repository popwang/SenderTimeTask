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
		 * 2����ִ��һ�Σ������Ͽ��Կ����2����֮�ڵ������ϴ�
		 * ���ڵ���ִ��������豸��*2����ӳ٣����ܻ�ѿ����ϴ������ݸ����Ե�
		 * ��˼���30��Ļ���
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
