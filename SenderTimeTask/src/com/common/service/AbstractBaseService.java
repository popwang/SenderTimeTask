package com.common.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.ServerInterface2;
import com.mapper.CommonMapper;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

public abstract class AbstractBaseService implements ServerInterface2 {
	
	public static Log log = LogFactory.getLog(AbstractBaseService.class);
	
	@Autowired
	protected CommonMapper mapper;
	
	protected SystemEnum systemEnum;
	
	@Override
	public void handler(SystemEnum systemEnum) {
		this.setSystemEnum(systemEnum);
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
		log.info(systemEnum.getName()+"本轮待发送设备数为：" + list.size());
		for (EquipmentProjectVo vo : list) {
			EquipmentData v = mapper.selectDataByName(vo.getV_real_equipment_name());
			if(v==null){
				log.info(vo.getV_equipment_name()+"当前无数据。");
				continue;
			}
			v.setV_equipment_name(vo.getV_equipment_name());
			v.setV_project_name(vo.getV_project_name());
			v.setV_url(vo.getV_url());
			log.info("当前发送设备号："+v.getV_equipment_name());
			sendEquipmentData(v);
		}
		log.info(systemEnum.getName()+"本轮发送完成。");
	}
	/**
	 * 根据接口实现不同的数据生成策略和发送策略
	 * @param v
	 */
	public abstract void sendEquipmentData(EquipmentData v);
	
	public void setSystemEnum(SystemEnum systemEnum) {
		this.systemEnum = systemEnum;
	}
	public SystemEnum getSystemEnum() {
		return systemEnum;
	}
}
