package com.tcp.zksq;

import org.springframework.stereotype.Component;

import com.common.service.AbstractBaseService;
import com.tcp.hncs.HnUtil;
import com.utils.CommonUtil;
import com.utils.SocketUtil;
import com.utils.SystemEnum;
import com.vo.EquipmentData;

/**
 * 周口沈丘平台，我们自己的平台
 * 经营不善已经关闭了
 * @author 27438
 */
@Component
public class ZksqService extends AbstractBaseService {

	@Override
	public void sendEquipmentData(EquipmentData v) {
		String info = HnUtil.getDataString(v);
		if(v.getV_equipment_name().contains("ZB")){
			info = info.replaceAll("AZ", "ZB");
		}
		SocketUtil.init2(SystemEnum.ZK_SQ_SYSTEM.toString());
		SocketUtil.sendDataBySocket(SystemEnum.ZK_SQ_SYSTEM.toString(), 1,info, log);
	}
	
	public static void main(String[] args) {
		EquipmentData v = CommonUtil.getEquipmentDataInstance();
		v.setV_equipment_name("AZ00005274");
		ZksqService zk  = new ZksqService();
		zk.sendEquipmentData(v);
	}
	

}
