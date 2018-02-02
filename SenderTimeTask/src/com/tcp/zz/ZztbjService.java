package com.tcp.zz;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapper.CommonMapper;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

/**
 * 1.郑州特必佳公司接口；目前接入5台设备； 2.设备列表直接添加即可； 3.技术电话：宋-177 3718 3234
 * 
 * @author Administrator
 */
@Component
public class ZztbjService {

	public static Log log = LogFactory.getLog(ZztbjService.class);
	@Autowired
	private CommonMapper mapper;

	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.ZZ_TBJ_SYSTEM.getId());
		log.info("本轮待发送设备数为：" + list.size());
		for (EquipmentProjectVo vo : list) {
			EquipmentData v = mapper.selectDataByName(vo.getV_equipment_name().substring(2));
			if(v==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			v.setV_equipment_name(vo.getV_equipment_name());
			String info = TBJUtil.getDataString(v);
			log.info("发送内容:" + info);
			CommonUtil.sendDataToRemote(ConfigReader.getZZtbjIP(), ConfigReader.getZZtbjPORT(),info,log);
		}
	}
	
	public static void main(String[] args){
		EquipmentData e = new EquipmentData();
		e.setV_equipment_name("AZ00000168");
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
		String info = TBJUtil.getDataString(e);
		log.info("发送内容:" + info);
//		CommonUtil.sendDataToRemote("61.185.220.176", 8068,info,log);
		
//		SocketUtil.init(SystemEnum.SD_JINAN_SYSTEM.toString(), "123.15.58.210", 9123);
//		SocketUtil.sendDataBySocket(SystemEnum.SD_JINAN_SYSTEM.toString(), 1,info, log);	
		
	}
}
