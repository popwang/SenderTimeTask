package com.tcp.zk;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * �ܿ����ݷ��ͽӿ�
 * ��ϵ�绰����18638065672��΢���ṩ������Ϣ������ӡ�
 * �������Ҵ���Ϊ��16
 * @author pactera
 */
@Component("zkServer")
public class ZkServer implements ServerInterface {
	public static Log log = LogFactory.getLog(ZkServer.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.HA_ZK_SYSTEM.getId());
		log.info(SystemEnum.HA_ZK_SYSTEM.getName()+"���ִ������豸��Ϊ��"+list.size());
		//Y0394 001600212
		for(EquipmentProjectVo vo : list){
			EquipmentData e = mapper.selectDataByName(vo.getV_real_equipment_name());
			if(e==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			e.setV_equipment_name(vo.getV_equipment_name());
			String info = CRC.getDataString3(e);
			log.info(SystemEnum.HA_ZK_SYSTEM.getName()+"��������:" + info);
			
			CommonUtil.sendDataToRemote(ConfigReader.getHost(SystemEnum.HA_ZK_SYSTEM.toString()),
					ConfigReader.getPort(SystemEnum.HA_ZK_SYSTEM.toString()),info,log);
		}
		log.info("���ִ����ݷ�����ɣ�");
	}
		
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = new EquipmentData();
				e.setV_equipment_name("000400069");
				e.setP001(0);
				e.setP002(68);
				e.setP003(85);
				e.setP004(0.5);
				e.setP005(3);
				e.setP006(28.0);
				e.setP007(45.3);
				e.setP008(52.1);
				e.setP009(0);
				e.setP010(0);
				String info = CRC.getDataString3(e);
				log.info("��������:" + info);
				CommonUtil.sendDataToRemote(ConfigReader.getHost(SystemEnum.HA_ZK_SYSTEM.toString()),
						ConfigReader.getPort(SystemEnum.HA_ZK_SYSTEM.toString()),info,log);
			}
		}, 1000, 1*120*1000);
	}
}
