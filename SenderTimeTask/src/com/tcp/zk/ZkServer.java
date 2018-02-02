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
 * 周口数据发送接口
 * 联系电话：张18638065672
 * 安正厂家代码为：16
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
		log.info("本轮待发送设备数为："+list.size());
		//001600212
		for(EquipmentProjectVo vo : list){
			EquipmentData e = mapper.selectDataByName("000"+vo.getV_equipment_name().substring(4));
			if(e==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			e.setV_equipment_name(vo.getV_equipment_name());
			String info = CRC.getDataString3(e);
			log.info("发送内容:" + info);
			
			CommonUtil.sendDataToRemote(ConfigReader.getZkIP(),
					ConfigReader.getZkPORT(),info,log);
		}
		log.info("本轮待数据发送完成！");
	}
		
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = new EquipmentData();
				e.setV_equipment_name("001600212");
				e.setP001(0);
				e.setP002(68);
				e.setP003(85);
				e.setP004(0.5);
				e.setP005(3);
				e.setP006(32.0);
				e.setP007(45.3);
				e.setP008(52.1);
				e.setP009(0);
				e.setP010(0);
				String info = CRC.getDataString3(e);
				log.info("发送内容:" + info);
				CommonUtil.sendDataToRemote(ConfigReader.getZkIP(),
						ConfigReader.getZkPORT(),info,log);
			}
		}, 1000, 1*30*1000);
	}
}
