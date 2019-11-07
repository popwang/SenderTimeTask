package com.tcp.zz;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

/**
 * 1.֣���رؼѹ�˾�ӿڣ�Ŀǰ����5̨�豸�� 
 * 2.�豸�б�ֱ����Ӽ��ɣ� 
 * 3.�����绰����-177 3718 3234
 * 4.20180808,�޸����ݲ�ѯ��ʽ��һ���Թ�����ѯȫ�����ݣ������̳߳ز����������ݣ�
 * 5.����˼ά����ÿ10���Ӽ��з��ͣ���Ϊһ���ӷ�һ���֣�ÿ��ֻ�����豸β���뵱ǰ������ͬ���豸���ݡ�
 * @author Administrator
 */
@Component
public class ZztbjService extends AbstractBaseService {
	
	public static void main(String[] args){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = CommonUtil.getEquipmentDataInstance();
				e.setV_equipment_name("AZ00005348");
				String info = TBJUtil.getDataString(e);
				SocketUtil.init(SystemEnum.ZZ_TBJ_SYSTEM.toString(), "123.15.58.210", 9123);
				SocketUtil.sendDataBySocket(SystemEnum.ZZ_TBJ_SYSTEM.toString(), 1,info, log);
			}
		}, 1000, 2*60*1000);
//		String no = "AZ01000947";
//		System.out.println(no.substring(no.length()-1));
	}

	@Override
	public void handler(SystemEnum systemEnum) {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		int minute = calendar.get(Calendar.MINUTE)%10;
		for (EquipmentProjectVo vo : list) {
			if(Integer.parseInt(vo.getV_equipment_name().substring(vo.getV_equipment_name().length()-1))==minute) {
				EquipmentData v = mapper.selectDataByName(vo.getV_real_equipment_name());
				if(v==null){
					log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
					continue;
				}
				v.setV_equipment_name(vo.getV_equipment_name());
				log.info("��ǰ�����豸�ţ�"+v.getV_equipment_name());
				String info = TBJUtil.getDataString(v);
//				SocketUtil.init2(SystemEnum.ZZ_TBJ_SYSTEM.toString());
//				SocketUtil.sendDataBySocket(SystemEnum.ZZ_TBJ_SYSTEM.toString(), 1,info, log);
				CommonUtil.sendDataToRemote2(SystemEnum.ZZ_TBJ_SYSTEM.toString(), info, log);
			}
		}
		log.info(systemEnum.getName()+"���ַ�����ɡ�");
	}
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		
	}
}

