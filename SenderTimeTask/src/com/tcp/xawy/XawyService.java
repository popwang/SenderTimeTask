package com.tcp.xawy;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 西安未央区接口对接
 * 地址：116.62.41.41 :8080
 * 技术支持：15709248662   吴工
 * @author 27438
 *
 */
@Component
public class XawyService  extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = XawyUtil.getDataStr(v);
		CommonUtil.sendDataToRemote2(SystemEnum.XA_WY_SYSTEM.toString(), info, log);
	}
	
	
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("180418118");
		String info = XawyUtil.getDataStr(v);
		CommonUtil.sendDataToRemote("116.62.41.41", 8080, info, log);
	}
}
