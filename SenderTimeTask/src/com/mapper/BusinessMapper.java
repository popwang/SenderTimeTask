package com.mapper;

import java.util.List;

import com.vo.EquipmentData;
import com.vo.EquipmentInfo;

public interface BusinessMapper {
	/**
	 * ��ѯ�豸��Ϣ
	 * @return
	 */
	public List<EquipmentInfo> selectEquipmentInfo();
	/**
	 * �����豸��Ϣ
	 * @param e
	 * @return
	 */
	public int updateEquipmentInfo(EquipmentInfo e);
	/**
	 * ��ѯ���µ��豸��Ϣ
	 * @param v_equipment_name
	 * @return
	 */
	public EquipmentData selectDataByNameFromData2(String v_equipment_name);
	
}
