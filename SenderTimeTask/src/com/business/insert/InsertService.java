package com.business.insert;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 鉴于某些设备发出后联网模块损坏导致不能联网，为避免更换或修理带来的不便
 * 将其他设备数据定时顶替插入表中
 * @author pactera
 */
@Component
public class InsertService extends AbstractBaseService {
	
	@Override
	public void sendEquipmentData(EquipmentData v) {
		String[] tmp = v.getV_project_name().split(",");
		v.setP015(Double.parseDouble(tmp[0]));
		v.setP014(Double.parseDouble(tmp[1]));
		mapper.deleteEquipmentData2(v);
		mapper.saveEquipmentData2(v);
		mapper.saveEquipmentData(v);
	}
	
	public static void main(String[] args) {
		InsertService is = new InsertService();
		is.handler(SystemEnum.INSERT_SYSTEM);
	}

}
