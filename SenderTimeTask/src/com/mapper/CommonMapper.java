package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vo.EquipmentData;
import com.vo.EquipmentProjectVo;
import com.vo.OrderBufferVo;

public interface CommonMapper {
	/**
	 * ͨ�÷����������豸���Ʋ�ѯ�豸����һ������
	 * @return
	 */
	public EquipmentData selectDataByName(String v_equipment_name);
	
	/**
	 * ����ϵͳID��ѯ��Ӧ���豸�б�
	 * @param i_system_id
	 * @return
	 */
	public List<EquipmentProjectVo> selectEquipmentListBySystemId(@Param("i_system_id")int id);
	
	/**
	 * ��������Ϣ���뷢�ͻ����
	 * @param vo
	 * @return
	 */
	public int insertWeatherInfoIntoBuffer(OrderBufferVo vo);
}
