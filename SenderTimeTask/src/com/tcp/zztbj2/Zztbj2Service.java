package com.tcp.zztbj2;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.hncs.HnUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.utils.ThreadPoolUtil;
import com.vo.EquipmentData;

/**
 * 1.֣���رؼѹ�˾�ӿڣ�Ŀǰ����5̨�豸�� 
 * 2.�豸�б�ֱ����Ӽ��ɣ� 
 * 3.�����绰����-177 3718 3234
 * 4.20180808,�޸����ݲ�ѯ��ʽ��һ���Թ�����ѯȫ�����ݣ������̳߳ز����������ݣ�
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

