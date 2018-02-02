package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
import com.vo.OrderBufferVo;

public interface CommonMapper {
	/**
	 * 通用方法：根据设备名称查询设备最新一条数据
	 * @return
	 */
	public EquipmentData selectDataByName(String v_equipment_name);
	
	/**
	 * 根据系统ID查询对应的设备列表
	 * @param i_system_id
	 * @return
	 */
	public List<EquipmentProjectVo> selectEquipmentListBySystemId(@Param("i_system_id")int id);
	
	/**
	 * 将发送信息插入发送缓存表
	 * @param vo
	 * @return
	 */
	public int insertWeatherInfoIntoBuffer(OrderBufferVo vo);
}
