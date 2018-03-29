package com.common.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.ServerInterface2;
import com.mapper.CommonMapper;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;

public abstract class AbstractBaseService implements ServerInterface2 {
	
	public static Log log = LogFactory.getLog(AbstractBaseService.class);
	
	@Autowired
	private CommonMapper mapper;
	
	@Override
	public void handler(SystemEnum systemEnum) {
		List<EquipmentProjectVo> list = mapper.selectEquipmentListBySystemId(systemEnum.getId());
		log.info(systemEnum.getName()+"���ִ������豸��Ϊ��" + list.size());
		for (EquipmentProjectVo vo : list) {
			EquipmentData v = mapper.selectDataByName(vo.getV_real_equipment_name());
			if(v==null){
				log.info(vo.getV_equipment_name()+"��ǰ�����ݡ�");
				continue;
			}
			v.setV_equipment_name(vo.getV_equipment_name());
			sendEquipmentData(v);
		}
		log.info(systemEnum.getName()+"���ַ�����ɡ�");
	}
	/**
	 * ���ݽӿ�ʵ�ֲ�ͬ���������ɲ��Ժͷ��Ͳ���
	 * @param v
	 */
	public abstract void sendEquipmentData(EquipmentData v);

}
