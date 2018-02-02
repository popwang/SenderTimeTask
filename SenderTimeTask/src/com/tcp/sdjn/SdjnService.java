package com.tcp.sdjn;

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
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

/**
 * 山东济南扬尘对接接口
 * 联系电话：周庆坤 151 5416 1520，已对接成功，尚未发送工地信息，没有上传数据
 * 对接设备号：391
 * @author Administrator
 */
@Component
public class SdjnService implements ServerInterface {

	public static Log log = LogFactory.getLog(SdjnService.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void handler(){
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.SD_JINAN_SYSTEM.getId());
		log.info(SystemEnum.SD_JINAN_SYSTEM.getName()+"本轮待发送设备数为："+list.size());
		for(EquipmentProjectVo vo : list){
			EquipmentData e = mapper.selectDataByName(vo.getV_equipment_name().substring(2));
			if(e==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			e.setV_equipment_name(vo.getV_url());
			String info = CRC.getDataString5(e);
//			log.info("发送内容:"+info);
			SocketUtil.init(SystemEnum.SD_JINAN_SYSTEM.toString(), ConfigReader.getJiNanIP(),
					ConfigReader.getJiNanPORT());
			SocketUtil.sendDataBySocket(SystemEnum.SD_JINAN_SYSTEM.toString(), 1, CRC.getHeartBit(vo.getV_url()), log);
			SocketUtil.sendDataBySocket(SystemEnum.SD_JINAN_SYSTEM.toString(), 1,info, log);
		}
	}
	
	public static void main(String[] args){
		SocketUtil.init(SystemEnum.SD_JINAN_SYSTEM.toString(), ConfigReader.getJiNanIP(),
				ConfigReader.getJiNanPORT());
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				EquipmentData e = new EquipmentData();
				e.setV_equipment_name("003701020000001211001307");
				e.setP001(0);
				e.setP002(68);
				e.setP003(85);
				e.setP004(0.5);
				e.setP005(3);
				e.setP006(32.0);
				e.setP007(10.3);
				e.setP008(52.1);
				e.setP009(0);
				e.setP010(0);
				String info = CRC.getDataString5(e);
				
				SocketUtil.sendDataBySocket(SystemEnum.SD_JINAN_SYSTEM.toString(), 5, CRC.getHeartBit(e.getV_equipment_name()), log);
					
				SocketUtil.sendDataBySocket(SystemEnum.SD_JINAN_SYSTEM.toString(), 5,info, log);
			}
		}, 1000, 1*30*1000);
	}

}
