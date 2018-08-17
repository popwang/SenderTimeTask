package com.mapper;

import java.util.List;

import com.vo.EquipmentData;
import com.vo.EquipmentInfo;

public interface BusinessMapper {
	/**
	 * 查询设备信息
	 * @return
	 */
	public List<EquipmentInfo> selectEquipmentInfo();
	/**
	 * 更新设备信息
	 * @param e
	 * @return
	 */
	public int updateEquipmentInfo(EquipmentInfo e);
	/**
	 * 查询最新的设备信息
	 * @param v_equipment_name
	 * @return
	 */
	public EquipmentData selectDataByNameFromData2(String v_equipment_name);
	
}
