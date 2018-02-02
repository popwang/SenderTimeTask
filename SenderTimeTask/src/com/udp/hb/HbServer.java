package com.udp.hb;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface;
import com.mapper.CommonMapper;
import com.utils.ByteUtil;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.HbUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
@Component("hbServer")
public class HbServer implements ServerInterface {
	public static Log log = LogFactory.getLog(HbServer.class);

	@Autowired
	private CommonMapper mapper;
	
	@Override
	public void handler() {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(SystemEnum.HB_SJZ_SYSTEM.getId());
		log.info(SystemEnum.HB_SJZ_SYSTEM.getName() + "本轮待发送设备数为：" + list.size());
		for (EquipmentProjectVo vo : list) {
			EquipmentData e = mapper.selectDataByName(vo.getV_equipment_name().substring(2));
			if (e == null) {
				log.info(vo.getV_equipment_name() + "当前无数据。");
				continue;
			}
			//对方系统给设备分配的独立编号，存在v_url字段中
			e.setI_equipment_id(Integer.parseInt(vo.getV_url()));
			HbUtil hb = new HbUtil(e);
			CommonUtil.sendByteDataToUDP(ConfigReader.getHbIP(), ConfigReader.getHbPORT(),
					hb.getAllInfoBytes(), log);
		}
		log.info(SystemEnum.HB_SJZ_SYSTEM.getName() + "本轮数据发送完成！");
	}

	public static void main(String[] args) {
		//01000100C91200320209000310170505095442010103010402010300D2030103271F6901030438700103031671010301B3820103000083010300A603D42804
		EquipmentData e = new EquipmentData();
		e.setI_equipment_id(260);
		e.setP002(79.0);
		e.setP003(108.0);
		e.setP004(0.0);
		e.setP005(166);
		e.setP006(26.0);
		e.setP007(21.0);
		e.setP008(43.5);
		e.setP010(1001.5);
		HbUtil hb = new HbUtil(e);
		System.out.println(ByteUtil.bytesToHexString(hb.getAllInfoBytes()).toUpperCase());
		CommonUtil.sendByteDataToUDP(ConfigReader.getHbIP(), ConfigReader.getHbPORT(),
				hb.getAllInfoBytes(), log);
	}
}
