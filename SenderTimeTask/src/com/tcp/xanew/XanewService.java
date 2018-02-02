package com.tcp.xanew;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SystemEnum;
import com.utils.TBJUtil;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
/**
 * 西安高新区对接接口，协议使用郑州特必佳协议
 * 联系人微信：蓝天
 * ip:61.185.220.176:8068
 * @author pactera
 */
@Component
public class XanewService implements ServerInterface {

	public static Log log = LogFactory.getLog(XanewService.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.XA_GXQ_SYSTEM.getId());
		log.info(SystemEnum.XA_GXQ_SYSTEM.getName()+"本轮待发送设备数为：" + list.size());
		for (EquipmentProjectVo vo : list) {
			EquipmentData v = mapper.selectDataByName(vo.getV_equipment_name().substring(2));
			if(v==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			v.setV_equipment_name(vo.getV_equipment_name());
			String info = TBJUtil.getDataString(v);
			log.info("发送内容:" + info);
			CommonUtil.sendDataToRemote(ConfigReader.getXaGxqIP(), ConfigReader.getXaGxqPORT(),info,log);
		}
	}

}
