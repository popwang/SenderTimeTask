package com.tcp.sxyc;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.utils.CRC;
import com.utils.CommonUtil;
import com.utils.ConfigReader;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;
/**
 * 山西运城数据对接服务
 * 重写数据发送接口即可
 * @author pactera
 */
@Component
public class SxycService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		System.out.println("发送完成");
	}
	
	public static void main(String[] args) {
		EquipmentData e = CommonUtil.getEquipmentDataInstance();
		e.setV_equipment_name("123456780359A012");
		YcUtil yc = new YcUtil(e);
		
		SocketUtil.init(SystemEnum.SX_YC_SYSTEM.toString(), ConfigReader.getHost(SystemEnum.SX_YC_SYSTEM.toString()),
				ConfigReader.getPort(SystemEnum.SX_YC_SYSTEM.toString()));
		
		SocketUtil.sendByteDataBySocket(SystemEnum.SX_YC_SYSTEM.toString(), 10, yc.getDataInfo(), log);
//		SocketUtil.sendByteDataBySocket(SystemEnum.SX_YC_SYSTEM.toString(), 5, yc.getHeartBeat(), log);
	
		CommonUtil.sendByteDataToRemote(ConfigReader.getHost(SystemEnum.SX_YC_SYSTEM.toString()),
				ConfigReader.getPort(SystemEnum.SX_YC_SYSTEM.toString()), yc.getDataInfo(), log);
		
		
	}
}
