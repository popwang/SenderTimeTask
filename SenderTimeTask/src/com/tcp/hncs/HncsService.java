package com.tcp.hncs;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.service.BaseService;
import com.mapper.CommonMapper;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

/**
 * 湖南长沙对接接口
 * 设备编号从110000开始共6位，以11开头，后面接4位设备真实地址
 * 服务地址：www.zzyczy.com.cn:30773
 * @author pactera
 *
 */
@Component
public class HncsService implements BaseService {
	public static Log log = LogFactory.getLog(HncsService.class);
	@Autowired
	private CommonMapper mapper;
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.HN_CS_SYSTEM.getId());
		log.info(SystemEnum.HN_CS_SYSTEM.getName()+"本轮待发送设备数为：" + list.size());
		for (EquipmentProjectVo vo : list) {
			EquipmentData v = mapper.selectDataByName("0000"+vo.getV_equipment_name().substring(2));
			if(v==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			v.setV_equipment_name(vo.getV_equipment_name());
			String info = getDataString(v);
			SocketUtil.init(SystemEnum.HN_CS_SYSTEM.toString(), ConfigReader.getHnChangshaIP(),
					ConfigReader.getHnChangshaPORT());
			SocketUtil.sendDataBySocket(SystemEnum.HN_CS_SYSTEM.toString(), 1,info, log);
		}
	}
	
	@Override
	public String getDataString(EquipmentData v) {
		return HnUtil.getDataString(v);
	}
	
	public static void main(String[] args){
		HncsService cs = new HncsService();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("110464");
		String info = cs.getDataString(v);
		log.info(info);
		SocketUtil.init(SystemEnum.HN_CS_SYSTEM.toString(), ConfigReader.getHnChangshaIP(),
				ConfigReader.getHnChangshaPORT());
		SocketUtil.sendDataBySocket(SystemEnum.HN_CS_SYSTEM.toString(), 1,info, log);
	}

}
