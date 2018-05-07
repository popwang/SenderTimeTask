package com.tcp.anhx;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 安阳滑县2平台，使用tcp协议，原平台可能已经弃用
 * 218.28.94.157:60009
 * @author pactera
 */
@Component
public class Ayhx2Service extends AbstractBaseService {
	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = AyHxUtil.getDataString(v);
		SocketUtil.init2(SystemEnum.AY_HX2_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.AY_HX2_SYSTEM.toString(), 1, info, log);
	}
	
	public static void main(String[] args) {
		Ayhx2Service service = new Ayhx2Service();
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("811170211");
		v.setV_project_name("天骄学府");
		service.sendEquipmentData(v);
	}
}
