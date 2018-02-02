package com.tcp.xa;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * 西安长安区对接接口，协议使用212
 * 添加设备需要先找张工（微信）审核工地信息，分配MN号 
 * AZ00000075：AZ000117110901
 * AZ00000109：AZ000117112903
 * @author pactera
 */
@Component
public class XaService implements ServerInterface {

	public static Log log = LogFactory.getLog(XaService.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void handler(){
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.SX_XA_SYSTEM.getId());
		log.info(SystemEnum.SX_XA_SYSTEM.getName()+"本轮待发送设备数为："+list.size());
		for(EquipmentProjectVo vo : list){
			EquipmentData e = mapper.selectDataByName(vo.getV_equipment_name().substring(2));
			if(e==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			e.setV_equipment_name(vo.getV_url());
			String info = CRC.getDataString4(CommonUtil.getRandomEquipmentData(e));
//			log.info("发送内容:"+info);
			
			SocketUtil.init(SystemEnum.SX_XA_SYSTEM.toString(), ConfigReader.getXaIP(),
					ConfigReader.getXaPORT());
			SocketUtil.sendDataBySocket(SystemEnum.SX_XA_SYSTEM.toString(), 1,info, log);
//			CommonUtil.sendDataToRemote(ConfigReader.getXaIP(),
//					ConfigReader.getXaPORT(), info, log);
		}
		log.info(SystemEnum.SX_XA_SYSTEM.getName()+"本轮发送完成。");
	}
	
	public static void main(String[] args){
		EquipmentData e = new EquipmentData();
		e.setV_equipment_name("AZ000117111502");
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
		String info = CRC.getDataString4(e);
		CommonUtil.sendDataToRemote(ConfigReader.getXaIP(),
				ConfigReader.getXaPORT(), info, log);
	}
}
