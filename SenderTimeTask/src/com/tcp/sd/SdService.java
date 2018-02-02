package com.tcp.sd;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.CommonMapper;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

/**
 * 1.ɽ�������޳ǶԽӳɹ�����˾Ϊ���ڹ�˾��������֣�ݽ�ί��traffic��Ϊͬһ�ҹ�˾�������㷨��ͬ��
 * 2.Ŀǰ�ѽ���187һ̨�豸��
 * 3.�������Ҫ�����豸��Ҫ����ʽ��д������Ϣ�ύ�����ڹ�˾��
 * 4.�����绰����-18124691046��QQ:259066977
 * @author Administrator
 */
@Component
public class SdService {
	public static Log log = LogFactory.getLog(SdService.class);
	@Autowired
	private CommonMapper mapper;
	public void handler(){
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.SD_JN_SYSTEM.getId());
		log.info(SystemEnum.SD_JN_SYSTEM.getName()+"���ִ������豸��Ϊ��"+list.size());
		for(EquipmentProjectVo vo : list){
			EquipmentData e = mapper.selectDataByName(vo.getV_equipment_name());
			if(e==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			String info = CRC.getDataString(e);
//			log.info("��������:"+info);
//			Socket socket = SocketUtil.getSocket("SD_JN_SYSTEM", ConfigReader.getSdjnIP(),
//					ConfigReader.getSdjnPORT());
//			SocketUtil.sendDataBySocket(socket, 5, info, log);
			CommonUtil.sendDataToRemote(ConfigReader.getSdjnIP(),
					ConfigReader.getSdjnPORT(), info, log);
		}
	}
	
public static void main(String[] args){
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = new EquipmentData();
				e.setV_equipment_name("AZ000117110901");
				e.setP001(0);
				e.setP002(68);
				e.setP003(83);
				e.setP004(0.5);
				e.setP005(3);
				e.setP006(32.0);
				e.setP007(45.3);
				e.setP008(52.1);
				e.setP009(0);
				e.setP010(0);
				String info = CRC.getDataString(e);
				CommonUtil.sendDataToRemote(ConfigReader.getSdjnIP(),
						ConfigReader.getSdjnPORT(), info, log);
			}
		}, 1000, 1*30*1000);
	}
}
