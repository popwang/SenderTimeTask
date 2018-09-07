package com.tcp.xabq;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.CommonMapper;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * �����������������QQ:380787270
 * ����ŷ������ң�������ƽ̨���еǼ�
 * SDYKAZ00000087
 * �����Զ����ţ���̨���
 * ���ڼ����Ϊ80-100��֮�䣬��90�룬cron�Ķ�ʱ�����޷���ɣ���ʹ��schedule��ʱ���ܣ�����main��������
 * @author pactera
 */
@Component
public class XabqService implements Runnable {

	public static Log log = LogFactory.getLog(XabqService.class);
	@Autowired
	private CommonMapper mapper;
	
	@Override
	public void run() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.XA_BQ_SYSTEM.getId());
		log.info(SystemEnum.XA_BQ_SYSTEM.getName()+"���ִ������豸��Ϊ��"+list.size());
		for(EquipmentProjectVo vo : list){
			EquipmentData e = mapper.selectDataByName(vo.getV_real_equipment_name());
			if(e==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			e.setV_equipment_name(vo.getV_equipment_name());
			String info = XabqUtil.getAirString(e);
			SocketUtil.init2(SystemEnum.XA_BQ_SYSTEM.toString());
			SocketUtil.sendDataBySocket(SystemEnum.XA_BQ_SYSTEM.toString(), 1,info, log);
		}
		log.info(SystemEnum.XA_BQ_SYSTEM.getName()+"���ִ����ݷ�����ɣ�");
	}
		
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = CommonUtil.getEquipmentDataInstance();
				e.setV_equipment_name("SDYKAZ00000087");
				String info = XabqUtil.getAirString(e);
				log.info("��������:" + info);
				CommonUtil.sendDataToRemote(ConfigReader.getHost(SystemEnum.XA_BQ_SYSTEM.toString()),
						ConfigReader.getPort(SystemEnum.XA_BQ_SYSTEM.toString()),info,log);
			}
		}, 1000, 1*30*1000);
	}

}
